package huru.query;

import huru.entity.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class From implements Base.IGetSQL {

  //  String simple;
  
  private AbstractJoin join;
  
  //  public From(String simple){
  //    this.simple = simple;
  //  }
  
  public From(Table... t){
    this.join = Join.Outer(t);
  }
  
  public From(AbstractJoin join){
    this.join = join;
  }
  
  
  @Override
  public String getSQL() {
//    return String.join(" ", this.table.getName(), this.table.getAlias());
    return String.join(" ", this.join.getSQL());
  }
}
