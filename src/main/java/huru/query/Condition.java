package huru.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Condition<T> {
  
  public TableField lhs;  // left-hand operand
  public Object rhs;      // right-hand operand (might be a TableField, might not)
  public ComparisonOperator<T> op;
  public ArrayList<Condition> conditions = new ArrayList<>();
  
  
  public static class OR extends Condition {
    
    public OR(Condition x, Condition... c) {
      this.conditions.addAll(Arrays.asList(x, c));
    }
    
    public OR(Condition x, List<Condition>... c) {
      this.conditions.addAll(Arrays.asList(x, c));
    }

//    public OR(Condition... c){
//      this.conditions.addAll(Arrays.asList(c));
//    }
    
  }
  
  
  public static class AND extends Condition {
    
    public AND(Condition x, Condition... c) {
//      this.conditions.add(x);
      this.conditions.addAll(Arrays.asList(x, c));
    }
    
    public AND(List<Condition> x, List<Condition>... c) {
      this.conditions.addAll(Arrays.asList(x, c));
    }
  }
  
  public static OR OR(Condition x, Condition... c) {
    return new OR(x, c);
  }
  
  
  public static AND AND(Condition x, Condition... c) {
    return new AND(x, c);
  }
  
  public static AND AND(List<Condition> x, List<Condition>... c) {
    return new AND(x, c);
  }
  
  
  public static void test() {
    
    ArrayList<Condition> c = new ArrayList<>();
    
    c.add(Condition.AND(Condition.AND(), Condition.OR()));
    
  }
  
  
}
