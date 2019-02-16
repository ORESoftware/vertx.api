package huru.entity;

import java.util.Date;
import static huru.entity.InterfaceContainer.*;

public abstract class BaseModel implements IModelHelper {
  
  private String createdBy;
  private Date createdAt;
  private Date updatedAt;
  private String updatedBy;
  
  public BaseModel(){
  
  }
  
  public Date getCreatedAt() {
    return createdAt;
  }
  
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
  
  
  public Date getUpdatedAt() {
    return updatedAt;
  }
  
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
  
  
  public String getCreatedBy() {
    return createdBy;
  }
  
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }
  
  
  public String getUpdatedBy() {
    return updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }
  
  public BaseModel call(){
    AnnotationInjector.inject(this);
    return this;
  }
  
  abstract public String getTableName();
  
}
