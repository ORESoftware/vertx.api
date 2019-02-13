package general;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.ThreadLocalTransactionProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;


class Foo {
  
  public String loaf = "";
  public Boolean bool;

//  static boolean dog(){
//    return true;
//  }
  
  public String toString() {
    return JsonObject.mapFrom(this).toString();
  }
}

class Model extends HashMap<String, Object> {
//  public boolean loaf;
//  public String bool;

}


public class GeneralTest {
  
  
  @Test
  public void test() {
    
    JsonObject v = new JsonObject();
    v.put("loaf", true);
    v.put("bool", "false");

//    v.put("bool", "false");
    
    var k = v.getMap();
    System.out.println(k.toString());
    
  }
  
  @Test
  public void JSON() {
  
  
  }

//  @Test
//  public void jooq(){
//
//    final Configuration configuration = new DefaultConfiguration()
////      .set(cp)
//      .set(SQLDialect.POSTGRES_10);
////      .set(new ThreadLocalTransactionProvider(cp, true));
//
//    final DSLContext ctx = DSL.using(configuration);
//
//    var field = new SelectField(){
//
//      @Override
//      public String getName() {
//        return "barf";
//      }
//
//      @Override
//      public Name getQualifiedName() {
//        return null;
//      }
//
//      @Override
//      public Name getUnqualifiedName() {
//        return null;
//      }
//
//      @Override
//      public String getComment() {
//        return null;
//      }
//
//      @Override
//      public Converter getConverter() {
//        return null;
//      }
//
//      @Override
//      public Binding getBinding() {
//        return null;
//      }
//
//      @Override
//      public Class getType() {
//        return null;
//      }
//
//      @Override
//      public DataType getDataType() {
//        return null;
//      }
//
//      @Override
//      public DataType getDataType(Configuration configuration) {
//        return null;
//      }
//    };
//
//    var sql = ctx.select(field).from("table").getSQL();
//
//    System.out.println("sql is:");
//    System.out.println(sql);
//
//  }
  
  
}
