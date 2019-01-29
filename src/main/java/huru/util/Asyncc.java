package huru.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


interface One {
  public void run();
}

interface Two {
  public void run();
}


class Foo {
  
  public void zoom(One v) {
  
  }
  
  public void run() {
    new Foo().zoom(() -> {
    
    });
  }
}

class AT implements Asyncc.AsyncCallback {
  
  @Override
  public void done(Object e, Object v) {
  
  }
  
}


class Limit {
  private int val;
  private int current = 0;
  
  public Limit(int val) {
    this.val = val;
  }
  
  public Limit() {
    this.val = 1;
  }
  
  public int getVal() {
    return this.val;
  }
  
  public int getCurrent() {
    return this.current;
  }
  
  public void increment() {
    this.current++;
  }
  
  public void decrement() {
    this.current--;
  }
  
  public boolean isBelowCapacity() {
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
  
  public static interface AsyncCallback<T, E> {
    //  public void done(Object v);
//  public void fail(Object e);
    public void done(E e, T v);
  }
  
  public static interface AsyncTask<T, E> {
    public void run(AsyncCallback<T, E> cb);
  }

//  public static interface FinalCallback {
//    public void run(Object e, List<Object> v);
//  }
  
  public static AsyncTask zoom() {
    return v -> {
      v.done(null, null);
    };
  }
  
  
  public static void main() {
    Asyncc.Series(Arrays.asList(
      Asyncc.fParallel(Arrays.asList(
        z -> {
          z.done(null, null);
        },
        Asyncc.zoom()
        )
      
      )), (e, results) -> {
      
    });
    
    Asyncc.Parallel(Arrays.asList(
      
      v -> {
        v.done(null, null);
      },
      
      Asyncc.zoom()
    
    ), (e, results) -> {
    
    });
  }
  
  public static <T, E> AsyncTask fParallel(List<AsyncTask<T,E>> tasks) {
    return cb -> {
      Asyncc.<T,E>Parallel(tasks, cb);
    };
  }
  
  public static <T, E> AsyncTask fSeries(List<AsyncTask> tasks) {
    return cb -> {
      Asyncc.Series(tasks, cb);
    };
  }
  
  
  public static <T, E> void Parallel(List<AsyncTask<T,E>> tasks, AsyncCallback f) {
    
    List<Object> results = new ArrayList<Object>(Collections.nCopies(tasks.size(), 0));
    boolean error = false;
    Counter c = new Counter();
    
    for (int i = 0; i < tasks.size(); i++) {
      
      final int index = i;
      
      tasks.get(i).run((e, v) -> {
        
        if (e != null) {
          f.done(e, null);
          return;
        }
        
        c.incrementFinished();
        results.set(index, v);
        
        if (c.getFinishedCount() == tasks.size()) {
          f.done(null, results);
        }
        
      });
      
    }
    
  }
  
  public static <T, E> void ParallelLimit(int limit, List<AsyncTask> tasks, AsyncCallback f) {
    
    Limit lim = new Limit(limit);
    
    List<Object> results = new ArrayList<Object>(Collections.nCopies(tasks.size(), 0));
    Counter c = new Counter();
    
    RunTasksLimit(tasks, results, c, lim, f);
    
  }
  
  private static void RunTasksLimit(List<AsyncTask> tasks, List<Object> results, Counter c, Limit lim, AsyncCallback f) {
    
    if (c.getStartedCount() >= tasks.size()) {
//      f.run(null, results);
      return;
    }
    
    final int val = c.getStartedCount();
    AsyncTask t = tasks.get(val);
    lim.increment();
    c.incrementStarted();
    
    t.run((e, v) -> {
      
      if (e != null) {
        f.done(e, null);
        return;
      }
      
      results.set(val, v);
      lim.decrement();
      c.incrementFinished();
      
      if (c.getFinishedCount() == tasks.size()) {
        f.done(null, results);
        return;
      }
      
      if (lim.isBelowCapacity()) {
        RunTasksLimit(tasks, results, c, lim, f);
      }
      
    });
    
    
    if (c.getStartedCount() >= tasks.size()) {
      return;
    }
    
    if (lim.isBelowCapacity()) {
      RunTasksLimit(tasks, results, c, lim, f);
    }
    
  }
  
  private static void RunTasksSerially(List<AsyncTask> tasks, List<Object> results, Counter c, AsyncCallback f) {
    
    final int startedCount = c.getStartedCount();
    
    if (startedCount >= tasks.size()) {
//      f.run(null, results);
      return;
    }
    
    AsyncTask t = tasks.get(startedCount);
    c.incrementStarted();
    
    t.run((e, v) -> {
      
      if (e != null) {
        f.done(e, null);
        return;
      }
      
      c.incrementFinished();
      results.set(startedCount, v);
      
      if (c.getFinishedCount() == tasks.size()) {
        f.done(null, results);
        return;
      }
      
      RunTasksSerially(tasks, results, c, f);
      
    });
    
  }
  
  public static <T, E> void Series(List<AsyncTask> tasks, AsyncCallback f) {
    
    List<Object> results = new ArrayList<Object>(Collections.nCopies(tasks.size(), 0));
    
    boolean error = false;
    Counter c = new Counter();
    
    if (tasks.size() < 1) {
      f.done(null, Collections.emptyList());
      return;
    }
    
    RunTasksSerially(tasks, results, c, f);
  }
}
