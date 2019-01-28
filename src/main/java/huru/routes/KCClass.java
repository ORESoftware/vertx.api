package huru.routes;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;

public class KCClass implements Handler<RoutingContext> {
  
  private Map<String, JsonObject> products = new HashMap<>();
  private SQLClient client;
  
  public KCClass(SQLClient client) {
    super();
    this.client = client;
  }
  
  @Override
  public void handle(RoutingContext ctx) {
    switch (ctx.request().method()) {
      case GET:
        handleAddProduct(ctx);
        break;
      case PUT:
        handleAddProduct(ctx);
        break;
      default:
        ctx.fail(new Exception("No method matched"));
    }
  }
  
  private void handleGetProduct(RoutingContext ctx) {
    
    String productID = ctx.request().getParam("productID");
    HttpServerResponse response = ctx.response();
    if (productID == null) {
      sendError(400, response);
      return;
    }
    
    JsonObject product = products.get(productID);
    if (product == null) {
      sendError(404, response);
    } else {
      response.putHeader("content-type", "application/json").end(product.encodePrettily());
    }
    
  }
  
  private void handleAddProduct(RoutingContext ctx) {
    String productID = ctx.request().getParam("productID");
    HttpServerResponse response = ctx.response();
    if (productID == null) {
      sendError(400, response);
    } else {
      JsonObject product = ctx.getBodyAsJson();
      if (product == null) {
        sendError(400, response);
      } else {
        products.put(productID, product);
        response.end();
      }
    }
  }
  
  private void handleListProducts(RoutingContext ctx) {
    JsonArray arr = new JsonArray();
    products.forEach((k, v) -> arr.add(v));
    ctx.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
  }
  
  private void sendError(int statusCode, HttpServerResponse response) {
    response.setStatusCode(statusCode).end();
  }
  
  private void setUpInitialData() {
    addProduct(new JsonObject().put("id", "prod3568").put("name", "Egg Whisk").put("price", 3.99).put("weight", 150));
    addProduct(new JsonObject().put("id", "prod7340").put("name", "Tea Cosy").put("price", 5.99).put("weight", 100));
    addProduct(new JsonObject().put("id", "prod8643").put("name", "Spatula").put("price", 1.00).put("weight", 80));
  }
  
  private void addProduct(JsonObject product) {
    products.put(product.getString("id"), product);
  }
}
