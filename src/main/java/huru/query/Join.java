package huru.query;

import huru.entity.Table;

import java.util.HashSet;

import static huru.query.Joins.*;

public class Join {
  
  
  public static Inner Inner(){
    return new Inner();
  }
  
  public static Cross Cross(){
    return new Cross();
  }
  
  public static Outer Outer(Table... t){
    return new Outer(t);
  }

  
  public static Left Left(){
    return new Left();
  }
  
  public static Right Right(){
    return new Right();
  }
  
  public static Full Full(){
    return new Full();
  }
  
}
