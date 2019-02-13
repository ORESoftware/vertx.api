package huru.query;

import huru.entity.BaseModel;

public class Insert<T extends BaseModel> {
  
  private T model;
  private String table;
  
  public Insert(T model) {
    this.model = model;
  }
}
