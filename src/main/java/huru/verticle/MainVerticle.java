package huru.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.SQLRowStream;
import io.vertx.ext.web.Router;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.RoutingContext;
import org.apache.log4j.Logger;
import io.vertx.core.Future;


public class MainVerticle extends AbstractVerticle {
  
  static Logger log = Logger.getLogger(MainVerticle.class);
  
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
  
  private void sendMessage(RoutingContext rc) {
  
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
    
    final Router router = Router.router(vertx);
    
    final EventBus eventBus = vertx.eventBus();
    
    eventBus.consumer("address", receivedMessage -> {
      log.debug("Received message: " + receivedMessage.body());
      receivedMessage.reply("my reply");
    });
    
    router.route("/").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      response.putHeader("content-type", "text/html").end("<h1>Hello from non-clustered messenger example!</h1>");
    });
    
    router.post("/send/:message").handler(this::sendMessage);
    
    
    JsonObject config = new JsonObject()
      .put("username", "postgres")
      .put("password", "postgres")
      .put("database", "oleg")
      .put("host", "localhost")
      .put("port", 5432);
    
    SQLClient client = PostgreSQLClient.createShared(vertx, config);
    
    
    client.getConnection(res -> {
      
      if (!res.succeeded()) {
        System.out.println("Here comes the cause:");
        System.out.println(res.cause());
        return;
      }
      
      
      SQLConnection connection = res.result();
      
      connection.queryStream("SELECT * FROM large_table; SELECT * FROM other_table", stream -> {
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
            });
        }
      });
      
      
      String v = "";
      
      connection.query("SELECT * FROM account", results -> {
        
        if (!results.succeeded()) {
          throw new Error("Could not connect to table.");
        }
        
        ResultSet rs = results.result();
        System.out.println("Connected and got results");
        System.out.println(rs.toString());
        
      });
      
    });
    
    int port = 3005;
    
    vertx.createHttpServer()
      .requestHandler(req -> {
        
        if (req.method() == HttpMethod.GET) {
          
          req.response().setChunked(true);
          
          if (req.path().equals("/products")) {
            
            vertx.eventBus().<String>send(SpringVerticle.ALL_PRODUCTS_ADDRESS, "", result -> {
              if (result.succeeded()) {
                req.response().setStatusCode(200).write(result.result().body()).end();
              } else {
                req.response().setStatusCode(500).write(result.cause().toString()).end();
              }
            });
            
            return;
          }
          
          req.response().setStatusCode(200).write("Hello from vert.x").end();
          return;
          
        }
        
        req.response()
          .putHeader("content-type", "text/plain")
          .end("Hello Vert.x!");
        
        
      })
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
