package huru.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utils {
  
  
  public static String join(String ...values){
    
    StringBuilder b = new StringBuilder();
  
    int i = 0, len = values.length;
    for(String s: values){
      b.append(s);
      if(++i < len){
        b.append(" ");
      }
    }
    
    return b.toString();
  }
  
  public static class Sep {
    public String value;
    public Sep(String v){
      this.value = v;
    }
  }
  
  
  public String join(Sep sep, String... values){
    
    StringBuilder b = new StringBuilder();
    
    int i = 0, len = values.length;
    for(String s: values){
      b.append(s);
      if(++i < len){
        b.append(sep);
      }
    }
    
    return b.toString();
    
  }
  
  
  public static <T> Map<String,T> renameKeys(Map<String,T> input, Map<String,String> mapper){
    
    HashMap<String,T> ret = new HashMap<>();
    Iterator it = input.entrySet().iterator();
    
    while (it.hasNext()) {
      Map.Entry<String,T> pair = (Map.Entry<String,T>)it.next();
      final String key = pair.getKey();
      final T val = pair.getValue();
      if(mapper.containsKey(key)){
        ret.put(mapper.get(key),val);
      }
      else{
        ret.put(key,val);
      }
    }
    
    return ret;
  }
  
  
}
