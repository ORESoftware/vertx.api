package huru.query;

public class SelectWithAlias extends Select {
  
  public String alias;
  
  public SelectWithAlias() {
    super();
  }
  
  public SelectWithAlias(String alias) {
    super();
    this.alias = alias;
  }
  
  public String getSQL() {
    
    StringBuilder b = new StringBuilder();
    
    b.append(" ( ");
    
    b.append(super.getSQL());
    
    b.append(" ) ");
    b.append(String.join(" ", " ", "AS", this.alias," "));
    return b.toString();
    
  }
  
}
