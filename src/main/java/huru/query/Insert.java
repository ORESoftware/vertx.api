package huru.query;

import huru.entity.BaseModel;

import java.util.Arrays;
import java.util.HashSet;

public class Insert<T extends BaseModel> {
  
  private T model;
  private Table table;
  private HashSet<InsertionValue> values = new HashSet<>();
  
  public Insert(T model) {
    this.model = model;
  }
  
  public Insert<T> into(Table t){
    this.table =t;
    return this;
  }
  
  public Insert<T> values(InsertionValue... values){
     this.values.addAll(Arrays.asList(values));
     return this;
  }
}
