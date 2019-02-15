package huru.entity;

import java.lang.annotation.Annotation;
import java.util.Map;

public class InterfaceContainer {
  
  public interface IValue{
    public Object value();
  }
  
  public interface IMapEnum {
    String getKey();
    String getValue();
    boolean hasAnnotation(Annotation a);
//    Map<String,String> getDBToRuntimeMap();
//    Map<String,String> getRuntimeToDBMap();
  
  }
  
  public interface IModelHelper<T extends BaseModel> {
    
    void validateForUpdate(T m);
    void validateForInsert(T m);
    void validate(T m);
    
  }
}


