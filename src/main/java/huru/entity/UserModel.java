package huru.entity;


import io.vertx.core.json.JsonObject;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.*;

import static huru.entity.InterfaceContainer.IModelHelper;
import static huru.entity.InterfaceContainer.IMapEnum;

import static huru.entity.Annotations.*;

class Helper implements IModelHelper<UserModel> {
  
  
  @Override
  public void validateForUpdate(UserModel m) {
  
  }
  
  @Override
  public void validateForInsert(UserModel m) {
  
  }
  
  @Override
  public void validate(UserModel m) {
  
  }
  
}



public class UserModel extends BaseModel {
  
  static Helper helper = new Helper();
  
  private static Map<String, String> getDBToRuntimeMap = null;
  private static Map<String, String> getRuntimeToDBMap = null;
  
  @Override
  public String getTableName() {
    return null;
  }
  
  public static class Model extends HashMap<String,Object> {
  
  }
  
  
  public Map<String, String> getDBToRuntimeMap() {
    
    if (getDBToRuntimeMap != null) {
      return getDBToRuntimeMap;
    }
  
    HashMap ret = new HashMap<String, String>();
    
//    for (KeyMap k : KeyMap.values()) {
//      ret.put(k.key, k.value);
//    }
    
    return getDBToRuntimeMap = ret;
  }
  
  
  public Map<String, String> getRuntimeToDBMap() {
    
    if (getRuntimeToDBMap != null) {
      return getRuntimeToDBMap;
    }
  
    HashMap ret = new HashMap<String, String>();
  
//    for (KeyMap k : KeyMap.values()) {
//      ret.put(k.value, k.key);
//    }
  
    return getRuntimeToDBMap = ret;
  }
  
}
