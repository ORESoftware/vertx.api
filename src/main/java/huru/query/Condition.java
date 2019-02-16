package huru.query;

import static huru.entity.Models.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static huru.query.Conditionals.*;

public class Condition<T> implements Base.IGetSQL {
  
  public TableField lhs;  // left-hand operand
  public Object rhs;      // right-hand operand (might be a TableField, might not)
  public ComparisonOperator<T> op;
  
  
  public Condition() {
  
  }
  
  public Condition(TableField lhs, Object rhs, ComparisonOperator<T> op) {
    this.lhs = lhs;
    this.rhs = rhs;
    this.op = op;
  }
  
  
  public static OR OR(Condition x, Condition y, Condition... z) {
    return new OR(x, y, z);
  }
  
  
  public static AND AND(Condition x, Condition y, Condition... c) {
    return new AND(x, y, c);
  }
  
  public static AND AND(List<Condition> x, List<Condition>... c) {
    return new AND(x, c);
  }
  
  
  public static void test() {
    
    ArrayList<Condition> c = new ArrayList<>();
    
    
//    c.add(Condition.AND(Condition.AND(user.EMAIL.eq(3), user.EMAIL.gte(6)), Condition.OR()));
    
  }
  
  
  @Override
  public String getSQL() {
  
    StringBuilder b = new StringBuilder();
  
    b.append(" ( ");
    
    b.append(this.lhs.getDbName() + " ");
    b.append(this.op.toString() + " ");
    
    if(this.rhs instanceof TableField){
      b.append(this.lhs.getDbName());
    }
    else{
      b.append(this.rhs.toString());
    }
    
  
    b.append(" ) ");
  
    return b.toString();
  
  }
}
