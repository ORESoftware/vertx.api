package huru.routes;

import io.vertx.ext.web.RoutingContext;

interface IBasicHandler {
  void basicGet(RoutingContext ctx);
  void basicPost(RoutingContext ctx);
  void basicHead(RoutingContext ctx);
  void basicPut(RoutingContext ctx);
  void basicDelete(RoutingContext ctx);
}
