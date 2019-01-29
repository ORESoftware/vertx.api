package huru.util;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Example {
  public static int add(int x, int y) {
    return x + y;
  }
  
  public static <T>  Function partial(BiFunction f, T x) {
    return (y) -> f.apply(x, y);
  }
  
  public static void main(String[] args) {
//    Function adder = partial(Example::add, 5);
//    System.out.println(adder.apply(2)); // 7
  }
}


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
