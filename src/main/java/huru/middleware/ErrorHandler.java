package huru.middleware;

import huru.MainVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.BodyHandlerImpl;
import org.apache.log4j.Logger;

public class ErrorHandler implements Handler<RoutingContext> {
  
  private final Logger log = Logger.getLogger(MainVerticle.class);
  
  public void handle(RoutingContext ctx) {
    HttpServerRequest request = ctx.request();
    try{
      ctx.next();
    }
    catch(Exception err){
      err.printStackTrace(System.err);
      ctx.response().end("We messed up: " + err.getMessage());
    }
  }
}
