package huru.util;

import java.util.*;
import static java.util.Arrays.asList;



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
  
  public static class KeyValue<V> {
    
    public String key;
    public V value;
    
    public KeyValue(String key, V value) {
      this.key = key;
      this.value = value;
    }
  }
  
  public static interface Mapper<V, T, E> {
    public void map(KeyValue<V> v, AsyncCallback<T, E> cb);
  }
  
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
    Asyncc.Series(asList(
      Asyncc.zoom(),
      Asyncc.Parallel(asList(
        z -> {
          z.done(null, null);
        },
        Asyncc.zoom()
        )
      )),
      (e, results) -> {
      
      });
    
    Asyncc.Parallel(asList(
      
      v -> {
        v.done(null, null);
      },
      
      Asyncc.zoom()
      
      ),
      (e, results) -> {
      
      });
  }
  
  public static <T, E> AsyncTask<List<T>, E> Parallel(AsyncTask<T,E>... tasks) {
    return cb -> Asyncc.<T, E>Parallel(List.of(tasks), cb);
  }
  
  public static <T, E> AsyncTask<List<T>, E> Series(AsyncTask<T,E>... tasks) {
    return cb -> Asyncc.<T, E>Parallel(List.of(tasks), cb);
  }
  
  public static <T, E> AsyncTask<List<T>, E> Parallel(List<AsyncTask> tasks) {
    return cb -> Asyncc.<T, E>Parallel(tasks, cb);
  }
  
  public static <T, E> AsyncTask<List<T>,E> Series(List<AsyncTask<T, E>> tasks) {
    return cb -> Asyncc.<T, E>Series(tasks, cb);
  }
  
  public static <T, E> AsyncTask<Map<String,T>, E> Parallel(Map<String, AsyncTask<T,E>> tasks) {
    return cb -> Asyncc.<T, E>Parallel(tasks, cb);
  }
  
  public static <T, E> AsyncTask<Map<String,T>, E> Series(Map<String, AsyncTask<T,E>> tasks) {
    return cb -> Asyncc.<T, E>Series(tasks, cb);
  }
  
  public static <V, T, E> void Map(List<?> items, Mapper<V, T, E> m, AsyncCallback<List<T>, E> f) {
    
    List<T> results = new ArrayList<T>(Collections.<T>nCopies(items.size(), null));
    Counter c = new Counter();
    
    for (int i = 0; i < items.size(); i++) {
      
      final int val = c.getStartedCount();
      c.incrementStarted();
      
      KeyValue kv = new KeyValue(null, items.get(i));
      
      m.map(kv, (e, v) -> {
        
        if (e != null) {
          f.done((E) e, Collections.emptyList());  // List.of()?
          return;
        }
        
        c.incrementFinished();
        results.set(val, (T) v);
        
        if (c.getFinishedCount() == items.size()) {
          f.done(null, results);
        }
        
      });
      
    }
    
  }
  
  public static <T, E> void Series(
    Map<String, AsyncTask<T, E>> tasks,
    AsyncCallback<Map<String, T>, E> f
  ) {
    
    Map<String, T> results = new HashMap<>();
    boolean error = false;
    Counter c = new Counter();
    
    Iterator<Map.Entry<String, AsyncTask<T,E>>> entries = tasks.entrySet().iterator();
    Limit lim = new Limit(1);
    
    Asyncc.<T,E>RunMapLimit(entries, tasks, new HashMap<>(), c, lim, f);
    
  }
  
  public static <T, E> void ParallelLimit(
    int limit,
    Map<String, AsyncTask<T, E>> tasks,
    AsyncCallback<Map<String, T>, E> f
  ) {
    
    Map<String, T> results = new HashMap<>();
    boolean error = false;
    Counter c = new Counter();
    
    Iterator<Map.Entry<String, AsyncTask<T,E>>> entries = tasks.entrySet().iterator();
    Limit lim = new Limit(limit);
    
    Asyncc.<T,E>RunMapLimit(entries, tasks, new HashMap<>(), c, lim, f);
    
  }
  
  public static <T, E> void Parallel(Map<String, AsyncTask<T, E>> tasks, AsyncCallback<Map<String, T>, E> f) {
    
    Map<String, T> results = new HashMap<>();
    boolean error = false;
    Counter c = new Counter();
    
    for (Map.Entry<String, AsyncTask<T, E>> entry : tasks.entrySet()) {
      
      final String key = entry.getKey();
      
      entry.getValue().run((e, v) -> {
        
        if (e != null) {
          f.done((E) e, Map.of());
          return;
        }
        
        c.incrementFinished();
        results.put(key, v);
        
        if (c.getFinishedCount() == tasks.size()) {
          f.done(null, results);
        }
        
      });
      
    }
    
  }
  
  @SuppressWarnings("Duplicates")
  public static <T, E> void Parallel(List<AsyncTask> tasks, AsyncCallback<List<T>, E> f) {
    
    List<T> results = new ArrayList<T>(Collections.<T>nCopies(tasks.size(), null));
    boolean error = false;
    Counter c = new Counter();
    
    for (int i = 0; i < tasks.size(); i++) {
      
      final int index = i;
      
      tasks.get(i).run((e, v) -> {
        
        if (e != null) {
          f.done((E) e, Collections.emptyList());
          return;
        }
        
        c.incrementFinished();
        results.set(index, (T) v);
        
        if (c.getFinishedCount() == tasks.size()) {
          f.done(null, results);
        }
        
      });
      
    }
    
  }
  
  
  public static <T, E> void ParallelLimit(
    int limit,
    List<AsyncTask> tasks,
    AsyncCallback<List<T>, E> f
  ) {
    
    Limit lim = new Limit(limit);
    
    List<T> results = new ArrayList<T>(Collections.<T>nCopies(tasks.size(), null));
    Counter c = new Counter();
    
    RunTasksLimit(tasks, results, c, lim, f);
    
  }
  
  private static <T, E> void RunMapLimit(
    Iterator<Map.Entry<String, AsyncTask<T,E>>> entries,
    Map<String, AsyncTask<T, E>> m,
    Map<String, T> results,
    Counter c,
    Limit lim,
    AsyncCallback<Map<String, T>, E> f
  ) {
    
    if (c.getStartedCount() >= m.size()) {
      return;
    }
    
    if (!entries.hasNext()) {
      return;
    }
    
    Map.Entry<String, AsyncTask<T,E>> entry =  entries.next();
    String key = entry.getKey();
    AsyncTask t = m.get(key);
    lim.increment();
    c.incrementStarted();
    
    t.<T, E>run((e, v) -> {
      
      if (e != null) {
        f.done((E) e, Map.of());
        return;
      }
      
      results.put(key, (T) v);
      lim.decrement();
      c.incrementFinished();
      
      if (c.getFinishedCount() == m.size()) {
        f.done(null, results);
        return;
      }
      
      if (lim.isBelowCapacity()) {
        RunMapLimit(entries, m, results, c, lim, f);
      }
      
    });
    
    
    if (c.getStartedCount() >= m.size()) {
      return;
    }
    
    if (lim.isBelowCapacity()) {
      RunMapLimit(entries, m, results, c, lim, f);
    }
    
  }
  
  private static <T, E> void RunTasksLimit(
    List<AsyncTask> tasks,
    List<T> results,
    Counter c,
    Limit lim,
    AsyncCallback<List<T>, E> f
  ) {
    
    if (c.getStartedCount() >= tasks.size()) {
//      f.run(null, results);
      return;
    }
    
    final int val = c.getStartedCount();
    AsyncTask t = tasks.get(val);
    lim.increment();
    c.incrementStarted();
    
    t.<T, E>run((e, v) -> {
      
      if (e != null) {
        f.done((E) e, Collections.emptyList());
        return;
      }
      
      results.set(val, (T) v);
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
  
  private static <T, E> void RunTasksSerially(
    List<AsyncTask<T, E>> tasks,
    List<T> results,
    Counter c,
    AsyncCallback<List<T>, E> f
  ) {
    
    final int startedCount = c.getStartedCount();
    
    if (startedCount >= tasks.size()) {
//      f.run(null, results);
      return;
    }
    
    AsyncTask t = tasks.get(startedCount);
    c.incrementStarted();
    
    t.run((e, v) -> {
      
      if (e != null) {
        f.done((E)e, Collections.emptyList());
        return;
      }
      
      c.incrementFinished();
      results.set(startedCount, (T)v);
      
      if (c.getFinishedCount() == tasks.size()) {
        f.done(null, results);
        return;
      }
      
      RunTasksSerially(tasks, results, c, f);
      
    });
    
  }
  
  public static <T, E> void Series(
    List<AsyncTask<T, E>> tasks,
    AsyncCallback<List<T>, E> f
  ) {
    
    List<T> results = new ArrayList<T>(Collections.nCopies(tasks.size(), null));
    
    boolean error = false;
    Counter c = new Counter();
    
    if (tasks.size() < 1) {
      f.done(null, Collections.emptyList());
      return;
    }
    
    Asyncc.<T, E>RunTasksSerially(tasks, results, c, f);
  }
}
