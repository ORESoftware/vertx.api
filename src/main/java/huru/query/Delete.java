package huru.query;

import huru.entity.BaseModel;

public class Delete<T extends BaseModel> {
  
  private T model;
  private String table;
  
  
  public Delete(T model) {
    this.model = model;
  }
}
