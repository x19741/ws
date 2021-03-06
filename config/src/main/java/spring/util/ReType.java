package spring.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 查询返回json格式依照ui默认属性名称
 */
public class ReType implements Serializable{
  /**状态*/
  public int status=200;
  /**状态信息*/
  public String message="";
  /**数据总数*/
  public long count;

  public List<?> data;

  public ReType() {
  }

  public ReType(long count, List<?> data) {
    this.count = count;
    this.data = data;
    this.message = "成功加载数据!";
  }

  /**
   * 动态添加属性 map 用法可以参考 activiti 模块中 com.nfdw.JsonTest 测试类中用法
   * @param count
   * @param data
   * @param map
   * @param node 绑定节点字符串 这样可以更加灵活
   * @return
   */
  public static String jsonStrng(long count,List<?> data,Map<String, Map<String,Object>> map,String node){
    JSONArray jsonArray= JSONArray.parseArray(JSON.toJSONString(data));
    JSONObject object=new JSONObject();
    for(int i=0;i<jsonArray.size();i++){
      JSONObject jsonData = (JSONObject) jsonArray.get(i);
      jsonData.putAll(map.get(jsonData.get(node)));
    }
    object.put("count",count);
    object.put("data",jsonArray);
    object.put("status",200);
    object.put("message","成功");
    return object.toJSONString();
  }
}
