package huru.query;

abstract class AbstractJoin<T> implements Base.IGetSQL, Base.IGetJoinName {
  
  //  protected HashSet<Table> tables = new HashSet<Table>();
  protected Table left;
  protected Table right;
  protected AbstractJoin join;
  private Condition condition;
  
  public AbstractJoin(){

  }
  
  public AbstractJoin(AbstractJoin join){
    this.join = join;
  }
  
  public AbstractJoin(Table left, Table right) {
    this.left = left;
    this.right = right;
  }
  
  public AbstractJoin(Table left, Table right, Condition c) {
    this.left = left;
    this.right = right;
    this.condition = c;
  }
  
  public AbstractJoin(Table left, AbstractJoin join) {
    this.left = left;
    this.join = join;
  }
  
  public AbstractJoin(Table left, AbstractJoin join, Condition c) {
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
  
  public String getSQL() {
    StringBuilder b = new StringBuilder();
    
    b.append(" ( ");
    b.append(this.left.getName());
    b.append(String.join(" "," ",this.getJoinName()));
    
    if (this.right != null) {
      b.append(String.join(" ", " ", this.right.getName()));
    } else {
      b.append(String.join(" ", " ", this.join.getSQL()));
    }
    
    if(this.condition != null){
      b.append(String.join(" ", " ", "ON", this.condition.getSQL()));
    }
    
    b.append(" ) ");
    
    return b.toString();
  }
  
}
