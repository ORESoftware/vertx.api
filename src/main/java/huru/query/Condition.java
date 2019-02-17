package huru.query;

import huru.entity.TableMap;

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
  
  public Condition(Condition<T> x) {
    this.rhs = x.rhs;
    this.lhs = x.lhs;
    this.op = x.op;
  }
  
  public Condition(TableField lhs, Object rhs, ComparisonOperator<T> op) {
    this.lhs = lhs;
    this.rhs = rhs;
    this.op = op;
  }
  
  public static Condition ON(Condition x) {
    return new Condition(x);
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
  
    {
      var tableName = lhs.getTableName();
      Table table = TableMap.TableMap.get(tableName);
      b.append(String.join("", table.alias, ".", this.lhs.getDbName()));
    }
   
    b.append(" " + this.op.toString() + " ");
    
    if (this.rhs instanceof TableField) {
      
      var rhs= ((TableField)this.rhs);
      
      {
        var tableName = lhs.getTableName();
        Table table = TableMap.TableMap.get(tableName);
        b.append(String.join("", table.alias, ".", rhs.getDbName()));
      }
      
    } else {
  
      var v = this.rhs.toString();
      
      if(v.contains(" ")){
        v = String.join("","'",v,"'");
      }
      
      b.append(v);
    }
    
    b.append(" ) ");
    
    return b.toString();
    
  }
}
