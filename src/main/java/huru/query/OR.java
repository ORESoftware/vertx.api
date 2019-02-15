package huru.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OR extends Condition {
  
  public ArrayList<Condition> conditions = new ArrayList<>();
  
  public OR(Condition x, Condition y, Condition... c) {
    this.conditions.add(x);
    this.conditions.add(y);
    this.conditions.addAll(Arrays.asList(c));
  }
  
  public OR(List<Condition> x, List<Condition>... c) {
    this.conditions.addAll(x);
    for (List<Condition> l : c) {
      this.conditions.addAll(l);
    }
  }
  
  @Override
  public String getSQL() {
    StringBuilder b = new StringBuilder();
    
    b.append(" ( ");
    
    int i = 0, len = this.conditions.size();
    for (Condition c : this.conditions) {
      
      b.append(c.getSQL());
      
      if (++i < len) {
        b.append(" OR ");
      }
      
    }
    
    b.append(" ) ");
    return b.toString();
    
  }

//    public OR(Condition... c){
//      this.conditions.addAll(Arrays.asList(c));
//    }

}
