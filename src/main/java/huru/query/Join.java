package huru.query;

import static huru.query.Joins.*;

public class Join {
  
  public static Inner Inner(Table t, Table x) {
    return new Inner(t, x);
  }
  
  public static Inner Inner(Table t, AbstractJoin x) {
    return new Inner(t, x);
  }
  
  public static Cross Cross(Table t, Table x) {
    return new Cross(t, x);
  }
  
  public static Cross Cross(Table t, AbstractJoin x) {
    return new Cross(t, x);
  }
  
  public static Outer Outer(Table x, Table y, Table... z) {
    return new Outer(x, Join.Outer(y, z[0]));
  }
  
  public static Outer Outer(Table t, AbstractJoin x) {
    return new Outer(t, x);
  }
  
  public static Left Left(Table t, Table x) {
    return new Left(t, x);
  }
  
  public static Left Left(Table t, AbstractJoin x) {
    return new Left(t, x);
  }
  
  public static Right Right(Table t, Table x) {
    return new Right(t, x);
  }
  
  public static Right Right(Table t, AbstractJoin x) {
    return new Right(t, x);
  }
  
  public static Full Full(Table t, Table x) {
    return new Full(t, x);
  }
  
  public static Full Full(Table t, AbstractJoin x) {
    return new Full(t, x);
  }
  
}
