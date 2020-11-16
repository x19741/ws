package spring.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.SneakyThrows;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import spring.entity.SysActivitiFormKey;
import spring.entity.SysActivitiTask;
import spring.exception.CustomException;
import spring.listener.assignee.Assignee;
import spring.listener.assignee.impl.DefaultAssignee;
import spring.util.ApplicationContextUtil;
import spring.util.IdWorker;

import java.util.*;

public class DefaultListener  implements ExecutionListener,TaskListener {

    @SneakyThrows
    @Override
    public void notify(DelegateTask delegateTask) {
        if("出访科科长分配经办人".equals(delegateTask.getName())){
            System.out.println("测试拦截点");
        }
        if(delegateTask.getFormKey()==null||"".equals(delegateTask.getFormKey())){
            delegateTask.setFormKey(JSON.toJSONString(new SysActivitiFormKey(), SerializerFeature.WriteMapNullValue));//防止这个值空值，导致解析出错
        }
        Map<String,String> params= (Map<String, String>) JSON.parse(delegateTask.getFormKey());
//        RuntimeService runtimeServiceimpl= (RuntimeService) ApplicationContextUtil.getInstance().getApplicationContext().getBean(RuntimeService.class);
        if(params.get("isAutoDispose")==null||"1".equals(params.get("isAutoDispose"))){
            String assignee= (String) delegateTask.getVariable("assignee");
            //String assignee= (String) runtimeServiceimpl.getVariable(delegateTask.getExecutionId(), "assignee");
            System.out.println("============TaskListener start============");
            System.out.println("设定流程处理人为"+assignee);
            delegateTask.setAssignee(assignee);

            //添加检查角色是否正确的过滤。
            //在那个获取下一节点处理人的接口那也要检查一次。
            // 下一个节点的问题。在节点开始之前检查节点是否正确匹配
            //那个获取一一个节点的接口那里也要检查一个

            String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
            String eventName = delegateTask.getEventName();
            System.out.println("事件名称:" + eventName);
            System.out.println("taskDefinitionKey:" + taskDefinitionKey);
            System.out.println("============TaskListener end============");
            if(delegateTask.getAssignee()==null||"".equals(delegateTask.getAssignee())){
                //throw  new CustomException("流程节点处理人为空！");
            }

            //流程触发事件
            System.out.println("############################################################");
            System.out.println("流程触发事件"+ delegateTask.getFormKey());
            System.out.println("##################################################################################################################");
        }else if("2".equals(params.get("isAutoDispose"))){
            /*Assignee assignee =null;
            if(params.get("assigneeClass")!=null&&!"".equals(params.get("assigneeClass"))){
                assignee=(Assignee)Class.forName(params.get("assigneeClass")).newInstance();
            }else{
                assignee=new DefaultAssignee();
            }
            List<Map<String, Object>> assignees = assignee.notify(JSON.parseObject(delegateTask.getFormKey(), HashMap.class));
            String assigneeList="";

            for (Map<String, Object> map: assignees) {
                assigneeList=assigneeList+","+map.get("assigneeValue");
            }
            assigneeList=assigneeList.substring(1,assigneeList.length());

            delegateTask.setVariable("assigneeList",Arrays.asList(assigneeList.split(",")));
            System.out.println("##################################################################################################################");*/
        }else if("3".equals(params.get("isAutoDispose"))){
            System.out.println("##################################################################################################################");
        }
        System.out.println("##################################################################################################################");

        /*if(params.get("isAutoDispose")==null||"1".equals(params)){
            sysActivitiTaskServiceImpl.insert(new SysActivitiTask(IdWorker.getIdWorkerNext().toString(), null, null, null, null, IdWorker.getIdWorkerNext().toString()
                    , serialNumber, urgencyDegree, task.getId(), runtimeServiceImpl.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getBusinessKey()
                    , null, null, null, null, null, assignee, assignee!=null
                    &&!"".equals(assignee)?sysUserServiceImpl.selectByPrimaryKey(assignee).getAccountname():null
                    , null, null, model.getName(), model.getId(), task.getName(), "角色任务", "003", sysUser.getId(), sysUser.getAccountname(), new Date()
                    , sysUser.getId(), sysUser.getAccountname(), params.get("ccCollege"), params.get("ccDept"), params.get("ccRole"), params.get("ccUnit"), params.get("ccBy1")
                    , params.get("ccUserId"), params.get("ccUserName"), null));
        }*/
    }

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
    }
}
