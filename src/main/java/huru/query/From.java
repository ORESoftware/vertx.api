package huru.query;

import huru.entity.Table;

public class From implements Base.IGetSQL {

//  String simple;
  
  Table table;
  
//  public From(String simple){
//    this.simple = simple;
//  }
  
  public From(Table t){
    this.table = t;
  }
  
  @Override
  public String getSQL() {
    return String.join(" ",this.table.getName(), this.table.getAlias());
  }
}
