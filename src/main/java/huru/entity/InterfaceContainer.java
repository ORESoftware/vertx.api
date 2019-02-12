package huru.entity;

import java.util.Map;

public class InterfaceContainer {
  
  public interface IMapEnum {
    String getKey();
    String getValue();
//    Map<String,String> getDBToRuntimeMap();
//    Map<String,String> getRuntimeToDBMap();
  
  }
  
  public interface IModelHelper<T extends BaseModel> {
    
    void validateForUpdate(T m);
    void validateForInsert(T m);
    void validate(T m);
    
  }
}


