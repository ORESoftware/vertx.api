package huru.routes;

import huru.entity.Model;
import huru.entity.Models;
import huru.entity.UserModel;
import huru.middleware.JWTHandler;
import huru.query.QueryBuilder;
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
import org.apache.log4j.Logger;
import org.jooq.tools.json.JSONObject;

import static huru.routes.RouteHelper.getSQLConnection;
import static huru.routes.RouteHelper.handleSQLResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KCUser implements IBasicHandler, Handler<RoutingContext> {
  
  private static final Logger log = Logger.getLogger(JWTHandler.class);
  private SQLClient client;
  private Map<String, JsonObject> products = new HashMap<>();
  private Models.User User = Model.User;
  private final QueryBuilder<Models.User> qb = new QueryBuilder<>(User);
  
  public KCUser(SQLClient client) {
    super();
    this.client = client;
  }
  
  
  @Override
  public void handle(RoutingContext ctx) {
    RouteHelper.BasicHandler(ctx, this);
  }
  
  public void mount(Router r, String routeBase) {
    r.route(routeBase).handler(this);
    r.get(routeBase.concat("/:id")).handler(this::getById);
    r.put(routeBase.concat("/:id")).handler(this::putById);
    
  }
  
  public void putById(RoutingContext ctx) {
    
    getSQLConnection(this.client, ctx, conn -> {
      
      conn.query("select * from bagel", res -> {
        
        List<JsonObject> o = res.result().getRows();


//        JsonArray v = res.result().toJson().getJsonArray("3");

//        UserModel.Model v = res.result().toJson().mapTo(UserModel.Model.class);
        
        Map<String, Object> v = res.result().getNext().toJson().getMap();
        
        ctx.response().end("boondocks 222");
      });
      
      
    });
  }
  
  public void getById(RoutingContext ctx) {
    
    getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks 222");
      
    });
  }
  
  public void basicGet(RoutingContext ctx) {
    
    getSQLConnection(this.client, ctx, conn -> {
      
      var sql = qb.select()
        .all()
        .from(User.getTableName())
        .getSQL();
      
      
      conn.query(sql, handleSQLResponse(ctx, r -> {
        ctx.response().end(r.result().toJson().toString());
      }));
      
      
      
      
    });
  }
  
  
  public void basicPost(RoutingContext ctx) {
    
    getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks");
      
    });
  }
  
  public void basicDelete(RoutingContext ctx) {
    
    getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks");
      
    });
  }
  
  public void basicHead(RoutingContext ctx) {
    
    getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks");
      
    });
  }
  
  public void basicPut(RoutingContext ctx) {
    
    getSQLConnection(this.client, ctx, r -> {
      
      ctx.response().end("boondocks");
      
    });
  }
  
  private void handleGetProduct(RoutingContext ctx) {
    
    getSQLConnection(this.client, ctx, conn -> {
      
      conn.queryStream(
        "SELECT * FROM large_table; SELECT * FROM other_table",
        handleSQLResponse(ctx, s -> {
          
          SQLRowStream sqlRowStream = s.result();
          
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
          
        }));
    });
  }
  
  private void handleAddProduct(RoutingContext ctx) {
    
    getSQLConnection(this.client, ctx, conn -> {
      
      String v = "";
      
      conn.query("SELECT * FROM account", handleSQLResponse(ctx, r -> {
        
        ResultSet rs = r.result();
        System.out.println("Connected and got results");
        System.out.println(rs.toString());
        
      }));
      
    });
  }
  
  
}
