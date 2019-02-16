package huru.query;

import java.util.Arrays;


public class Joins {
  
  public static class Inner extends AbstractJoin {
  
    public Inner(Table left, Table right) {
      super(left, right);
    }
  
    public Inner(Table left, AbstractJoin right) {
      super(left, right);
    }
    
    @Override
    public String getSQL() {
      return null;
    }
  }
  
  public static class Outer extends AbstractJoin {
  
    public Outer(Table left, Table right, Table... z) {
      super(left, right);
    }
  
    public Outer(Table left, AbstractJoin right) {
      super(left, right);
    }
  
    @Override
    public String getSQL() {
      return null;
    }
  }
  
  public static class Full extends AbstractJoin {
  
    public Full(Table left, Table right) {
      super(left, right);
    }
  
    public Full(Table left, AbstractJoin right) {
      super(left, right);
    }
    
    @Override
    public String getSQL() {
      return null;
    }
  }
  
  
  public static class Cross extends AbstractJoin {
  
    public Cross(Table left, Table right) {
      super(left, right);
    }
  
    public Cross(Table left, AbstractJoin right) {
      super(left, right);
    }
    
    @Override
    public String getSQL() {
      return null;
    }
  }
  
  
  public static class Left extends AbstractJoin {
  
    public Left(Table left, Table right) {
      super(left, right);
    }
  
  
    public Left(Table left, AbstractJoin right) {
      super(left, right);
    }
    
    @Override
    public String getSQL() {
      return null;
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
    public String getSQL() {
      return null;
    }
  }
  
}
