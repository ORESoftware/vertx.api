package huru.entity;

import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

import static huru.entity.InterfaceContainer.IModelHelper;
import static huru.entity.InterfaceContainer.IMapEnum;

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

@interface NotNull {
  boolean value() default false;
}

@interface Type {
  Class<?> value();
}


public class UserModel extends BaseModel {
  
  static Helper helper = new Helper();
  
  private static Map<String, String> getDBToRuntimeMap = null;
  private static Map<String, String> getRuntimeToDBMap = null;
  
  public enum KeyMap implements IMapEnum {
  
    @Type(Integer.class)
    @NotNull
    USER_ID("user_id", "userId"),
    
    @NotNull
    USER_HANDLE("user_handle", "userHandle"),
    
    @NotNull
    USER_EMAIL("user_email", "userEmail");
    
    private String key;
    private String value;
    
    KeyMap(String k, String v) {
      this.key = k;
      this.value = v;
      
    }
    
    @Override
    public String getKey() {
      return this.key;
    }
    
    @Override
    public String getValue() {
      return this.value;
    }
    
    
  }
  
  
  public Map<String, String> getDBToRuntimeMap() {
    
    if (getDBToRuntimeMap != null) {
      return getDBToRuntimeMap;
    }
    
    var ret = new HashMap<String, String>();
    
    for (KeyMap k : KeyMap.values()) {
      ret.put(k.key, k.value);
    }
    
    return getDBToRuntimeMap = ret;
  }
  
  
  public Map<String, String> getRuntimeToDBMap() {
    
    if (getRuntimeToDBMap != null) {
      return getRuntimeToDBMap;
    }
  
    var ret = new HashMap<String, String>();
  
    for (KeyMap k : KeyMap.values()) {
      ret.put(k.value, k.key);
    }
  
    return getRuntimeToDBMap = ret;
  }
  
}
