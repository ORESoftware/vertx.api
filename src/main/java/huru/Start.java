package huru;

import huru.config.SpringConfiguration;
import huru.verticle.MainVerticle;
import huru.verticle.SpringVerticle;
import io.vertx.core.Vertx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Runner for the vertx-spring sample
 *
 */
public class Start {
  
  public static void main( String[] args ) {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new SpringVerticle(context));
    vertx.deployVerticle(new MainVerticle());
    System.out.println("Deployment done");
  }
  
}
