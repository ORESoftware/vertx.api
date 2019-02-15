package huru.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

interface IGetValue {
  public Object getDefaultValue();
}



public class Annotations {
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface NotNull {
    String id() default "@NotNullAnnotationId";
    boolean value() default false;
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface ColumnType {
    String id() default "@ColumnTypeAnnotationId";
    Class<?> value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Zoom {
    String id() default "@ZoomAnnotationId";
    int value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface RuntimeType {
    String id() default "@RuntimeTypeAnnotationId";
    Class<?> value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface SetTable {
    String id() default "@SetTableAnnotationId";
    String value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public @interface DefaultValue {
    String id() default "@DefaultValueAnnotationId";
    Class<? extends IGetValue> value();
  }
  
}
