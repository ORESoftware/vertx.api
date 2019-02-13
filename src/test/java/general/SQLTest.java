package general;

import huru.entity.BaseModel;
import huru.entity.Model;
import huru.entity.Models;
import huru.entity.Table;
import huru.query.QueryBuilder;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

public class SQLTest {
  
  @Test
  public void test() {
  
    Models.User user = Model.User;
    QueryBuilder<Models.User> qb = new QueryBuilder<>(user);
  
//    var sql = qb.select()
//      .all()
//      .from(user.getTableName())
//      .getSQL();
  
    var sql = qb.select()
      .fields(user.USER_EMAIL, user.USER_HANDLE.as("foo"))
      .from(new Table("randy"))
      .getSQL();
  
  
  
    System.out.println(sql);
    
  }
  
}
