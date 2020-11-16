package spring.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.util.logging.Slf4j;
import org.activiti.bpmn.model.*;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.fop.hyphenation.CharVector;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.SysActivitiFormKey;
import spring.entity.SysActivitiTask;
import spring.entity.SysUser;
import spring.exception.CustomException;
import spring.listener.assignee.Assignee;
import spring.listener.assignee.impl.DefaultAssignee;
import spring.service.ProcessService;
import spring.service.PublicService;
import spring.service.SysActivitiTaskService;
import spring.service.SysUserService;
import spring.util.ApplicationContextUtil;
import spring.util.IdWorker;
import spring.util.StringUtils;

import java.io.InputStream;
import java.util.*;

@Service
@Transactional
@Slf4j
public class ProcessServiceImpl implements ProcessService {


    @Autowired
    TaskService taskServiceImpl;

    @Autowired
    RepositoryService repositoryServiceImpl;

    @Autowired
    RuntimeService runtimeServiceImpl;

    @Autowired
    HistoryService historyServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ProcessEngine processEngine;

    @Autowired
    SysUserService sysUserServiceImpl;


    @Autowired
    SysActivitiTaskService sysActivitiTaskServiceImpl;

    @Autowired
    PublicService publicServiceImpl;

    @Override
    public List<Map<String, Object>> getNextNode(String taskId, String modelId, String businessKey) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
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
        if (taskId != null && !"".equals(taskId)) {
            task = taskServiceImpl.createTaskQuery().taskId(taskId).singleResult();
        } else if (businessKey != null && !"".equals(businessKey)) {
            task = taskServiceImpl.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
        }  /*else {
            throw new CustomException("任务ID或表单ID不正确");
        }*/

        if(task!=null&&"申请人/代填人提交材料".equals(task.getName())&&task.getTaskDefinitionKey().equals("shijian7")){
            System.out.println("测试拦截点");
        }
        activitiId = task != null ? ((ExecutionEntity) runtimeServiceImpl.createExecutionQuery().executionId(task.getExecutionId()).singleResult()).getActivityId() : null;
        if (task != null && task.getName().equals("团员确认")) {
            System.out.println("团员确认节点断点");
        }
        if (task != null && task.getName().equals("出访科经办人审核材料")) {
            System.out.println("出访科经办人审核材料节点断点");
        }
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
            if (e instanceof org.activiti.bpmn.model.SubProcess) {
                Collection<FlowElement> subFlowElements = ((org.activiti.bpmn.model.SubProcess) e).getFlowElements();
                for (FlowElement e2 : subFlowElements) {

                    //System.out.println("循环节点key：" + forEachId);
                    if (e2.getId().equals(activitiId)) {//当前节点==循环到的节点
                        if (e2 instanceof org.activiti.bpmn.model.UserTask) {//节点
                            //如果formKey中有class要做镜像处理 //直接返回镜像信息
                            sequenceFlowList = ((org.activiti.bpmn.model.UserTask) e2).getOutgoingFlows();//流出信息
                            nowNode = e2.getId();
                            break;
                        }
                    }
                }
            }
            i++;
        }
        if (sequenceFlowList.size() == 0) {
            return null;
        }
        //这里反射节点处理类
        List<String> nextNodelist = null;
        if (task != null && task.getName().equals("申请人/代填人提交材料")&&task.getTaskDefinitionKey().equals("shijian3")) {//这部分代码后续开发人员可以写到节点处理类实现
            nextNodelist = new ArrayList<>();
            nextNodelist.add("出访科科长分配经办人");
        }
        /*if(task!=null&&task.getName().equals("出访科经办人审核材料")){
            nextNodelist=new ArrayList<>();
            nextNodelist.add("每个团员一条分支");
        }*/
        for (int j = 0; j < sequenceFlowList.size(); j++) {
            //System.out.println("节点名称" + sequenceFlowList.get(j).getTargetRef());
            for (FlowElement e : flowElements) {
                boolean isnextNode = false;
                if (nextNodelist != null) {
                    for (String str : nextNodelist) {
                        if (str.equals(e.getName())) {
                            isnextNode = true;
                        }
                    }
                    if (!isnextNode)
                        continue;
                }
                if (e instanceof org.activiti.bpmn.model.SubProcess) {//任务节点
                    SubProcess subProcess = ((org.activiti.bpmn.model.SubProcess) e);
                    Collection<FlowElement> flowElements2 = subProcess.getFlowElements();
                    for (FlowElement e2 : flowElements2) {
                        isnextNode = false;
                        if (nextNodelist != null) {
                            for (String str : nextNodelist) {
                                if (str.equals(e.getName())) {
                                    isnextNode = true;
                                }
                            }
                            if (!isnextNode)
                                continue;
                        }

                        if (e2.getId().equals(sequenceFlowList.get(j).getTargetRef())) {
                            if (e2 instanceof org.activiti.bpmn.model.UserTask) {//任务节点
                                UserTask userTask = ((org.activiti.bpmn.model.UserTask) e2);
                                //sequenceFlowList = userTask.getOutgoingFlows();//流出信息
                                String fromKey = ((org.activiti.bpmn.model.UserTask) e2).getFormKey();
                                if (fromKey == null) {
                                    fromKey = JSON.toJSONString(new SysActivitiFormKey(), SerializerFeature.WriteMapNullValue);//防止这个值空值，导致解析出错
                                }
                                Map<String, String> formkeyMap = (Map<String, String>) JSON.parse(fromKey);
                                Assignee assignee = null;
                                if (formkeyMap.get("assigneeClass") != null && !"".equals(formkeyMap.get("assigneeClass"))) {
                                    assignee = (Assignee) Class.forName(formkeyMap.get("assigneeClass")).newInstance();
                                } else {
                                    assignee = new DefaultAssignee();
                                }
                                List<Map<String, Object>> assignees = assignee.notify(JSON.parseObject(fromKey, HashMap.class));
                                Map<String, Object> map = new HashMap<>();
                                map.put("nextNodeName", userTask.getName());
                                map.put("nextNodeValue", userTask.getName());
                                map.put("nextNodeValueId", userTask.getId());
                                map.put("assignees", assignees);
                                map.put("fromKey", formkeyMap);
                                map.put("flowElementType", "UserTask");
                                list.add(map);
                                //break;
                            }

                            if (e2 instanceof org.activiti.bpmn.model.Event) {//event节点
                                Event event = ((org.activiti.bpmn.model.Event) e2);
                                //sequenceFlowList = userTask.getOutgoingFlows();//流出信息
                                Map<String, Object> map = new HashMap<>();
                                map.put("nextNodeName", event.getName());
                                map.put("nextNodeValue", event.getName());
                                map.put("nextNodeValueId", event.getId());
                                map.put("assignees", null);
                                map.put("flowElementType", "Event");
                                map.put("fromKey", new HashMap<String,String>());
                                list.add(map);
                            }
                        }


                    }


                    /*//sequenceFlowList = userTask.getOutgoingFlows();//流出信息
                    String fromKey = null;
                    if (fromKey == null) {
                        SysActivitiFormKey sysActivitiFormKey = new SysActivitiFormKey();
                        sysActivitiFormKey.setIsAutoDispose("2");
                        fromKey=JSON.toJSONString(sysActivitiFormKey, SerializerFeature.WriteMapNullValue);//防止这个值空值，导致解析出错
                    }
                    Map<String, String> formkeyMap = (Map<String, String>) JSON.parse(fromKey);
                    Assignee assignee = null;
                    if (formkeyMap.get("assigneeClass") != null && !"".equals(formkeyMap.get("assigneeClass"))) {
                        assignee = (Assignee) Class.forName(formkeyMap.get("assigneeClass")).newInstance();
                    } else {
                        assignee = new DefaultAssignee();
                    }
                    List<Map<String, Object>> assignees = assignee.notify(JSON.parseObject(fromKey, HashMap.class));
                    Map<String, Object> map = new HashMap<>();
                    map.put("nextNodeName", userTask.getName());
                    map.put("nextNodeValue", userTask.getName());
                    map.put("nextNodeValueId", userTask.getId());
                    map.put("assignees", assignees);
                    map.put("fromKey", formkeyMap);
                    map.put("flowElementType", "UserTask");
                    list.add(map);*/
                    //break;
                }
                if (e.getId().equals(sequenceFlowList.get(j).getTargetRef())) {
                    if (e instanceof org.activiti.bpmn.model.UserTask) {//任务节点
                        UserTask userTask = ((org.activiti.bpmn.model.UserTask) e);
                        //sequenceFlowList = userTask.getOutgoingFlows();//流出信息
                        String fromKey = ((org.activiti.bpmn.model.UserTask) e).getFormKey();
                        if (fromKey == null) {
                            fromKey = JSON.toJSONString(new SysActivitiFormKey(), SerializerFeature.WriteMapNullValue);//防止这个值空值，导致解析出错
                        }
                        Map<String, String> formkeyMap = (Map<String, String>) JSON.parse(fromKey);
                        Assignee assignee = null;
                        if (formkeyMap.get("assigneeClass") != null && !"".equals(formkeyMap.get("assigneeClass"))) {
                            assignee = (Assignee) Class.forName(formkeyMap.get("assigneeClass")).newInstance();
                        } else {
                            assignee = new DefaultAssignee();
                        }
                        List<Map<String, Object>> assignees = assignee.notify(JSON.parseObject(fromKey, HashMap.class));
                        Map<String, Object> map = new HashMap<>();
                        map.put("nextNodeName", userTask.getName());
                        map.put("nextNodeValue", userTask.getName());
                        map.put("nextNodeValueId", userTask.getId());
                        map.put("assignees", assignees);
                        map.put("fromKey", formkeyMap);
                        map.put("flowElementType", "UserTask");
                        list.add(map);
                        //break;
                    }

                    if (e instanceof org.activiti.bpmn.model.Event) {//event节点
                        Event event = ((org.activiti.bpmn.model.Event) e);
                        //String fromKey = ((org.activiti.bpmn.model.UserTask) e).getFormKey();
                        //sequenceFlowList = userTask.getOutgoingFlows();//流出信息
                        Map<String, Object> map = new HashMap<>();
                        map.put("nextNodeName", event.getName());
                        map.put("nextNodeValue", event.getName());
                        map.put("nextNodeValueId", event.getId());
                        map.put("assignees", null);
                        map.put("flowElementType", "Event");
                        map.put("fromKey", new HashMap<String,String>());
                        list.add(map);
                    }
                    if (e instanceof org.activiti.bpmn.model.SubProcess) {//任务节点
                        SubProcess subProcess = ((org.activiti.bpmn.model.SubProcess) e);
                        //sequenceFlowList = userTask.getOutgoingFlows();//流出信息
                        String fromKey = null;
                        if (fromKey == null) {
                            SysActivitiFormKey sysActivitiFormKey = new SysActivitiFormKey();
                            sysActivitiFormKey.setIsAutoDispose("2");
                            fromKey = JSON.toJSONString(sysActivitiFormKey, SerializerFeature.WriteMapNullValue);//防止这个值空值，导致解析出错
                        }
                        Map<String, String> formkeyMap = (Map<String, String>) JSON.parse(fromKey);
                        Assignee assignee = null;
                        if (formkeyMap.get("assigneeClass") != null && !"".equals(formkeyMap.get("assigneeClass"))) {
                            assignee = (Assignee) Class.forName(formkeyMap.get("assigneeClass")).newInstance();
                        } else {
                            assignee = new DefaultAssignee();
                        }
                        List<Map<String, Object>> assignees = assignee.notify(JSON.parseObject(fromKey, HashMap.class));
                        Map<String, Object> map = new HashMap<>();
                        map.put("nextNodeName", subProcess.getName());
                        map.put("nextNodeValue", subProcess.getName());
                        map.put("nextNodeValueId", subProcess.getId());
                        map.put("assignees", assignees);
                        map.put("fromKey", formkeyMap);
                        map.put("flowElementType", "UserTask");
                        list.add(map);
                        //break;
                    }
                }
            }
        }
        return list;
    }

    /**
     * @param taskId        任务id
     * @param modelId       模型id
     * @param assignee      下一节点处理人
     * @param nextNode      下一节点
     * @param comformInfo   表单信息
     * @param businessKey   表单id
     * @param opinion       意见
     * @param serialNumber  流水号
     * @param urgencyDegree 紧急程度
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> completeTask(String taskId, String modelId, String assignee, String nextNode, String comformInfo, String comformId, String businessKey
            , String opinion, String serialNumber, String urgencyDegree) throws Exception {
        Map<String, Object> map = null;
        SysUser sysUser = null;
        Task task = null;
        if (taskId != null && !"".equals(taskId)) {
            task = taskServiceImpl.createTaskQuery().taskId(taskId).singleResult();
            if (task == null) {
                throw new CustomException("taskId错误！");
            }
            businessKey = runtimeServiceImpl.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getBusinessKey();
        }
        Model model = repositoryServiceImpl.getModel(modelId);
        System.out.println("模型id" + model.getId());
        System.out.println("模型名称" + model.getName());
        try {
            sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        } catch (Exception e) {
            e.printStackTrace();
            sysUser = new SysUser();
        }
        try {
            synchronized (this.getClass()) {
                if (businessKey == null || "".equals(businessKey)) {
                    businessKey = IdWorker.getIdWorkerNext().toString();
                    if (!(comformInfo == null || "".equals(comformInfo))) {
                        if (publicServiceImpl.insertComfromTable(comformInfo, null, businessKey) <= 0) {
                            throw new CustomException(CustomException.EXCEPTION_MASSAGE);
                        }
                    }
                } else {
                    if (!(comformInfo == null || "".equals(comformInfo))) {
                        if (publicServiceImpl.updateComfromTable(comformInfo, null) <= 0) {
                            throw new CustomException(CustomException.EXCEPTION_MASSAGE);
                        }
                    }
                }
                if (StringUtils.isEmpty(taskId)) {//开启流程
                    //开启前检查一下日志里面有没有业务id未该业务id的数据
                    if (StringUtils.isEmpty(businessKey)) {
                        throw new CustomException("业务ID不能为空！");
                    }

                    SysActivitiTask sysActivitiTask = new SysActivitiTask();
                    sysActivitiTask.setBusinessKey(businessKey);
                    List<SysActivitiTask> sysActivitiTasks = sysActivitiTaskServiceImpl.selectListByPage(sysActivitiTask);

                    if (sysActivitiTasks == null || sysActivitiTasks.size() <= 0) {
                        //这里可以判断该用户是否有权限添加流程
                        Map<String, Object> variables = new HashMap<>();
                        variables.put("assignee", sysUser.getId());
                        //根据modelId获取processDefinitionId
                        ProcessDefinition processDefinition = repositoryServiceImpl.createProcessDefinitionQuery().deploymentId(model.getDeploymentId()).singleResult();
                        if (processDefinition == null) {
                            throw new CustomException("请发布流程");
                        }
                        String processDefinitionId = processDefinition.getId();
                        ProcessInstance processInstance = runtimeServiceImpl.startProcessInstanceById(processDefinitionId, businessKey, variables);
                        //得到第一个流程
                        List<Task> list = taskServiceImpl.createTaskQuery().processDefinitionId(processInstance.getProcessDefinitionId()).processInstanceBusinessKey(businessKey).orderByTaskCreateTime().desc().list();
                        //这里要完成第一个任务
                        System.out.println(list);
                        task = list.get(0);
                        Map<String, String> params = null;
                        try {
                            params = JSONObject.parseObject(task.getFormKey(), new TypeReference<Map<String, String>>() {
                            });
                        } catch (Exception e) {
                            throw new Exception("流程FormKey配置不正确");
                        }
                        //处理人选择方式  1该节点确认   2系统分配   3父节点确定
                        if (params.get("isAutoDispose") == null || "1".equals(params)) {
                            sysActivitiTaskServiceImpl.insert(new SysActivitiTask(IdWorker.getIdWorkerNext().toString(), null, null, null, null, IdWorker.getIdWorkerNext().toString()
                                    , serialNumber, urgencyDegree, task.getId(), runtimeServiceImpl.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getBusinessKey()
                                    , null, null, null, null, null, assignee, assignee != null
                                    && !"".equals(assignee) ? sysUserServiceImpl.selectByPrimaryKey(assignee).getAccountname() : null
                                    , null, null, model.getName(), model.getId(), task.getName(), "角色任务", "003", sysUser.getId(), sysUser.getAccountname(), new Date()
                                    , sysUser.getId(), sysUser.getAccountname(), params.get("ccCollege"), params.get("ccDept"), params.get("ccRole"), params.get("ccUnit"), params.get("ccBy1")
                                    , params.get("ccUserId"), params.get("ccUserName"), null));
                        }
                        //不能自行选择的情况
                        /*sysActivitiTaskServiceImpl.insert(new SysActivitiTask(IdWorker.getIdWorkerNext().toString(), null, null, null, null, IdWorker.getIdWorkerNext().toString()
                                , serialNumber, urgencyDegree, task.getId(), runtimeServiceImpl.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getBusinessKey()
                                , params.get("disposeCollege"), params.get("disposeDept"), params.get("disposeRole"), params.get("disposeUnit"), params.get("disposeBy1"), null, null
                                , null, null, model.getName(), model.getId(), task.getName(), "角色任务", "003", sysUser.getId(), sysUser.getAccountname(), new Date()
                                , null, null, params.get("ccCollege"), params.get("ccDept"), params.get("ccRole"), params.get("ccUnit"), params.get("ccBy1")
                                , params.get("ccUserId"), params.get("ccUserName"), null));*/
                        taskId = task.getId();
                    }
                }
            }
            task = taskServiceImpl.createTaskQuery().taskId(taskId).singleResult();
            if(task.getAssignee()==null||!task.getAssignee().equals(sysUser.getId())){
                throw new CustomException("该用户没有权限处理该流程。");
            }
            //这里可以判断一下该用户是否有权限完成这个任务
            boolean nextNodecheak = false;
            SysActivitiTask sysActivitiTask = null;
            if (task.getName().equals("出访科经办人审核材料")) {
                System.out.println("出访科经办人审核材料节点断点");
            }
            List<Map<String, Object>> nextNodeList = getNextNode(taskId, modelId, businessKey);
            for (Map<String, Object> item : nextNodeList) {
                if("出访科科长分配经办人".equals(item.get("nextNodeValue"))){
                    System.out.println("测试拦截点");
                }
                Map<String, String> params = (Map<String, String>) item.get("fromKey");
                if (params.get("isAutoDispose") == null || "1".equals(params.get("isAutoDispose"))) {
                    if (nextNode != null && !"".equals(nextNode) && nextNode.equals(item.get("nextNodeValue"))) {
                        //根据任务ID去完成任务
                        Map<String, Object> variables = new HashMap<>();
                        variables.put("assignee", assignee);//int
                        variables.put("nextNode", nextNode);
                        taskServiceImpl.complete(taskId, variables);
                        //更新流程任务
                        nextNodecheak = true;
                    }
                } else if ("2".equals(params.get("isAutoDispose"))) {
                    List<Map<String, Object>> assignees = (List<Map <String, Object>>) item.get("assignees");
                    String assigneeList = "";
                    for (Map<String, Object> imap : assignees) {
                        assigneeList = assigneeList + "," + imap.get("assigneeValue");
                    }
                    assigneeList = assigneeList.substring(1, assigneeList.length());
                    Map<String, Object> variables = new HashMap<>();
                    variables.put("assignee", assignee);//int
                    variables.put("nextNode", nextNode);
                    variables.put("assigneeList", Arrays.asList(assigneeList.split(",")));
                    taskServiceImpl.complete(taskId, variables);
                    nextNodecheak = true;
                }/*else if("3".equals(params.get("isAutoDispose"))){
                }*/
            }
            if (nextNodecheak) {
                sysActivitiTask = new SysActivitiTask();
                sysActivitiTask.setTaskId(taskId);
                sysActivitiTask = sysActivitiTaskServiceImpl.selectOne(sysActivitiTask);
                sysActivitiTask.setDisposeUserId(sysUser.getId());//办理人
                sysActivitiTask.setDisposeUserName(sysUser.getAccountname());//办理人姓名
                sysActivitiTask.setActivitiStatus("005");//流程状态
                sysActivitiTask.setContent(opinion);//意见
                //代办人 代办以后再处理
                //代办人姓名
                sysActivitiTaskServiceImpl.updateByPrimaryKeySelective(sysActivitiTask);


                //更新流程任务
                List<Task> tlist = taskServiceImpl.createTaskQuery().processInstanceBusinessKey(businessKey).list();
                if (tlist != null && tlist.size() > 0) {
                    for (Task item : tlist) {
                        if("出访科科长分配经办人".equals(item.getName())){
                            System.out.println("测试拦截点");
                        }
                        Map<String, String> params = null;
                        try {
                            params = item.getFormKey() == null || "".equals(item.getFormKey()) ? (Map<String, String>) JSON.parse(JSON.toJSONString(new SysActivitiFormKey(), SerializerFeature.WriteMapNullValue)) : (Map<String, String>) JSON.parse(item.getFormKey());
                            //检查一下这个任务id 是不是存在。不存在则添加
                            SysActivitiTask sysActivitiTask1 = new SysActivitiTask();
                            sysActivitiTask1.setTaskId(item.getId());
                            if (sysActivitiTaskServiceImpl.selectListByPage(sysActivitiTask1).size() <= 0) {
                                String name1 = item.getAssignee() != null && !"".equals(item.getAssignee()) ? item.getAssignee() : null;
                                String name2 = item.getAssignee() != null && !"".equals(item.getAssignee()) ? sysUserServiceImpl.selectByPrimaryKey(item.getAssignee()).getAccountname() : null;
                                sysActivitiTaskServiceImpl.insert(new SysActivitiTask(IdWorker.getIdWorkerNext().toString(), null, null, null, null, IdWorker.getIdWorkerNext().toString()
                                        , serialNumber, urgencyDegree, item.getId(), runtimeServiceImpl.createProcessInstanceQuery().processInstanceId(item.getProcessInstanceId()).singleResult().getBusinessKey()
                                        , params.get("disposeCollege"), params.get("disposeDept"), params.get("disposeRole"), params.get("disposeUnit"), params.get("disposeBy1"), name1, name2
                                        , null, null, model.getName(), model.getId(), item.getName(), "角色任务", "003", sysActivitiTask.getInitiateUserId(), sysActivitiTask.getInitiateUserName(), sysActivitiTask.getInitiateDate()
                                        , sysUser.getId(), sysUser.getAccountname(), params.get("ccCollege"), params.get("ccDept"), params.get("ccRole"), params.get("ccUnit"), params.get("ccBy1")
                                        , params.get("ccUserId"), params.get("ccUserName"), null));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new Exception("流程FormKey配置不正确");
                        }
                    }
                } else {
                    //把数据状态改成审结
                    System.out.println("没有下一节点任务");
                }
                return newHashmap(taskId, modelId, assignee, nextNode, comformInfo, comformId, businessKey, serialNumber, urgencyDegree);
            } else {
                throw new CustomException("节点审结失败！");
            }
        } catch (CustomException ce) {
            throw ce;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Map getprocessImageDate(String taskId, String modelId) {
        Map<String, Object> map = new HashMap();
        ProcessDefinition processDefinition = null;
        if (taskId == null || "".equals(taskId)) {
            Model model = repositoryServiceImpl.getModel(modelId);
            processDefinition = repositoryServiceImpl.createProcessDefinitionQuery().deploymentId(model.getDeploymentId()).singleResult();
            map.put("deploymentId", processDefinition.getDeploymentId()); // 部署id
            map.put("diagramResourceName", processDefinition.getDiagramResourceName());
            return map;
        }

        Task task = taskServiceImpl.createTaskQuery() // 创建任务查询
                .taskId(taskId)// 根据任务id查询
                .singleResult();
        String processDefinitionId = task.getProcessDefinitionId(); // 获取流程定义id
        processDefinition = repositoryServiceImpl.createProcessDefinitionQuery() // 创建流程定义查询
                .processDefinitionId(processDefinitionId) // 根据流程定义id查询
                .singleResult();
        map.put("deploymentId", processDefinition.getDeploymentId()); // 部署id
        map.put("diagramResourceName", processDefinition.getDiagramResourceName()); // 图片资源文件名称
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryServiceImpl.getProcessDefinition(processDefinitionId);
        String processInstanceId = task.getProcessInstanceId(); // 获取流程实例id
        ProcessInstance pi = runtimeServiceImpl.createProcessInstanceQuery() // 根据流程实例id获取流程实例
                .processInstanceId(processInstanceId)
                .singleResult();
        ActivityImpl activityImpl = processDefinitionEntity.findActivity(pi.getActivityId()); // 根据活动id获取活动实例
        map.put("x", activityImpl.getX()); // x坐标
        map.put("y", activityImpl.getY()); // y坐标
        map.put("width", activityImpl.getWidth()); // 宽度
        map.put("height", activityImpl.getHeight()); // 高度
        return map;
    }

    @Override
    public InputStream getProcessImage(String taskId) {
        String processDefinitionId = null; // 获取流程定义id
        String processInstanceId = null; // 获取流程实例id
        List<String> currentActs = Collections.EMPTY_LIST;//获取流程实例当前的节点，需要高亮显示
        BpmnModel model = null;
        String fontName = "宋体";

        //获得流程实例
        Task task = taskServiceImpl.createTaskQuery() // 创建任务查询
                .taskId(taskId) // 根据任务id查询
                .singleResult();
        if (task != null) {
            //processDefinitionId = task.getProcessDefinitionId(); // 获取流程定义id
            processInstanceId = task.getProcessInstanceId(); // 获取流程实例id
        } else {//尝试用模型id
            return processEngine.getProcessEngineConfiguration()
                    .getProcessDiagramGenerator()
                    .generateDiagram(repositoryServiceImpl.getBpmnModel(repositoryServiceImpl.createProcessDefinitionQuery().deploymentId(repositoryServiceImpl.getModel(taskId).getDeploymentId()).singleResult().getId()), "png", currentActs, new ArrayList<>(),
                            fontName, fontName, fontName, null, 1.0);
        }


        //ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryServiceImpl.getProcessDefinition(processDefinitionId);

        ProcessInstance processInstance = runtimeServiceImpl.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        //processDefinitionId = StringUtils.EMPTY;
        processDefinitionId = processInstance.getProcessDefinitionId();
        /*if (processInstance == null) {
            //查询已经结束的流程实例
            HistoricProcessInstance processInstanceHistory = historyServiceImpl.createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstanceId).singleResult();
            if (processInstanceHistory == null)
                return null;
            else
                processDefinitionId = processInstanceHistory.getProcessDefinitionId();
        } else {
            processDefinitionId = processInstance.getProcessDefinitionId();
        }*/

        //获取BPMN模型对象
        model = repositoryServiceImpl.getBpmnModel(processDefinitionId);

        if (processInstance != null)
            currentActs = runtimeServiceImpl.getActiveActivityIds(processInstance.getId());

        return processEngine.getProcessEngineConfiguration()
                .getProcessDiagramGenerator()
                .generateDiagram(model, "png", currentActs, new ArrayList<>(),
                        fontName, fontName, fontName, null, 1.0);
    }

    public Map<String, Object> newHashmap(String taskId, String modelId, String assignee, String nextNode, String comformInfo, String comformId, String businessKey, String serialNumber, String urgencyDegree) {
        Map<String, Object> map = new HashMap<>();
        map.put("taskId", taskId);
        map.put("assignee", assignee);
        map.put("nextNode", nextNode);
        map.put("comformInfo", comformInfo);
        map.put("comformId", comformId);
        map.put("businessKey", businessKey);
        map.put("modelId", modelId);
        map.put("serialNumber", serialNumber);
        map.put("urgencyDegree", urgencyDegree);
        return map;
    }


    /**
     * 流程撤回操作
     */
    public void execute(String taskId, String businessKey) {
        try {
            // 取得当前任务
            HistoricTaskInstance currTask = historyServiceImpl
                    .createHistoricTaskInstanceQuery().taskId(taskId)
                    .singleResult();
            //根据流程id查询代办任务中流程信息
            Task task = taskServiceImpl.createTaskQuery().processInstanceId(currTask.getProcessInstanceId()).singleResult();
            //查询下一个节点是否已经审批
            SysActivitiTask sysActivitiTask = new SysActivitiTask();
            sysActivitiTask.setBusinessKey(businessKey);
            /*List<SysActivitiTask> sysActivitiTasks = sysActivitiTaskServiceImpl.selectListByPage(sysActivitiTask);
            boolean bool = true;
            for (int i = 0; i < sysActivitiTasks.size(); i++) {
                if (taskId.equals(sysActivitiTasks.get(i).getTaskId())) {
                    if (sysActivitiTasks.size() - i == 2) {
                            bool = false;
                    }
                }
            }
            if (bool) {
                //System.out.println("下一节点信息已被审批，无法撤回流程。");
            }*/
            //取回流程接点 当前任务id 取回任务id
            callBackProcess(task.getId(), currTask.getId());
            //删除历史流程走向记录
            historyServiceImpl.deleteHistoricTaskInstance(currTask.getId());
            historyServiceImpl.deleteHistoricTaskInstance(task.getId());
            //System.out.println("流程取回成功");
        } catch (Exception e) {
            //System.out.println("流程取回失败，未知错误.");
        }
    }

    /**
     * 驳回流程
     *
     * @throws Exception 异常
     */
    public void backProcess() throws Exception {
        String taskId = "";//当前任务ID
        String activityId = "";//驳回节点ID
        Map<String, Object> variables = new HashMap<>();//流程存储参数

        if (org.springframework.util.StringUtils.isEmpty(activityId)) {
            throw new Exception("驳回目标节点ID为空！");
        }

        // 查找所有并行任务节点，同时驳回
        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(
                taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());
        for (Task task : taskList) {
            commitProcess(task.getId(), variables, activityId);
        }
    }


    /**
     * 取回流程
     *
     * @param taskId     当前任务ID
     * @param activityId 取回节点ID
     * @throws Exception 异常
     */


    public void callBackProcess(String taskId, String activityId)
            throws Exception {
        if (org.springframework.util.StringUtils.isEmpty(activityId)) {
            throw new Exception("目标节点ID为空！");
        }

        // 查找所有并行任务节点，同时取回
        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(
                taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());
        for (Task task : taskList) {
            commitProcess(task.getId(), null, activityId);
        }
    }


    /**
     * 清空指定活动节点流向
     *
     * @param activityImpl 活动节点
     * @return 节点流向集合
     */


    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
        // 存储当前节点所有流向临时变量
        // 获取当前节点所有流向，存储到临时变量，然后清空
        List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();
        List<PvmTransition> oriPvmTransitionList = new ArrayList<>(pvmTransitionList);
        pvmTransitionList.clear();
        return oriPvmTransitionList;
    }


    /**
     * 提交流程/流程转向
     *
     * @param taskId     当前任务ID
     * @param variables  流程变量
     * @param activityId 流程转向执行任务节点ID<br>
     *                   此参数为空，默认为提交操作
     * @throws Exception 异常
     */


    private void commitProcess(String taskId, Map<String, Object> variables,
                               String activityId) throws Exception {
        // 跳转节点为空，默认提交操作
        if (org.springframework.util.StringUtils.isEmpty(activityId)) {
            taskServiceImpl.complete(taskId, variables);
        } else {// 流程转向操作
            turnTransition(taskId, activityId, variables);
        }
    }


    /**
     * 中止流程(特权人直接审批通过等)
     *
     * @param taskId 任务id
     * @throws Exception 异常
     */
    public void endProcess(String taskId) throws Exception {
        ActivityImpl endActivity = findActivitiImpl(taskId, "end");
        commitProcess(taskId, null, endActivity.getId());
    }


    /**
     * 根据流入任务集合，查询最近一次的流入任务节点
     *
     * @param processInstance 流程实例
     * @param tempList        流入任务集合
     * @return 异常
     */
    private ActivityImpl filterNewestActivity(ProcessInstance processInstance,
                                              List<ActivityImpl> tempList) {
        while (tempList.size() > 0) {
            ActivityImpl activity_1 = tempList.get(0);
            HistoricActivityInstance activityInstance_1 = findHistoricUserTask(
                    processInstance, activity_1.getId());
            if (activityInstance_1 == null) {
                tempList.remove(activity_1);
                continue;
            }

            if (tempList.size() > 1) {
                ActivityImpl activity_2 = tempList.get(1);
                HistoricActivityInstance activityInstance_2 = findHistoricUserTask(
                        processInstance, activity_2.getId());
                if (activityInstance_2 == null) {
                    tempList.remove(activity_2);
                    continue;
                }

                if (activityInstance_1.getEndTime().before(
                        activityInstance_2.getEndTime())) {
                    tempList.remove(activity_1);
                } else {
                    tempList.remove(activity_2);
                }
            } else {
                break;
            }
        }
        if (tempList.size() > 0) {
            return tempList.get(0);
        }
        return null;
    }


    /**
     * 根据任务ID和节点ID获取活动节点 <br>
     *
     * @param taskId     任务ID
     * @param activityId 活动节点ID <br>
     *                   如果为null或""，则默认查询当前活动节点 <br>
     *                   如果为"end"，则查询结束节点 <br>
     * @return ActivityImpl
     * @throws Exception 异常
     */


    private ActivityImpl findActivitiImpl(String taskId, String activityId)
            throws Exception {
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);

        // 获取当前活动节点ID
        if (org.springframework.util.StringUtils.isEmpty(activityId)) {
            activityId = findTaskById(taskId).getTaskDefinitionKey();
        } else {
            HistoricTaskInstance currTask = historyServiceImpl
                    .createHistoricTaskInstanceQuery().taskId(activityId)
                    .singleResult();
            activityId = currTask.getTaskDefinitionKey();
        }

        // 根据流程定义，获取该流程实例的结束节点
        if (activityId.toUpperCase().equals("END")) {
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {
                List<PvmTransition> pvmTransitionList = activityImpl
                        .getOutgoingTransitions();
                if (pvmTransitionList.isEmpty()) {
                    return activityImpl;
                }
            }
        }

        // 根据节点ID，获取对应的活动节点
        return (/*(ProcessDefinitionImpl)*/ processDefinition).findActivity(activityId);
    }


    /*
      根据当前任务ID，查询可以驳回的任务节点

      @param taskId 当前任务ID
     */
    /*public List<ActivityImpl> findBackAvtivity(String taskId) throws Exception {
        List<ActivityImpl> rtnList = iteratorBackActivity(taskId, findActivitiImpl(taskId,
                null), new ArrayList<ActivityImpl>(),
                new ArrayList<ActivityImpl>());
        return reverList(rtnList);
    }*/

    /**
     * 查询指定任务节点的最新记录
     *
     * @param processInstance 流程实例
     * @param activityId      活动id
     * @return 历史活动实例
     */


    private HistoricActivityInstance findHistoricUserTask(
            ProcessInstance processInstance, String activityId) {
        HistoricActivityInstance rtnVal = null;
        // 查询当前流程实例审批结束的历史节点
        List<HistoricActivityInstance> historicActivityInstances = historyServiceImpl
                .createHistoricActivityInstanceQuery().activityType("userTask")
                .processInstanceId(processInstance.getId()).activityId(
                        activityId).finished()
                .orderByHistoricActivityInstanceEndTime().desc().list();
        if (historicActivityInstances.size() > 0) {
            rtnVal = historicActivityInstances.get(0);
        }

        return rtnVal;
    }

    /**
     * 根据当前节点，查询输出流向是否为并行终点，如果为并行终点，则拼装对应的并行起点ID
     *
     * @param activityImpl 当前节点
     * @return 异常
     */


    private String findParallelGatewayId(ActivityImpl activityImpl) {
        List<PvmTransition> incomingTransitions = activityImpl
                .getOutgoingTransitions();
        for (PvmTransition pvmTransition : incomingTransitions) {
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
            activityImpl = transitionImpl.getDestination();
            String type = (String) activityImpl.getProperty("type");
            if ("parallelGateway".equals(type)) {// 并行路线
                String gatewayId = activityImpl.getId();
                String gatewayType = gatewayId.substring(gatewayId
                        .lastIndexOf("_") + 1);
                if ("END".equals(gatewayType.toUpperCase())) {
                    return gatewayId.substring(0, gatewayId.lastIndexOf("_"))
                            + "_start";
                }
            }
        }
        return null;
    }

    /**
     * 根据任务ID获取流程定义
     *
     * @param taskId 任务ID
     * @return 流程实例实体对象
     * @throws Exception 异常
     */


    public ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
            String taskId) throws Exception {
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryServiceImpl)
                .getDeployedProcessDefinition(findTaskById(taskId)
                        .getProcessDefinitionId());

        if (processDefinition == null) {
            throw new Exception("流程定义未找到!");
        }

        return processDefinition;
    }

    /**
     * 根据任务ID获取对应的流程实例
     *
     * @param taskId 任务ID
     * @return 流程实例对象
     * @throws Exception 异常
     */
    public ProcessInstance findProcessInstanceByTaskId(String taskId) throws Exception {
        // 找到流程实例
        ProcessInstance processInstance = runtimeServiceImpl
                .createProcessInstanceQuery().processInstanceId(
                        findTaskById(taskId).getProcessInstanceId())
                .singleResult();
        if (processInstance == null) {
            throw new CustomException("流程实例未找到!");
        }
        return processInstance;
    }

    /**
     * 根据任务ID获得任务实例
     *
     * @param taskId 任务ID
     * @return 任务实体
     */
    private TaskEntity findTaskById(String taskId) throws Exception {
        TaskEntity task = (TaskEntity) taskServiceImpl.createTaskQuery().taskId(
                taskId).singleResult();
        if (task == null) {
            throw new Exception("任务实例未找到!");
        }
        return task;
    }


    /**
     * 根据流程实例ID和任务key值查询所有同级任务集合
     *
     * @param processInstanceId 流程实例id
     * @param key               流程实例key
     * @return 任务列表
     */
    private List<Task> findTaskListByKey(String processInstanceId, String key) {
        return taskServiceImpl.createTaskQuery().processInstanceId(processInstanceId).taskDefinitionKey(key).list();
    }


    /**
     * 迭代循环流程树结构，查询当前节点可驳回的任务节点
     *
     * @param taskId       当前任务ID
     * @param currActivity 当前活动节点
     * @param rtnList      存储回退节点集合
     * @param tempList     临时存储节点集合（存储一次迭代过程中的同级userTask节点）
     * @return 回退节点集合
     */
    private List<ActivityImpl> iteratorBackActivity(String taskId,
                                                    ActivityImpl currActivity, List<ActivityImpl> rtnList,
                                                    List<ActivityImpl> tempList) throws Exception {
        // 查询流程定义，生成流程树结构
        ProcessInstance processInstance = findProcessInstanceByTaskId(taskId);

        // 当前节点的流入来源
        List<PvmTransition> incomingTransitions = currActivity
                .getIncomingTransitions();
        // 条件分支节点集合，userTask节点遍历完毕，迭代遍历此集合，查询条件分支对应的userTask节点
        List<ActivityImpl> exclusiveGateways = new ArrayList<>();
        // 并行节点集合，userTask节点遍历完毕，迭代遍历此集合，查询并行节点对应的userTask节点
        List<ActivityImpl> parallelGateways = new ArrayList<>();
        // 遍历当前节点所有流入路径
        for (PvmTransition pvmTransition : incomingTransitions) {
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
            ActivityImpl activityImpl = transitionImpl.getSource();
            String type = (String) activityImpl.getProperty("type");
            /*
              并行节点配置要求：<br>
              必须成对出现，且要求分别配置节点ID为:XXX_start(开始)，XXX_end(结束)
             */
            if ("parallelGateway".equals(type)) {// 并行路线
                String gatewayId = activityImpl.getId();
                String gatewayType = gatewayId.substring(gatewayId
                        .lastIndexOf("_") + 1);
                if ("START".equals(gatewayType.toUpperCase())) {// 并行起点，停止递归
                    return rtnList;
                } else {// 并行终点，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点
                    parallelGateways.add(activityImpl);
                }
            } else if ("startEvent".equals(type)) {// 开始节点，停止递归
                return rtnList;
            } else if ("userTask".equals(type)) {// 用户任务
                tempList.add(activityImpl);
            } else if ("exclusiveGateway".equals(type)) {// 分支路线，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点
                currActivity = transitionImpl.getSource();
                exclusiveGateways.add(currActivity);
            }
        }

        /*
          迭代条件分支集合，查询对应的userTask节点
         */
        for (ActivityImpl activityImpl : exclusiveGateways) {
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);
        }

        /*
          迭代并行集合，查询对应的userTask节点
         */
        for (ActivityImpl activityImpl : parallelGateways) {
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);
        }

        /*
          根据同级userTask集合，过滤最近发生的节点
         */
        currActivity = filterNewestActivity(processInstance, tempList);
        if (currActivity != null) {
            // 查询当前节点的流向是否为并行终点，并获取并行起点ID
            String id = findParallelGatewayId(currActivity);
            if (org.springframework.util.StringUtils.isEmpty(id)) {// 并行起点ID为空，此节点流向不是并行终点，符合驳回条件，存储此节点
                rtnList.add(currActivity);
            } else {// 根据并行起点ID查询当前节点，然后迭代查询其对应的userTask任务节点
                currActivity = findActivitiImpl(taskId, id);
            }

            // 清空本次迭代临时集合
            tempList.clear();
            // 执行下次迭代
            iteratorBackActivity(taskId, currActivity, rtnList, tempList);
        }
        return rtnList;
    }


    /**
     * 还原指定活动节点流向
     *
     * @param activityImpl         活动节点
     * @param oriPvmTransitionList 原有节点流向集合
     */
    private void restoreTransition(ActivityImpl activityImpl,
                                   List<PvmTransition> oriPvmTransitionList) {
        // 清空现有流向
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        pvmTransitionList.clear();
        // 还原以前流向
        pvmTransitionList.addAll(oriPvmTransitionList);
    }

    /**
     * 反向排序list集合，便于驳回节点按顺序显示
     *
     * @param list List<ActivityImpl>
     * @return List<ActivityImpl>
     */
    private List<ActivityImpl> reverList(List<ActivityImpl> list) {
        List<ActivityImpl> rtnList = new ArrayList<>();
        // 由于迭代出现重复数据，排除重复
        for (int i = list.size(); i > 0; i--) {
            if (!rtnList.contains(list.get(i - 1)))
                rtnList.add(list.get(i - 1));
        }
        return rtnList;
    }

    /**
     * 转办流程
     *
     * @param taskId   当前任务节点ID
     * @param userCode 被转办人Code
     */
    public void transferAssignee(String taskId, String userCode) {
        taskServiceImpl.setAssignee(taskId, userCode);
    }

    /**
     * 流程转向操作
     *
     * @param taskId     当前任务ID
     * @param activityId 目标节点任务ID
     * @param variables  流程变量
     * @throws Exception 异常
     */
    private void turnTransition(String taskId, String activityId,
                                Map<String, Object> variables) throws Exception {
        // 当前节点
        ActivityImpl currActivity = findActivitiImpl(taskId, null);
        // 清空当前流向
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

        // 创建新流向
        TransitionImpl newTransition = currActivity.createOutgoingTransition();
        // 目标节点
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
        // 设置新流向的目标节点
        newTransition.setDestination(pointActivity);

        // 执行转向任务
        taskServiceImpl.complete(taskId, variables);
        // 删除目标节点新流入
        pointActivity.getIncomingTransitions().remove(newTransition);

        // 还原以前流向
        restoreTransition(currActivity, oriPvmTransitionList);
    }


}
