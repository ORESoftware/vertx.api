package huru.query;

import java.util.Arrays;

public class Joins {
  
  public static class Inner extends AbstractJoin {
  
    public Inner(Table left, Table right, Condition on) {
      super(left, right, on);
    }
  
    public Inner(Table left, AbstractJoin right, Condition on) {
      super(left, right, on);
    }
  
  
    public Inner(Table left, Table right) {
      super(left, right);
    }
  
  
    public Inner(Table left, AbstractJoin right) {
      super(left, right);
    }
    
    @Override
    public String getJoinName() {
      return "INNER JOIN";
    }
  }
  
  public static class Outer extends AbstractJoin {
  
    public Outer(Table left, Table right, Condition on) {
      super(left, right, on);
    }
  
    public Outer(Table left, AbstractJoin right, Condition on) {
      super(left, right, on);
    }
  
  
    public Outer(Table left, Table right) {
      super(left, right);
    }
    
  
    public Outer(Table left, Table right, Table... z) {
  
      super();
      
      Outer j = new Outer(left,right);
      
      for(int i = 0; i < z.length; i++){
        j = new Outer(z[i],j);
      }
      
      this.join = j;
      
    }
  
    public Outer(Table left, AbstractJoin right) {
      super(left, right);
    }
    
    @Override
    public String getJoinName() {
      return "OUTER JOIN";
    }
    
  }
  
  public static class Full extends AbstractJoin {
  
    public Full(Table left, Table right, Condition on) {
      super(left, right, on);
    }

    public Full(Table left, AbstractJoin right, Condition on) {
      super(left, right, on);
    }
  
    public Full(Table left, Table right) {
      super(left, right);
    }
  
  
    public Full(Table left, AbstractJoin right) {
      super(left, right);
    }
    
    @Override
    public String getJoinName() {
      return "FULL OUTER JOIN";
    }
    
  }
  
  public static class Cross extends AbstractJoin {
  
    public Cross(Table left, Table right, Condition on) {
      super(left, right, on);
    }
  
    public Cross(Table left, AbstractJoin right, Condition on) {
      super(left, right, on);
    }
  
    public Cross(Table left, Table right) {
      super(left, right);
    }
  
  
    public Cross(Table left, AbstractJoin right) {
      super(left, right);
    }
    
    @Override
    public String getJoinName() {
      return "CROSS JOIN";
    }
  }
  
  public static class Left extends AbstractJoin {
  
    public Left(Table left, Table right, Condition on) {
      super(left, right, on);
    }
  
    public Left(Table left, AbstractJoin right, Condition on) {
      super(left, right, on);
    }
    
    public Left(Table left, Table right) {
      super(left, right);
    }
    
    public Left(Table left, AbstractJoin right) {
      super(left, right);
    }
    @Override
    public String getJoinName() {
      return "LEFT JOIN";
    }
  }
  
  public static class Right extends AbstractJoin {
  
    public Right(Table left, Table right) {
      super(left, right);
    }
  
  
    public Right(Table left, AbstractJoin right) {
      super(left, right);
    }
    
    @Override
    public String getJoinName() {
      return "RIGHT JOIN";
    }
    
  }
  
}
