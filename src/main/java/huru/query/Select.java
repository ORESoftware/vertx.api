package huru.query;

import huru.entity.BaseModel;
import huru.entity.Table;
import huru.entity.TableMap;
import huru.util.Utils;

import static huru.query.Base.*;

import java.util.*;
import java.util.stream.Collectors;

public class Select<T extends BaseModel> implements IGetSQL, Cloneable {
  
  private T model;
  private HashSet<TableField> fields = new HashSet<>();
  private Where where;
  private From from;
  private boolean selectAll = false;
  
  public Select(T model) {
    this.model = model;
  }
  
//  public Select(T model, String... s) {
//    this.model = model;
//    this.fields(s);
//  }
  
  public Select(T model, TableField... s) {
    this.model = model;
    this.fields(s);
  }
  
  public Select<T> clone(){
    return this;
  }
  
//  public Select<T> fields(String... s) {
//    this.fields.addAll(Arrays.asList(s));
//    return this.clone();
//  }
  
//  public Select<T> fields(List<String> s) {
//    this.fields.addAll(s);
//    return this;
//  }
  
  public Select<T> fields(TableField... s) {
    this.fields.addAll(Arrays.asList(s));
    return this;
  }
  
  public Select<T> all() {
    this.selectAll = true;
    return this;
  }
  
  public Select<T> from(Table t) {
    this.from = new From(t);
    return this;
  }
  
  public Select<T> where(String t) {
    this.where = new Where(t);
    return this;
  }
  
  public Select<T> where(Condition c) {
    this.where = new Where(c);
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
  
        Collection<String> fields = this.fields.stream()
          .map(v -> {
            
            var tableName = v.getTableName();
            System.out.println("the table name xxx:");
            System.out.println(tableName);
            Table table = TableMap.TableMap.get(tableName);
            System.out.println("the table map:");
            System.out.println(TableMap.TableMap);
            String a = table.alias;
            
            if(v.hasAlias()){
              return String.join("", a,".",v.getDbName()," ","as"," ",v.getAlias());
            }
            
            return String.join("", a, ".", v.getDbName());
          })
          .collect(Collectors.toList());
        
        b.append(
          Utils.join("SELECT", String.join(", ", fields))
        );
      }
      
      
      b.append("\n");
      
      b.append(
        Utils.join(" FROM", this.from.getSQL())
      );
      
      b.append("\n");
      
      if (this.where != null) {
        b.append(" WHERE " + this.where.getSQL());
      }
      
      return b.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
