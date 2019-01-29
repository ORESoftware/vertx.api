package huru.middleware;

import huru.MainVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.BodyHandlerImpl;
import org.apache.log4j.Logger;

public class JWTHandler implements Handler<RoutingContext> {
  
  private final Logger log = Logger.getLogger(MainVerticle.class);
  
  public void handle(RoutingContext ctx) {
    HttpServerRequest request = ctx.request();
    ctx.put("foo", "bar");  // add stuff
    log.info("in the jwt middleware");
    System.out.println("DOOOOF");
    System.out.println(ctx.request().method());
    ctx.next();
  }
}
