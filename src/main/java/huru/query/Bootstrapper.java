package huru.query;

import huru.entity.BaseModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import static huru.entity.Annotations.*;

public class Bootstrapper {
  
  public static List<Class<? extends Annotation>> annots = Arrays.asList(
    NotNull.class,
    ColumnType.class,
    RuntimeType.class,
    DefaultValue.class
  );
  
  public static <T extends BaseModel> QueryBuilder<T> bootstrap(Class<T> k, Object... args) {
    
    try {
      
      String tableName = k.getAnnotation(SetTable.class).value();
      
      System.out.println("TABLE NAME:");
      System.out.println(tableName);
      
      Constructor<?> c = k.getConstructor();
      T v = (T) c.newInstance(args);
      
      Field[] fields = k.getDeclaredFields();
      
      for (Field field : fields) {
        
        if (Modifier.isStatic(field.getModifiers())) {
          continue;
        }
        
        Class<?> fieldClass = field.getType();
        
        if (TableField.class != fieldClass) {
          continue;
        }
        
        TableField tf = (TableField) field.get(v);
        System.out.println("Setting table name..");
        System.out.println(tableName);
        tf.setTableName(tableName);

        for(Class<? extends Annotation> x: Bootstrapper.annots){
          if (field.isAnnotationPresent(x)) {
            Annotation a =  field.getAnnotation(x);
            
              Method idMeth = a.getClass().getDeclaredMethod("id");
              String id = (String)idMeth.invoke(a);
              
              Method m = a.getClass().getDeclaredMethod("value");
              Object val = m.invoke(a);
  
            tf.addAnnotation(id, val);
            
          }
        }
        
//        if (field.isAnnotationPresent(NotNull.class)) {
//          System.out.println("Not null annot is present");
//          String id = field.getAnnotation(NotNull.class).id();
//          Object value = field.getAnnotation(NotNull.class).value();
//          tf.addAnnotation(id, value);
//        }
//
//        if (field.isAnnotationPresent(RuntimeType.class)) {
//          System.out.println("Runtime Type annot is present");
//          String id = field.getAnnotation(RuntimeType.class).id();
//          Object value = field.getAnnotation(RuntimeType.class).value();
//          tf.addAnnotation(id, value);
//        }
//
//        if (field.isAnnotationPresent(DefaultValue.class)) {
//          System.out.println("Default value annot is present");
//          String id = field.getAnnotation(DefaultValue.class).id();
//          Object value = field.getAnnotation(DefaultValue.class).value();
//          tf.addAnnotation(id, value);
//        }
//
//        if (field.isAnnotationPresent(ColumnType.class)) {
//          System.out.println("Column type annot is present");
//          String id = field.getAnnotation(ColumnType.class).id();
//          Object value = field.getAnnotation(ColumnType.class).value();
//          tf.addAnnotation(id, value);
//        }
  
        System.out.println(tf);
        
      }
      
      return new QueryBuilder<T>(v);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    
    
  }
}
