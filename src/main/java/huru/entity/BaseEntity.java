package huru.entity;

import io.vertx.core.json.JsonObject;
import java.util.Date;

public class BaseEntity <T extends BaseModel> extends JsonObject {
  
  private Class<T> model;
  
  public BaseEntity(Class<T> m){
    this.model = m;
    var b = new BaseEntity<KCClassModel>(KCClassModel.class);
  }
  
  public void setUpdateInfo(String user){
//    this.model.updatedBy = user;
//    this.model.updatedAt = new Date();
  }
  
  public JsonObject toJsonObject(){
    return JsonObject.mapFrom(this.model);
  }
  
  public T toObject(JsonObject v){
    return v.mapTo(this.model);
  }
  
  public Class<T> getEntityType (){
    return this.model;
  }
  
}
