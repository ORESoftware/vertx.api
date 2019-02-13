package huru.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface SetAnnotation {
  int value();
}


public class AnnotationInjector {
  public static void inject(Object instance) {
    Field[] fields = instance.getClass().getDeclaredFields();
    for (Field field : fields) {
      
      if (field.isAnnotationPresent(SetAnnotation.class)) {
        SetAnnotation set = field.getAnnotation(SetAnnotation.class);
        field.setAccessible(true); // should work on private fields
        try {
          field.set(instance, set.value());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
