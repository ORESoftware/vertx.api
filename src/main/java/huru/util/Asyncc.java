package huru.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Limit {
  private int val;
  private int current = 0;
  
  public Limit(int val){
    this.val = val;
  }
  
  public Limit(){
    this.val = 1;
  }
  
  public int getVal(){
    return this.val;
  }
  
  public int getCurrent(){
    return this.current;
  }
  
  public void increment(){
    this.current++;
  }
  
  public void decrement(){
    this.current--;
  }
  
  public boolean isBelowCapacity(){
    return this.current < this.val;
  }
  
}

class Counter {
  
  private int started = 0;
  private int finished = 0;
  
  void incrementStarted() {
    this.started++;
  }
  
  void incrementFinished() {
    this.finished++;
  }
  
  int getStartedCount() {
    return this.started;
  }
  
  int getFinishedCount() {
    return this.finished;
  }
}

public class Asyncc {
  
  public static interface AsyncCallback {
    //  public void done(Object v);
//  public void fail(Object e);
    public void done(Object e, Object v);
    
  }
  
  public static interface AsyncTask {
    public void run(AsyncCallback cb);
  }
  
  public static interface FinalCallback {
    public void run(Object e, List<Object> v);
  }
  
  
  public static void main() {
    Asyncc.Parallel(Arrays.asList(
      
      v -> {
        v.done(null, null);
      }
    
    ), (e, results) -> {
    
    });
  }
  
  

  
  public static <T, E> void Parallel(List<AsyncTask> tasks, FinalCallback f) {
    
    List<Object> results = new ArrayList<Object>(Collections.nCopies(tasks.size(), 0));
    boolean error = false;
    Counter c = new Counter();
    
    for (int i = 0; i < tasks.size(); i++) {
      
      final int index = i;
      
      tasks.get(i).run((e, v) -> {
        
        if (e != null) {
          f.run(e, null);
          return;
        }
        
        c.incrementFinished();
        results.set(index, v);
        
        if (c.getFinishedCount() == tasks.size()) {
          f.run(null, results);
        }
        
      });
      
    }
    
  }
  
  public static <T, E> void ParallelLimit(int limit,List<AsyncTask> tasks, FinalCallback f) {
    
    Limit lim = new Limit(limit);
    
    List<Object> results = new ArrayList<Object>(Collections.nCopies(tasks.size(), 0));
    Counter c = new Counter();
    
    RunTasksLimit(tasks, results, c, lim, f);
    
  }
  
  private static void RunTasksLimit(List<AsyncTask> tasks, List<Object> results, Counter c, Limit lim, FinalCallback f) {
    
    if (c.getStartedCount() >= tasks.size()){
//      f.run(null, results);
      return;
    }
  
    final int val = c.getStartedCount();
    AsyncTask t = tasks.get(val);
    lim.increment();
    c.incrementStarted();
    
    t.run((e, v) -> {
      
      if (e != null) {
        f.run(e, null);
        return;
      }
      
      results.set(val, v);
      lim.decrement();
      c.incrementFinished();
      
      if (c.getFinishedCount() == tasks.size()) {
        f.run(null, results);
        return;
      }
      
      if(lim.isBelowCapacity()){
        RunTasksLimit(tasks, results, c, lim,f);
      }
      
    });
    
    
    if(c.getStartedCount() >= tasks.size()){
      return;
    }
    
    if(lim.isBelowCapacity()){
      RunTasksLimit(tasks, results, c, lim,f);
    }
    
  }
  
  private static void RunTasksSerially(List<AsyncTask> tasks, List<Object> results, Counter c, FinalCallback f) {
    
    final int startedCount = c.getStartedCount();
    
    if (startedCount >= tasks.size()) {
//      f.run(null, results);
      return;
    }
    
    AsyncTask t = tasks.get(startedCount);
    c.incrementStarted();
    
    t.run((e, v) -> {
      
      if (e != null) {
        f.run(e, null);
        return;
      }
  
      c.incrementFinished();
      results.set(startedCount, v);
      
      if (c.getFinishedCount() == tasks.size()) {
        f.run(null, results);
        return;
      }
      
      RunTasksSerially(tasks, results, c, f);
      
    });
    
  }
  
  public static <T, E> void Series(List<AsyncTask> tasks, FinalCallback f) {
    
    List<Object> results = new ArrayList<Object>(Collections.nCopies(tasks.size(), 0));
    
    boolean error = false;
    Counter c = new Counter();
    
    if (tasks.size() < 1) {
      f.run(null, Collections.emptyList());
      return;
    }
    
    RunTasksSerially(tasks, results, c, f);
  }
}
