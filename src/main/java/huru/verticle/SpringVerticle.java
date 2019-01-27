package huru.verticle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import huru.service.UserService;
import org.springframework.context.ApplicationContext;
import java.util.concurrent.TimeUnit;


/**
 * Simple verticle to wrap a Spring service bean - note we wrap the service call
 * in executeBlocking, because we know it's going to be a JDBC call which blocks.
 * As a general principle with Spring beans, the default assumption should be that it will block unless you
 * know for sure otherwise (in other words use executeBlocking unless you know for sure your service call will be
 * extremely quick to respond)
 *
 */
class Pool {
  static int poolSize = 9;
  static long maxExecuteTime = 2;
  static TimeUnit maxExecuteTimeUnit = TimeUnit.MINUTES;
}

public class SpringVerticle extends AbstractVerticle {
  
  private WorkerExecutor pool;
  static final String ALL_PRODUCTS_ADDRESS = "example.all.products";
  // Reuse the Vert.x Mapper, alternatively you can use your own.
  private final ObjectMapper mapper = Json.mapper;
  private final UserService service;
  
  public SpringVerticle(final Vertx vertx, final ApplicationContext ctx) {
    this.service = (UserService) ctx.getBean("userService");
    this.vertx  = vertx;
    this.pool = vertx.createSharedWorkerExecutor(
      "my-worker-pool",
      Pool.poolSize,
      Pool.maxExecuteTime,
      Pool.maxExecuteTimeUnit
    );
  }
  
  private Handler<Message<String>> allProductsHandler(UserService service) {
    // It is important to use an executeBlocking construct here
    // as the service calls are blocking (dealing with a database)
    return msg -> pool.<String>executeBlocking(f -> {
        try {
          f.complete(mapper.writeValueAsString(service.getAllUsers()));
        } catch (JsonProcessingException e) {
          System.out.println("Failed to serialize result");
          f.fail(e);
        }
      },
      result -> {
        if (result.succeeded()) {
          msg.reply(result.result());
        } else {
          msg.reply(result.cause().toString());
        }
      });
  }
  
  @Override
  public void start() throws Exception {
    super.start();
    vertx.
      eventBus().
      <String>consumer(ALL_PRODUCTS_ADDRESS).handler(allProductsHandler(service));
  }
}