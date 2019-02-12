package huru.routes;

import huru.middleware.JWTHandler;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.SQLRowStream;
import io.vertx.ext.web.RoutingContext;
import org.apache.log4j.Logger;

public class RouteHelper {
  
  private static final Logger log = Logger.getLogger(JWTHandler.class);
  
  static void BasicHandler(RoutingContext ctx, IBasicHandler h) {
    switch (ctx.request().method()) {
      case GET:
        h.basicGet(ctx);
        break;
      case PUT:
        h.basicPut(ctx);
        break;
      default:
        ctx.fail(new Exception("No method matched"));
    }
  }
  
  static <T> Handler<AsyncResult<T>> handleSQLResponse(RoutingContext ctx, Handler<AsyncResult<T>> h){
    
    return res -> {
      
      if(res.failed()){
        ctx.response().setStatusCode(500);
        ctx.response().headers().add("foo","bar");
        ctx.fail(res.cause());
        return;
      }
  
      h.handle(res);
    };
  }
  

  
  static void getSQLConnection(SQLClient client, RoutingContext ctx, Handler<SQLConnection> v) {
    
    client.getConnection(res -> {
      
      if (!res.succeeded()) {
        log.error("Here comes the cause:");
        log.error(res.cause());
        ctx.fail(res.cause());
        return;
      }
      
      v.handle(res.result());
      
    });
  }
}
