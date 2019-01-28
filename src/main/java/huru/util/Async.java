package huru.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface AsyncCallback {
  //  public void done(Object v);
//  public void fail(Object e);
  public void done(Object e, Object v);
  
}

interface AsyncTask {
  public void run(AsyncCallback cb);
}

interface FinalCallback {
  public void run(Object e, List<Object> v);
}

class Counter {
  
  int val = 0;
  
  void increment() {
     this.val++;
  }
  
  int getVal() {
    return this.val;
  }
}

public class Async {
  
  public void run() {
    Async.Parallel(Arrays.asList(
      
      v -> {
        v.done(null, null);
      }
    
    ), (e, results) -> {
    
    });
    
  }
  
  static <T, E> void Parallel(List<AsyncTask> tasks, FinalCallback f) {
    
    List<Object> results = new ArrayList<Object>();
    boolean error = false;
    Counter c = new Counter();
    
    for (int i = 0; i < tasks.size(); i++) {
      
      final int index = i;
      
      tasks.get(i).run((e, v) -> {
        
        if (e != null) {
          f.run(e, null);
          return;
        }
        
        c.increment();
        results.set(index, v);
        
        if (c.getVal() == tasks.size()) {
          f.run(null, results);
        }
        
      });
      
    }
    
  }
  
  private static void RunTasksSerially(List<AsyncTask> tasks, ArrayList<Object> results, Counter c, FinalCallback f){
    
    if(c.getVal() > tasks.size()){
        f.run(null, results);
        return;
    }
    
    AsyncTask t = tasks.get(c.getVal());
    
    t.run((e,v) -> {
  
      if (e != null) {
        f.run(e, null);
        return;
      }
      
      results.set(c.getVal(), v);
      
      if (c.getVal() == tasks.size()) {
        f.run(null, results);
        return;
      }
  
      c.increment();
      RunTasksSerially(tasks, results, c, f);
      
    });
    
  }
  
  static <T, E> void Series(List<AsyncTask> tasks, FinalCallback f) {
    
    List<Object> results = new ArrayList<Object>();
    boolean error = false;
    Counter c = new Counter();
    
    if(tasks.size() < 1){
      f.run(null, Arrays.asList());
      return;
    }
      
      tasks.get(i).run((e, v) -> {
        
        if (e != null) {
          f.run(e, null);
          return;
        }
        
        c.increment();
        results.set(index, v);
        
        if (c.getVal() == tasks.size()) {
          f.run(null, results);
        }
        
      });
      
    
    
  }
}
