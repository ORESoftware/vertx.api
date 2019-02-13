package huru.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Annotations {
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface NotNull {
    boolean value() default false;
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Type {
    Class<?> value();
  }
  
}
