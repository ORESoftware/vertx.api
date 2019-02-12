package huru.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utils {
  
  
  static String join(String ...v){
    
    StringBuilder b = new StringBuilder();
    
    for(String s: v){
      b.append(s);
    }
    
    return b.toString();
  }
  
  
  static <T> Map<String,T> renameKeys(Map<String,T> input, Map<String,String> mapper){
    
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
