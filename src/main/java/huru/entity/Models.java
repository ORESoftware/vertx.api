package huru.entity;

import huru.query.Bootstrapper;
import huru.query.TableField;
import static huru.entity.Annotations.*;

public class Models {
  
  @SetTable(User.tableName)
  public static class User extends BaseModel {
    
    public static User Model;
    private final static String tableName = "user_table";
    
    static {
      Model = Bootstrapper.bootstrap(User.class);
    }
  
    @NotNull
    @ColumnType(Integer.class)
    public final TableField ID = new TableField("id", "id");
  
  
    @NotNull
    public final TableField HANDLE = new TableField("handle", "handle");
  
  
    @NotNull
    public final TableField EMAIL = new TableField("email", "email");
    
    
    @Override
    public String getTableName() {
      return User.tableName;
    }
  
  
    @Override
    public void validateForUpdate(BaseModel m) {
    
    }
  
    @Override
    public void validateForInsert(BaseModel m) {
    
    }
  
    @Override
    public void validate(BaseModel m) {
    
    }
  }
  
  @SetTable(Klass.tableName)
  public static class Klass extends BaseModel {
    
    private final static String tableName = "klass_table";
    public static Klass Model;
    
    static {
      Model = Bootstrapper.bootstrap(Klass.class);
    }
  
    @NotNull
    public final TableField TITLE = new TableField("title", "title");
    
    @Override
    public String getTableName() {
      return Klass.tableName;
    }
  
    @Override
    public void validateForUpdate(BaseModel m) {
    
    }
  
    @Override
    public void validateForInsert(BaseModel m) {
    
    }
  
    @Override
    public void validate(BaseModel m) {
    
    }
  }
  
  
}
