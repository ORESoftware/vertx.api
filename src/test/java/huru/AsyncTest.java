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
import static java.util.Arrays.asList;


import java.util.Arrays;

@RunWith(VertxUnitRunner.class)
public class AsyncTest {
  
  
  @Test
  public void testMap(TestContext tc) {
    
    Async z = tc.async();
    
    Asyncc.<Integer, Object, Object>Map(asList(1, 2, 3),
      
      (kv, cb) -> {
      
        cb.done(null, kv.value + 2);
      
      },
      (e, results) -> {
      
        if (e != null) {
          z.complete();
        } else {
          z.complete();
        }
        
      });
  }
  
  @Test
  public void testParallel(TestContext tc) {
    
    Async z = tc.async();
    
    Asyncc.Parallel(asList(
      
      v -> {
        v.done(null, null);
      }
    
    ), (e, results) -> {
      
      if (e != null) {
        z.complete();
      } else {
        z.complete();
      }
      
    });
  }
  
  @Test
  public void testParallelLimit(TestContext tc) {
    
    Async z = tc.async();
    
    Asyncc.ParallelLimit(4, asList(
      
      v -> {
        v.done(null, null);
      },
      
      v -> {
        v.done(null, null);
      },
      
      v -> {
        
        new Thread(() -> {
          try {
            Thread.sleep(3);
          } catch (Exception e) {
            System.out.println(e);
          }
          v.done(null, null);
        })
          .start();
      },
      
      v -> {
        new Thread(() -> {
          try {
            Thread.sleep(3);
          } catch (Exception e) {
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
          try {
            Thread.sleep(3);
          } catch (Exception e) {
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
      
      if (e != null) {
        z.complete();
      } else {
        z.complete();
      }
      
    });
  }
  
  @Test
  public void testSeries(TestContext tc) {
    
    Async z = tc.async();
    
    Asyncc.Series(asList(
      
      v -> {
        new Thread(() -> {
          try {
            Thread.sleep(500);
          } catch (Exception e) {
            System.out.println(e);
          }
          
          v.done(null, null);
        })
          .start();
      },
  
      v -> {
        new Thread(() -> {
          try {
            Thread.sleep(500);
          } catch (Exception e) {
            System.out.println(e);
          }
      
          v.done(null, null);
        })
          .start();
      },
  
      v -> {
        new Thread(() -> {
          try {
            Thread.sleep(500);
          } catch (Exception e) {
            System.out.println(e);
          }
          
          v.done(null, null);
        })
          .start();
      }
    
    ), (e, results) -> {
      
      if (e != null) {
        z.complete();
      } else {
        z.complete();
      }
      
    });
  }
  
}
