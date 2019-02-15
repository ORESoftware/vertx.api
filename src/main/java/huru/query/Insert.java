package huru.query;

import huru.entity.BaseModel;
import huru.entity.Table;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
