package huru;

import huru.entity.Annotations;
import huru.entity.BaseModel;
import huru.entity.Models;
import huru.entity.UserModel;
import huru.middleware.JWTHandler;
import huru.query.Bootstrapper;
import huru.query.QueryBuilder;
import huru.routes.KCClass;
import huru.routes.KCUser;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.SQLRowStream;
import io.vertx.ext.sync.Sync;
import io.vertx.ext.web.Router;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.RoutingContext;
import org.apache.log4j.Logger;
import io.vertx.core.Future;
import io.vertx.ext.web.handler.BodyHandler;

import static huru.entity.Models.*;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;


public class MainVerticle extends AbstractVerticle {
  
  private final Logger log = Logger.getLogger(MainVerticle.class);
  
  private Future<Void> deployHelper(String name) {
    
    final Future<Void> future = Future.future();
    vertx.deployVerticle(name, res -> {
      if (res.failed()) {
        log.error("Failed to deploy verticle " + name);
        future.fail(res.cause());
      } else {
        log.info("Deployed verticle " + name);
        future.complete();
      }
    });
    
    return future;
  }
  
  
  @Override
  public void start(Future<Void> f) throws Exception {
    
    super.start();

//    JsonObject config = new JsonObject()
//      .put("url", "jdbc:hsqldb:mem:test?shutdown=true")
//      .put("driver_class", "org.hsqldb.jdbcDriver")
//      .put("max_pool_size", 30);
//
//    SQLClient client = JDBCClient.createShared(vertx, config);

//    final Vertx vertx = Vertx.vertx();
  
    QueryBuilder<User> qb = new QueryBuilder<User>(User.Model);
    
    
    log.info("foo");
    
    JsonObject config = new JsonObject()
      .put("username", "postgres")
      .put("password", "postgres")
      .put("database", "oleg")
//      .put("host", "0.0.0.0")
      .put("host", "localhost")
      .put("port", 5432);
    
    final SQLClient client = PostgreSQLClient.createShared(vertx, config);
    final Router router = Router.router(vertx);
//    final Router router = mainRouter.mountSubRouter("/api",mainRouter);
    
    router.route()
      .handler(BodyHandler.create())
//      .handler(Sync.fiberHandler(new JWTHandler()));
      .handler(new JWTHandler());
    
    
    new KCUser(client)
      .mount(router, "/api/user");
    
    
    router.route("/foo1").handler(h -> {
      
      throw new RuntimeException("shit1");
//      h.response().write("damn");
    });
    
    
    router.route("/foo2").handler(h -> {
      
      vertx.runOnContext(v -> {
        throw new RuntimeException("shit2");
      });
    });

//    router.route("/api/user").handler(new KCUser(client));
    router.route("/api/class").handler(new KCClass(client));
    
    router.route().failureHandler(ctx -> {
      ctx.response().end("We failed here!");
    });
    
    router.route().last().handler(ctx -> {
      ctx.response()
        .setStatusCode(404)
        .end("404 - route/resource could not be found.");
    });
    
    
    int port = 3005;
    
    vertx.createHttpServer()
      .exceptionHandler(ctx -> {
        
        log.error("In the exception handler.");
        log.error(ctx.getCause());
        
      })
      .requestHandler(router)
//      .requestHandler(req -> {
//
//        if (req.method() == HttpMethod.GET) {
//
//          req.response().setChunked(true);
//
//          if (req.path().equals("/products")) {
//
//            vertx.eventBus().<String>send("whoooop", "", r -> {
//              if (r.succeeded()) {
//                req.response().setStatusCode(200).write(r.result().body()).end();
//              } else {
//                req.response().setStatusCode(500).write(r.cause().toString()).end();
//              }
//            });
//
//            return;
//          }
//
//          req.response().setStatusCode(200).write("Hello from vert.x").end();
//          return;
//
//        }
//
//        req.response()
//          .putHeader("content-type", "text/plain")
//          .end("Hello Vert.x!");
//
//
//      })
      .listen(port, res -> {
        if (res.succeeded()) {
          System.out.println("Server listening on port " + port);
          f.complete();
        } else {
          System.out.println("Failed to launch server");
          f.fail(res.cause());
        }
      });
    
  }
  
}
