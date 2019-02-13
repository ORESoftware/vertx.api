package huru.query;

public class Where implements Base.IGetSQL {
  
  String simple;
  
  public Where(String simple){
    this.simple = simple;
  }
  
  @Override
  public String getSQL() {
    return this.simple;
  }
}
