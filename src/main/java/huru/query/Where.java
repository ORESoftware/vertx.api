package huru.query;

public class Where implements Base.IGetSQL {
  
  public String simple;
  public Condition c;
  
  public Where(String simple){
    this.simple = simple;
  }
  
  public Where(Condition c){
    this.c = c;
  }
  
  @Override
  public String getSQL() {
    
    if(this.c != null){
      return this.c.getSQL();
    }
    
    return this.simple;
  }
}
