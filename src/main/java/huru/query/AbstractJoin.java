package huru.query;

import java.util.HashSet;

abstract class AbstractJoin<T> implements Base.IGetSQL {
  
  //  protected HashSet<Table> tables = new HashSet<Table>();
  protected Table left;
  protected Table right;
  protected AbstractJoin join;
  private Condition condition;
  
  public AbstractJoin(Table left, Table right) {
    this.left = left;
    this.right = right;
  }
  
  public AbstractJoin(Table left, AbstractJoin join) {
    this.left = left;
    this.join = join;
  }
  
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
  
  
}
