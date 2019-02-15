package huru.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AND extends Condition {
  
  public ArrayList<Condition> conditions = new ArrayList<>();
  
  public AND(Condition x, Condition y, Condition... c) {
//      this.conditions.add(x);
    this.conditions.add(x);
    this.conditions.add(y);
    this.conditions.addAll(Arrays.asList(c));
  }
  
  public AND(List<Condition> x, List<Condition>... c) {
    this.conditions.addAll(x);
    for(List<Condition> z: c){
      this.conditions.addAll(z);
    }
  }
  
  public String getSQL(){
    StringBuilder b = new StringBuilder();
    
    b.append('(');
    
    int i = 0,len = this.conditions.size();
    for(Condition c: this.conditions){
      
      b.append(c.getSQL());
      
      if(++i < len){
        b.append(" AND ");
      }
    
    }
    
    b.append(')');
    
    return b.toString();
    
  }
}
