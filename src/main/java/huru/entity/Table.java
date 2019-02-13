package huru.entity;

class AliasMaker {
  
  static int length = 1;
  static int chr = 0;
  
  
  static String make(){
    
    // chr should be between 65 and 90 inclusive
    
    StringBuilder b = new StringBuilder();
    chr = chr++%25;
  
    char c = (char)(65 + chr);
    for(int i =0; i < length; i++){
      b.append(c);
    }
  
    if(chr == 25){
      length++;
    }
    
    return b.toString();
  }
}

public class Table {
  
  public String alias;
  public String name;
  
  public Table(String name){
    this.name = name;
    this.alias = AliasMaker.make();
    TableMap.TableMap.put(name,this);
  }
  
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getAlias() {
    return alias;
  }
  
  public void setAlias(String alias) {
    this.alias = alias;
  }
}
