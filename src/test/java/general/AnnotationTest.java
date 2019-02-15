package general;

import huru.entity.AnnotationInjector;
import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


@Retention(RetentionPolicy.RUNTIME)
@interface SetTable {
  String table();
}

class Star {
  public String foo = "";
  public String toString(){
    return "<Star> : " + this.foo;
  }
}

class Bar {
  
  @SetTable(table = "xxx")
  public Star s = new Star();
  public String toString(){
    return "<Bar> : " + this.s.toString();
  }
}

class AnnotationInjector1 {
  
  static <T> void inspect(Class<T> klazz) {
    
//    AnnotationInjector1.class.getDeclaredField("foo");
    
    Field[] fields = klazz.getDeclaredFields();
    System.out.printf("%d fields:%n", fields.length);
    for (Field field : fields) {
      
      Class<?> c = field.getType();
      
//      field.get(klazz);
      
      if(Modifier.isStatic(field.getModifiers())){
      
      }
      
      System.out.printf("%s %s %s%n",
        Modifier.toString(field.getModifiers()),
        field.getType().getSimpleName(),
        field.getName()
      );
    }
  }
  
  public static void inject(Object instance) {
    Field[] fields = instance.getClass().getDeclaredFields();
    
    System.out.println("All fields");
    System.out.println(fields);
    
    for (Field field : fields) {
      
      if (field.isAnnotationPresent(SetTable.class)) {
        
        System.out.println("It has the annotation: " + field);
        SetTable set = field.getAnnotation(SetTable.class);
        field.setAccessible(true); // should work on private fields
        
        try {
          Star x = (Star)field.get(instance);
          x.foo = "whatever";
//          field.set(instance, set.table());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      else{
        System.out.println("It does not have the annotation: " + field);
      }
    }
  }
}

public class AnnotationTest {
  
  @Test
  public void JSON() {
  
    Bar b = new Bar();
    AnnotationInjector1.inject(b);
    System.out.println(b.toString());
  }
  
  
}
