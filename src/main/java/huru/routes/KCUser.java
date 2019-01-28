package huru.routes;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.SQLRowStream;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;

public class KCUser implements IBasicHandler, Handler<RoutingContext> {
  
  private SQLClient client;
  private Map<String, JsonObject> products = new HashMap<>();
  
  public KCUser(SQLClient client) {
    super();
    this.client = client;
  }
  
  @Override
  public void handle(RoutingContext ctx) {
    RouteHelper.BasicHandler(ctx, this);
  }
  
  public void mount(Router r, String routeBase){
    r.route(routeBase).handler(this);
    r.get(routeBase.concat("/:id")).handler(this::getById);
    r.put(routeBase.concat("/:id")).handler(this::putById);
  
  }
  
  public void putById(RoutingContext ctx) {
    
    RouteHelper.getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks 222");
      
    });
  }
  
  public void getById(RoutingContext ctx) {
    
    RouteHelper.getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks 222");
      
    });
  }
  
  public void basicGet(RoutingContext ctx) {
    
    RouteHelper.getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks");
    
    });
  }
  
  
  public void basicPost(RoutingContext ctx) {
    
    RouteHelper.getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks");
      
    });
  }
  
  public void basicDelete(RoutingContext ctx) {
    
    RouteHelper.getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks");
      
    });
  }
  
  public void basicHead(RoutingContext ctx) {
    
    RouteHelper.getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks");
      
    });
  }
  
  public void basicPut(RoutingContext ctx) {
    
    RouteHelper.getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks");
      
    });
  }
  
  private void handleGetProduct(RoutingContext ctx) {
    
    RouteHelper.getSQLConnection(this.client, ctx, res -> {
      
      SQLConnection conn = res.result();
      
      conn.queryStream("SELECT * FROM large_table; SELECT * FROM other_table", stream -> {
        if (stream.succeeded()) {
          SQLRowStream sqlRowStream = stream.result();
          
          sqlRowStream
            .resultSetClosedHandler(v -> {
              // will ask to restart the stream with the new result set if any
              sqlRowStream.moreResults();
            })
            .handler(row -> {
              // do something with the row...
            })
            .endHandler(v -> {
              // no more data available...
              ctx.response().end();
            });
        }
      });
    });
  }
  
  private void handleAddProduct(RoutingContext ctx) {
    
    RouteHelper.getSQLConnection(this.client, ctx, res -> {
      
      SQLConnection conn = res.result();
      String v = "";
      
      conn.query("SELECT * FROM account", results -> {
        
        if (!results.succeeded()) {
          throw new Error("Could not connect to table.");
        }
        
        ResultSet rs = results.result();
        System.out.println("Connected and got results");
        System.out.println(rs.toString());
        
      });
      
    });
  }
  
  
}
