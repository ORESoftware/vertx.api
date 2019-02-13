package huru.entity;

import static huru.entity.Annotations.*;

public class Models {
  
  public static class User extends BaseModel {
    
    private static String tableName = "users_table";
    
    @NotNull
    @Type(Integer.class)
    final TableField USER_ID = new TableField("user_id", "userId");
    
    
    @NotNull
    final TableField USER_HANDLE = new TableField("user_handle", "userHandle");
    
    
    @NotNull
    final TableField USER_EMAIL = new TableField("user_email", "userEmail");
  
    
    @Override
    public String getTableName() {
      return User.tableName;
    }
  }
  
  
  public static class Klass extends BaseModel {
  
    private static String tableName = "klass_table";
  
    @Override
    public String getTableName() {
      return Klass.tableName;
    }
  }
  
  
  
}
