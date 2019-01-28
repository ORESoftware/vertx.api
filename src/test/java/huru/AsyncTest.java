package huru;

import huru.util.Asyncc;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.vertx.ext.unit.Async;
import java.util.Arrays;

@RunWith(VertxUnitRunner.class)
public class AsyncTest {
  

  @Test
  public void test(TestContext tc) {
    
    Async z = tc.async();
  
    Asyncc.Parallel(Arrays.asList(
    
      v -> {
        v.done(null, null);
      }
  
    ), (e, results) -> {
    
      if(e != null){
        z.complete();
      }
      else{
        z.complete();
      }
      
    });
  }
  
  @Test
  public void testSeries(TestContext tc) {
    
    Async z = tc.async();
  
    Asyncc.Series(Arrays.asList(
      
      v -> {
        v.done(null, null);
      },
      
      v -> {
        v.done(null, null);
      }
    
    ), (e, results) -> {
      
      if(e != null){
        z.complete();
      }
      else{
        z.complete();
      }
      
    });
  }
  
}
