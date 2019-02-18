package huru.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class GroupBy {
  
  HashSet<TableField> tablefields = new HashSet<>();
  
  public GroupBy(TableField... t){
    this.tablefields.addAll(Arrays.asList(t));
  }
  
  public GroupBy(List<TableField> list){
    this.tablefields.addAll(list);
  }
  
  
  
}
