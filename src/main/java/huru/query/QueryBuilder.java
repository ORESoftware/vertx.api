package huru.query;


import huru.entity.BaseModel;

import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class QueryBuilder<T extends BaseModel> {
  
  private T model;
  
  public QueryBuilder(T model){
    this.model = model;
  }
  
  public Select<T> select(){
    return new Select<T>(this.model);
  }
  
  public Delete<T> delete(){
    return new Delete<T>(this.model);
  }
  
}
