package huru.entity;

import io.vertx.core.json.JsonObject;
import java.util.Date;

public class BaseEntity <T extends BaseModel> extends JsonObject {
  
  private T model;
  
  public BaseEntity(T m){
    this.model = m;
  }
  
  public void setUpdateInfo(String user){
    this.model.updatedBy = user;
    this.model.updatedAt = new Date();
  }
  
  
  
}
