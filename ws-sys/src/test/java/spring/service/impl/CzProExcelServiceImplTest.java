package spring.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import spring.util.IdWorker;

import static org.junit.Assert.*;

public class CzProExcelServiceImplTest {

    public static void main(String[] args) throws ClassNotFoundException {
        /*Long a =1296456023409299456L;
        String c =IdWorker.getIdWorkerNext().toString();
        Double b= Double.valueOf(c);
        System.out.println(b);*/
        String str="{\"applyDate\":\"2020-09-09 00:00:00\"}";
        JSONObject json;
        json = JSONObject.parseObject(str);
        Object object = JSON.toJavaObject((JSON) json, Class.forName("spring.entity.WsExchangeActiviti"));
        System.out.println(object);

    }
}