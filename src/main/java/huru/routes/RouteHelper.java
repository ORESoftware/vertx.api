package huru.routes;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.RoutingContext;

public class RouteHelper {
  
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
  
  static void getSQLConnection(SQLClient client, RoutingContext ctx, Handler<AsyncResult<SQLConnection>> v) {
    
    client.getConnection(res -> {
      
      if (!res.succeeded()) {
        System.out.println("Here comes the cause:");
        System.out.println(res.cause());
        ctx.fail(res.cause());
        return;
      }
      
      v.handle(res);
      
    });
  }
}
