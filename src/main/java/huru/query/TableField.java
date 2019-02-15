package huru.query;

import huru.entity.TableMap;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import huru.query.ComparisonOperators.*;


public class TableField {
  
//  private Set<Class<? extends Annotation>> annots = new HashSet<>();
  //  private HashMap<Class<? extends Annotation>, Object> annotationMap = new HashMap<>();
  private HashMap<String, Object> annotationMap = new HashMap<>();
  private String dbName;
  private String runtimeName;
  private String tableName;
  private String alias;
  private Object value;  // for INSERT INTO
  
  
  public TableField(String k, String v) {
    this.dbName = k;
    this.runtimeName = v;
  }
  
  
  public TableField(String k, String v, String as,  HashMap<String, Object> annotationMap, String tableName) {
    this.dbName = k;
    this.runtimeName = v;
    this.alias = as;
    this.annotationMap = annotationMap;
    this.tableName = tableName;
  }
  
  public TableField(String k, String v,  HashMap<String, Object> annotationMap, Object value, String tableName) {
    this.dbName = k;
    this.runtimeName = v;
    this.value = value;
    this.annotationMap = annotationMap;
    this.tableName = tableName;
  }
  
  public String getTableName() {
    return this.tableName;
  }
  
  public void setTableName(String tableName) {
    this.tableName = tableName;
  }
  
  public String getAlias() {
    return this.alias;
  }
  
  public void setAlias(String alias) {
    this.alias = alias;
  }
  
  
  public boolean hasAlias() {
    return this.alias != null;
  }
  
  public String getDbName() {
    return this.dbName;
  }
  
  public String getRuntimeName() {
    return this.runtimeName;
  }
  
  
  public boolean hasAnnotation(Annotation a) {
    return this.annotationMap.containsKey(a);
  }
  
  public boolean hasAnnotation(Class<? extends Annotation> c) {
    return this.annotationMap.containsKey(c);
  }
  
  public void addAnnotation(String c, Object v) {
    this.annotationMap.put(c, v);
//    this.annotationMap.get(Annotations.NotNull.class);
  }
  
  public void addAnnotation(Class<? extends Annotation> c, Object v) {
//    this.annotationMap.put(c, v);
//    this.annotationMap.get(Annotations.NotNull.class);
  }
  
  public TableField as(String s) {
    return new TableField(
      this.dbName,
      this.runtimeName,
      s,
      new HashMap<String,Object>(this.annotationMap),
      this.tableName
    );
  }
  
  public TableField val(Object v) {
    return new TableField(
      this.dbName,
      this.runtimeName,
      this.annotationMap,
      v,
      this.tableName
    );
  }
  
  public String toString() {
    return String.join(",", this.dbName, this.runtimeName, this.tableName,this.annotationMap.toString());
  }
  
  
  public Condition<EqualTo> eq(Object o){
    return new Condition<>(this, o, new EqualTo());
  }
  
  public Condition<NotEqualTo> neq(Object o){
    return new Condition<>(this, o, new NotEqualTo());
  }
  
  public Condition<GreaterThan> gt(Object o){
    return new Condition<>(this, o, new GreaterThan());
  }
  
  public Condition<LessThan> lt(Object o){
    return new Condition<>(this, o, new LessThan());
  }
  
  public Condition<LessThanOrEqual> lte(Object o){
    return new Condition<>(this, o, new LessThanOrEqual());
  }
  
  public Condition<GreaterThanOrEqual> gte(Object o){
    return new Condition<>(this, o, new GreaterThanOrEqual());
  }
  
}
