package spring.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

@Data
public class JsonUtil {

    //状态码  200: 成功 400:调用方法错误  500:程序出现了错误  700:没有权限
    private int status;
    private String message;
    private JSONObject josnObj;
    private Object data;
    private Integer  count;

    public static String ERROR_MESSAGE="出现了意外的错误请联系管理员.";
    public JsonUtil() {
    }

    public JsonUtil(String status, String message,JSONObject josnObj,Object data,Integer count ) {
        this.status = Integer.valueOf(status);
        this.message = message;
        this.josnObj =josnObj;
        this.data=data;
        this.count=count;
    }

    public JsonUtil(String status, String message,Object data) {
        this.status = Integer.valueOf(status);
        this.message = message;
        this.data=data;
    }

    /**
     * restful 返回
     */
    public static String error(String msg)
    {
        return JSON.toJSONString(new JsonUtil("500",msg,null,null,0),SerializerFeature.WriteMapNullValue);
    }


    public static String sucess(String msg, Object data) {
        return JSON.toJSONString(new JsonUtil("200", msg,null,data,0),SerializerFeature.WriteMapNullValue);
    }

    public static String sucess(String msg, Object data,Integer count ) {
        return JSON.toJSONString(new JsonUtil("200", msg,null,data,count),SerializerFeature.WriteMapNullValue);
    }
}
