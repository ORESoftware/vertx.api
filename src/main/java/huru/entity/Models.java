package huru.entity;

import huru.query.TableField;

import static huru.entity.Annotations.*;

public class Models {
  
  @SetTable(User.tableName)
  public static class User extends BaseModel {
    
    private final static String tableName = "user_table";
    
//    public User(){
//
//    }
    
    @NotNull
    @ColumnType(Integer.class)
    public final TableField ID = new TableField("user_id", "userId");
    
    
    @NotNull
    public final TableField HANDLE = new TableField("user_handle", "userHandle");
    
    
    @NotNull
    public final TableField EMAIL = new TableField("user_email", "userEmail");
    
    
    @Override
    public String getTableName() {
      return User.tableName;
    }
  }
  
  @SetTable(Klass.tableName)
  public static class Klass extends BaseModel {
    
    private final static String tableName = "klass_table";
    
//    public Klass(){
//
//    }
  
    @NotNull
    public final TableField TITLE = new TableField("user_handle", "userHandle");
    
    @Override
    public String getTableName() {
      return Klass.tableName;
    }
  }
  
  
}
