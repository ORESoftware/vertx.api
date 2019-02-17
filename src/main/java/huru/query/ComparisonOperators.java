package huru.query;

class ComparisonOperator<T> {
}


public class ComparisonOperators<T> {
  
  public static class GreaterThanOrEqual  extends ComparisonOperator<GreaterThanOrEqual> {
    public String toString(){
      return ">=";
    }
  }
  
  public static class GreaterThan extends ComparisonOperator<GreaterThan> {
    public String toString(){
      return ">";
    }
  }
  
  public static class LessThan extends ComparisonOperator<LessThan> {
    public String toString(){
      return "<";
    }
  }
  
  public static class LessThanOrEqual extends ComparisonOperator<LessThanOrEqual> {
    public String toString(){
      return "<=";
    }
  }
  
  public static class EqualTo extends ComparisonOperator<EqualTo> {
    public String toString(){
      return "=";
    }
  }
  
  public static class NotEqualTo extends ComparisonOperator<NotEqualTo> {
    public String toString(){
      return "<>";
    }
  }
  
  
}
