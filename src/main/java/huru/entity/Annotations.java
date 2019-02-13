package huru.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Annotations {
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface NotNull {
    boolean value() default false;
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface ColumnType {
    Class<?> value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface RuntimeType {
    Class<?> value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface SetTable {
    String table();
  }
  
}
