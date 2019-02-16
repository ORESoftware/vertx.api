package huru.query;

import huru.entity.Table;

import java.util.HashSet;

abstract class AbstractJoin implements Base.IGetSQL {
  
  protected HashSet<Table> tables = new HashSet<Table>();
  private Condition condition;
  
  public AbstractJoin on(Condition c) {
    this.condition = c;
    return this;
  }
  
  public Condition getCondition() {
    return condition;
  }
  
  public void setCondition(Condition condition) {
    this.condition = condition;
  }
  
  public HashSet<Table> getTables() {
    return tables;
  }
  
  public void setTables(HashSet<Table> tables) {
    this.tables = tables;
  }
  
  
}
