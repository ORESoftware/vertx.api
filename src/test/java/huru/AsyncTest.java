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
  public void testParallel(TestContext tc) {
    
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
  public void testParallelLimit(TestContext tc) {
    
    Async z = tc.async();
    
    Asyncc.ParallelLimit(4, Arrays.asList(
      
      v -> {
        v.done(null, null);
      },
  
      v -> {
        v.done(null, null);
      },
  
      v -> {
  
        new Thread(() -> {
          try{
            Thread.sleep(3);
          }
          catch(Exception e){
            System.out.println(e);
          }
          v.done(null, null);
        })
          .start();
      },
  
      v -> {
       new Thread(() -> {
         try{
           Thread.sleep(3);
         }
         catch(Exception e){
           System.out.println(e);
         }
           v.done(null, null);
         })
         .start();
      },
  
      v -> {
        v.done(null, null);
      },
  
      v -> {
        v.done(null, null);
      },
  
      v -> {
        new Thread(() -> {
          try{
            Thread.sleep(3);
          }
          catch(Exception e){
            System.out.println(e);
          }
         
          v.done(null, null);
        })
          .start();
      },
  
      v -> {
        v.done(null, null);
      }
    
    ), (e, results) -> {
      
      System.out.println("DDDDDDDAMN");
      
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
        new Thread(() -> {
          try{
            Thread.sleep(3000);
          }
          catch(Exception e){
            System.out.println(e);
          }
    
          v.done(null, null);
        })
          .start();
      },
      
      v -> {
        new Thread(() -> {
          try{
            Thread.sleep(3000);
          }
          catch(Exception e){
            System.out.println(e);
          }
    
          v.done(null, null);
        })
          .start();
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
