package general;

import huru.entity.*;
import huru.query.Bootstrapper;
import huru.query.Condition;
import huru.query.QueryBuilder;
import huru.query.TableField;
import org.junit.Test;

import static huru.entity.Models.*;
import java.util.Arrays;
import java.util.List;

import static huru.query.Condition.*;

public class SQLTest {
  
  @Test
  public void test() {
    
    
    List<Class<? extends BaseModel>> annots = Arrays.asList(
      Models.User.class,
      Models.Klass.class
    );
    
    
    QueryBuilder<User> qb = new QueryBuilder<User>(User.Model);
    var u = User.Model;
    
    var sql = qb.select()
      .fields(
        u.EMAIL,
        u.HANDLE.as("foo")
      )
      .from(Tables.UserTable)
      .where(
        AND(u.EMAIL.eq("alex@gmail.com"), OR(u.HANDLE.gt(u.HANDLE), u.ID.eq("my id"),u.ID.neq("my id"))))
//      .from(new Table("randy"))
        .getSQL();
    
    
    synchronized (this) {
      synchronized (this) {
        System.out.println("fop");
      }
    }
    
    System.out.println(sql);
    
  }
  
}
