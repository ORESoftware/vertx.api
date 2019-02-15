package huru.query;

public class InsertionValue {
  
  public TableField f;

  public InsertionValue(TableField f){
    this.f = f;
  }

  public boolean equals(Object o){
    if(o.getClass() != InsertionValue.class){
      return false;
    }
    
    if(((InsertionValue)o).f != this.f){
       return true;
    }
    
    return true;
  }
}
