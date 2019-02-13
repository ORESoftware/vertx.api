package huru.query;

import huru.entity.BaseModel;
import huru.entity.TableField;
import huru.util.Utils;

import static huru.query.Base.*;

import java.util.*;
import java.util.stream.Collectors;

public class Select<T extends BaseModel> implements IGetSQL {
  
  private T model;
  private HashSet<String> fields = new HashSet<>();
  private Where where;
  private From from;
  private boolean selectAll = false;
  
  public Select(T model) {
    this.model = model;
  }
  
  public Select(T model, String... s) {
    this.model = model;
    this.fields(s);
  }
  
  public Select(T model, TableField... s) {
    this.model = model;
    this.fields(s);
  }
  
  public Select<T> fields(String... s) {
    this.fields.addAll(Arrays.asList(s));
    return this;
  }
  
  public Select<T> fields(TableField... s) {
    Collection<String> fields = Arrays.asList(s)
      .stream()
      .map(v -> v.getDbName())
      .collect(Collectors.toList());
    
    this.fields.addAll(fields);
    return this;
  }
  
  public Select<T> all() {
    this.selectAll = true;
    return this;
  }
  
  public Select<T> from(String t) {
    this.from = new From(t);
    return this;
  }
  
  public Select<T> where(String t) {
    this.where = new Where(t);
    return this;
  }
  
  @Override
  public String getSQL() {
    
    try {
      
      if (this.from == null) {
        throw new Exception("Missing table property in query builder.");
      }
      
      StringBuilder b = new StringBuilder();
      
      if (this.selectAll) {
        b.append(
          Utils.join("SELECT", "*")
        );
      } else {
        
        
        if (this.fields.isEmpty()) {
          throw new Exception("No fields selected for select query.");
        }
        
        b.append(
          Utils.join("SELECT", String.join(",", this.fields))
        );
      }
      
      
      b.append("\n");
      
      b.append(
        Utils.join("FROM", this.from.simple)
      );
      
      b.append("\n");
      
      if (this.where != null) {
        b.append(this.where.getSQL());
      }
      
      return b.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
