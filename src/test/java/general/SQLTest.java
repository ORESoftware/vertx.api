package general;

import huru.entity.*;
import huru.entity.Model;
import huru.query.Bootstrapper;
import huru.query.QueryBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SQLTest {
  
  @Test
  public void test() {
    
    
    List<Class<? extends BaseModel>> annots = Arrays.asList(
      Models.User.class,
      Models.Klass.class
    );

//    for(Class<? extends BaseModel> b: annots){
//      QueryBuilder<?> qb = Bootstrapper.bootstrap(b);
//    }
//
//    Models.User user = Model.User;
//    QueryBuilder<Models.User> qb = new QueryBuilder<>(user);

//    var sql = qb.select()
//      .all()
//      .from(user.getTableName())
//      .getSQL();
    
    QueryBuilder<Models.User> qb = Bootstrapper.bootstrap(Models.User.class);
    Models.User user = qb.getModel();
    
    
    var sql = qb.select()
   
      .fields(user.EMAIL, user.HANDLE.as("foo"))
      .from(Tables.UserTable)
      .where()
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
