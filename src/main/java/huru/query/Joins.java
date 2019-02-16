package huru.query;

import huru.entity.Table;

import java.util.Arrays;
import java.util.HashSet;


public class Joins {
  
  public static class Inner extends AbstractJoin {
  
    @Override
    public String getSQL() {
      return null;
    }
  }
  
  public static class Outer extends AbstractJoin {
    
    
    public Outer(Table... t){
      this.tables.addAll(Arrays.asList(t));
    }
  
    @Override
    public String getSQL() {
      return null;
    }
  }
  
  public static class Full extends AbstractJoin {
  
    @Override
    public String getSQL() {
      return null;
    }
  }
  
  
  public static class Cross extends AbstractJoin {
  
    @Override
    public String getSQL() {
      return null;
    }
  }
  
  
  public static class Left extends AbstractJoin {
  
    @Override
    public String getSQL() {
      return null;
    }
  }
  
  
  public static class Right extends AbstractJoin {
  
    @Override
    public String getSQL() {
      return null;
    }
  }
  
}
