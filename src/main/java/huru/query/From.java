package huru.query;

public class From<T extends AbstractJoin> implements Base.IGetSQL {

  //  String simple;
  
  private Table t;
  private AbstractJoin<T> join;
  
  //  public From(String simple){
  //    this.simple = simple;
  //  }
  
  public From(Table t){
    this.t = t;
  }
  
  public From(Table x, Table y, Table... z){
    this.join = Join.Outer(x,y,z);
  }
  
  public From(AbstractJoin<T> join){
    this.join = join;
  }
  
  
  @Override
  public String getSQL() {
    
    if(this.t != null){
      return String.join(" ", this.t.getName(), this.t.getAlias());
    }
    
    return String.join(" ", this.join.getSQL());
  }
}
