package huru.query;

import huru.entity.BaseModel;


public class Update<T extends BaseModel> {
  
  private T model;
  private String table;
  
  public Update(T model) {
    this.model = model;
  }
}
