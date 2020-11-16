package spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import spring.entity.SysActivitiFormKey;
import spring.util.IdWorker;
import spring.util.JsonUtil;

/**
 * @author shen
 * @date 2020/10/15 13:35
 */
public class test6 {
    //填写formkey的工具
    public static void main(String[] args) {
        SysActivitiFormKey sysActivitiFormKey=new SysActivitiFormKey();
        sysActivitiFormKey.setActivitiType("2");
        sysActivitiFormKey.setIsAutoDispose("2");
        sysActivitiFormKey.setAssigneeClass("spring.listener.impl.WsVisitCountryAssignee");
        System.out.println(JSON.toJSONString(sysActivitiFormKey, SerializerFeature.WriteMapNullValue));
        System.out.println(IdWorker.getIdWorkerNext().toString());
        System.out.println(IdWorker.getIdWorkerNext().toString());
        System.out.println(IdWorker.getIdWorkerNext().toString());
        System.out.println(IdWorker.getIdWorkerNext().toString());
        System.out.println(IdWorker.getIdWorkerNext().toString());
        System.out.println(IdWorker.getIdWorkerNext().toString());
        System.out.println(IdWorker.getIdWorkerNext().toString());
        System.out.println(IdWorker.getIdWorkerNext().toString());

    }

}
