package spring.listener.nextnode.impl;

import com.alibaba.fastjson.JSON;
import org.activiti.bpmn.model.*;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import spring.exception.CustomException;
import spring.listener.assignee.Assignee;
import spring.listener.assignee.impl.DefaultAssignee;
import spring.listener.nextnode.NextNode;
import spring.util.ApplicationContextUtil;

import java.util.*;

public class DefaultNextNode implements NextNode {

    @Override
    public List<Map<String, Object>> notify(String taskId, String modelId, String businessKey) {
        ProcessEngine processEngine = ApplicationContextUtil.getInstance().getApplicationContext().getBean(ProcessEngine.class);
        RepositoryService repositoryServiceImpl = processEngine.getRepositoryService();
        TaskService taskServiceImpl = processEngine.getTaskService();
        RuntimeService runtimeServiceImpl = processEngine.getRuntimeService();
        List<Map<String, Object>> list = new ArrayList<>();
        //1.首先拿到配置文件：
        Model modelData = repositoryServiceImpl.getModel(modelId);
        ProcessDefinition pd = repositoryServiceImpl.createProcessDefinitionQuery().deploymentId(modelData.getDeploymentId()).singleResult();
        //System.out.println("===模板相应版本ID：===" + pd.getDeploymentId() + ",  " + pd.getId() + "," + pd.getVersion());
        BpmnModel model = repositoryServiceImpl.getBpmnModel(pd.getId());
        //获取所有节点
        Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
        //根据taskId 获取activityId
        String activitiId = null;
        String nowNode = null;
        List<SequenceFlow> sequenceFlowList = new ArrayList<SequenceFlow>();//下一个节点集合
        Task task = null;
        if (businessKey != null && !"".equals(businessKey)) {
            task = taskServiceImpl.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
        } else if (taskId != null && !"".equals(taskId)) {
            task = taskServiceImpl.createTaskQuery().taskId(taskId).singleResult();
        } /*else {
            throw new CustomException("任务ID或表单ID不正确");
        }*/

        activitiId = task != null ? ((ExecutionEntity) runtimeServiceImpl.createExecutionQuery().executionId(task.getExecutionId()).singleResult()).getActivityId() : null;
        //2.然后根据配置文件获取当前活动节点的流出节点
        int i = 0;
        for (FlowElement e : flowElements) {

            String forEachId = e.getId();//循环节点key
            //System.out.println("循环节点key：" + forEachId);
            if (i == 1 && (activitiId == null || "".equals(activitiId))) {//流程还没有开启
                sequenceFlowList = ((org.activiti.bpmn.model.UserTask) e).getOutgoingFlows();
                nowNode = e.getId();
                break;
            } else if (forEachId.equals(activitiId)) {//当前节点==循环到的节点
                if (e instanceof org.activiti.bpmn.model.UserTask) {//节点
                    //如果formKey中有class要做镜像处理 //直接返回镜像信息
                    sequenceFlowList = ((org.activiti.bpmn.model.UserTask) e).getOutgoingFlows();//流出信息
                    nowNode = e.getId();
                    break;
                }
            }
            i++;
        }
        if (sequenceFlowList.size() == 0) {
            return null;
        }

        for (int j = 0; j < sequenceFlowList.size(); j++) {
            //System.out.println("节点名称" + sequenceFlowList.get(j).getTargetRef());
            for (FlowElement e : flowElements) {
                if (e.getId().equals(sequenceFlowList.get(j).getTargetRef())) {
                    if (e instanceof org.activiti.bpmn.model.UserTask) {//任务节点
                        UserTask userTask = ((org.activiti.bpmn.model.UserTask) e);
                        //sequenceFlowList = userTask.getOutgoingFlows();//流出信息
                        String fromKey = ((org.activiti.bpmn.model.UserTask) e).getFormKey();
                        if (fromKey == null) {
                            throw new CustomException("formKey异常");
                        }
                        Assignee assignee = new DefaultAssignee(); //如果formKey中有class要做镜像处理
                        List<Map<String, Object>> assignees = assignee.notify(JSON.parseObject(fromKey, HashMap.class));
                        Map<String, Object> map = new HashMap<>();
                        map.put("nextNodeName", userTask.getId());
                        map.put("nextNodeValue", userTask.getName());
                        map.put("assignees", assignees);
                        map.put("formKey", fromKey);
                        map.put("flowElementType", "UserTask");
                        list.add(map);
                        //break;
                    }
                    if (e instanceof org.activiti.bpmn.model.Event) {//event节点
                        Event event = ((org.activiti.bpmn.model.Event) e);
                        //sequenceFlowList = userTask.getOutgoingFlows();//流出信息
                        String fromKey = ((org.activiti.bpmn.model.UserTask) e).getFormKey();
                        Map<String, Object> map = new HashMap<>();
                        map.put("nextNodeName", event.getId());
                        map.put("nextNodeValue", event.getName());
                        map.put("assignees", null);
                        map.put("formKey", fromKey);
                        map.put("flowElementType", "Event");
                        list.add(map);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public boolean notifyCheak(String taskId, String ModelId, String nextNode) {
        return false;
    }
}
