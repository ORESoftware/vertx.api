package huru;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import org.apache.log4j.Logger;


public class MainVerticle extends AbstractVerticle {
  
  static Logger log = Logger.getLogger(MainVerticle.class);
  
  @Override
  public void start() {

//    JsonObject config = new JsonObject()
//      .put("url", "jdbc:hsqldb:mem:test?shutdown=true")
//      .put("driver_class", "org.hsqldb.jdbcDriver")
//      .put("max_pool_size", 30);
//
//    SQLClient client = JDBCClient.createShared(vertx, config);
    
    
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
      
//      String v = `
//      `;
      
      connection.query("SELECT * FROM account", results -> {
        
        if (!results.succeeded()) {
          throw new Error("Could not connect to table.");
        }
        
        ResultSet rs = results.result();
        System.out.println("Connected and got results");
        System.out.println(rs.toString());
        
      });
      
    });
    
    vertx.createHttpServer()
      .requestHandler(req -> req.response().end("Hello Vert.x!"))
      .listen(8080);
  }
  
}
