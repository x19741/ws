package spring;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.SneakyThrows;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import spring.entity.*;
import spring.exception.CustomException;
import spring.service.*;
import spring.service.impl.SysActivitiTaskServiceImpl;
import spring.service.impl.WsVisitCountryServiceImpl;
import spring.service.impl.WsVisitCountryUserServiceImpl;
import spring.util.*;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class test {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    TaskService taskServiceImpl;

    @Autowired
    RepositoryService repositoryServiceImpl;

    @Autowired
    RuntimeService runtimeServiceImpl;

    @Autowired
    HistoryService historyServiceImpl;

    @Autowired
    IdentityService identityServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ProcessService processServiceImpl;

    @Autowired
    ActivitiIdentityService activitiIdentityServiceImpl;

    @Autowired
    SysActivitiTaskServiceImpl sysActivitiTaskServiceImpl;

    @Autowired
    WsVisitCountryUserServiceImpl wsVisitCountryUserServiceImpl;

    @Autowired
    WsVisitCountryServiceImpl wsVisitCountryServiceImpl;

    @Autowired
    org.apache.shiro.mgt.SecurityManager securityManager;

    public MockMvc mockMvc;

    @Before
    public void before() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //设置ApplicationContext
        ApplicationContextUtil.getInstance().setApplicationContext(applicationContext);
        //设置登录用户
        SecurityUtils.setSecurityManager(securityManager);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        login("admin", Md5Util.EncoderByMd5("1"));
    }

    private void login(String username, String password) {

        final UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        final Subject subject = SecurityUtils.getSubject();

        subject.login(token);
    }


    /**
     * 因公出国流程2
     */
    @Test
    public void cese2() {

        List<String> listMassage = new ArrayList<>();
        try {
            listMassage.add("删除流程实例");
            List<ProcessInstance> listhi = runtimeServiceImpl.createProcessInstanceQuery().list();
            if (null != listhi && listhi.size() > 0) {
                for (ProcessInstance pr : listhi) {
                    runtimeServiceImpl.deleteProcessInstance(pr.getProcessInstanceId(), "删除原因");//删除流程
                }
            }

            listMassage.add("删除流程任务");
            SysActivitiTask sysActivitiTask=new SysActivitiTask();
            sysActivitiTask.setCreateBy("test1");
            sysActivitiTaskServiceImpl.delete(sysActivitiTask);

            listMassage.add("删除表单内容");
            WsVisitCountryUser wsVisitCountryUser=new WsVisitCountryUser();
            wsVisitCountryUser.setCreateBy("test1");
            wsVisitCountryUserServiceImpl.delete(wsVisitCountryUser);
            WsVisitCountry wsVisitCountry=new WsVisitCountry();
            wsVisitCountry.setCreateBy("test1");
            wsVisitCountryServiceImpl.delete(wsVisitCountry);

            //申请人/代填人填写团组信息
            Map<String,Object> cmap=new HashMap<>();
            cmap.put("taskId","");//任务ID
            cmap.put("Assignee","");//下一节点处理人
            cmap.put("nextNode","");//下一节点
            cmap.put("comformInfo","{\"formId\":\"1316844061495603200\",\"date\":{\"createBy\":\"1\"},\"mainformdates\":[{\"formId\":\"1316844695280103424\",\"dates\":[{\"date\":{\"createBy\":\"1\",\"userId\":\"01\"}},{\"date\":{\"createBy\":\"1\",\"userId\":\"02\"}}]}]}");//资料
            cmap.put("comformId","1316844061495603200");//资料formid
            cmap.put("businessKey","");//业务ID(表单ID)
            cmap.put("modelId","492501");//模型ID
            cmap.put("opinion","");//意见
            cmap.put("serialNumber",IdWorker.getIdWorkerNext().toString());//流水号
            cmap.put("urgencyDegree","1");//紧急程度
            cmap=processServiceImpl.completeTask((String)cmap.get("taskId"), (String)cmap.get("modelId"), (String)cmap.get("Assignee"), (String)cmap.get("nextNode")
                    , (String)cmap.get("comformInfo"),(String)cmap.get("comformId"), (String)cmap.get("businessKey"), (String)cmap.get("opinion"), (String)cmap.get("serialNumber"), (String)cmap.get("urgencyDegree"));
            listMassage.add("申请人/代填人填写团组信息："+JSON.toJSONString(cmap,SerializerFeature.WriteMapNullValue));
            listMassage.add("####################");
            listMassage.add("查询下一节点信息");

            List<Task> list = taskServiceImpl.createTaskQuery().processInstanceBusinessKey((String)cmap.get("businessKey")).orderByTaskCreateTime().desc().list();
            if (null != list && list.size() > 0) {
                for (Task task : list) {
                    listMassage.add("任务ID:" + task.getId()+",任务办理人:" + task.getAssignee()+",执行实例ID:" + task.getExecutionId()+",任务名称:"
                            + task.getName()+",formKey:" + task.getFormKey()+",流程定义ID:" + task.getProcessDefinitionId()
                            +",流程实例ID:" + task.getProcessInstanceId()+",任务创建时间:" + task.getCreateTime());
                }
            } else {
                throw new CustomException("任务列表为空！");
            }







            /*Map<String, Object> variables = new HashMap<>();
            //variables.put("assignee", "test1");
            variables.put("nextNode", "");
            //variables.put("assigneelList", "");

            Model model = repositoryServiceImpl.getModel("492501");
            String processDefinitionId = repositoryServiceImpl.createProcessDefinitionQuery().deploymentId(model.getDeploymentId()).singleResult().getId();
            String businessKey = IdWorker.getIdWorkerNext().toString();
            ProcessInstance processInstance = runtimeServiceImpl.startProcessInstanceById(processDefinitionId, businessKey, variables);

            Map<String, Object> map = new HashMap<>();
            map.put("assigneeList", "jjh,yxc");

            taskServiceImpl.complete(taskServiceImpl.createTaskQuery().processInstanceBusinessKey(businessKey).orderByTaskCreateTime().desc().singleResult().getId(), map);
            List<Task> list = taskServiceImpl.createTaskQuery().processInstanceBusinessKey(businessKey).orderByTaskCreateTime().desc().list();
            //		.listPage(firstResult, maxResults)
            //		.count();
            //		.singleResult()


            variables = new HashMap<>();
            //4个部门审核，生成4个流程，集合中获取并存放的是一级评审人
            List<String> assigneeList = Arrays.asList(new String[]{"1", "2", "3", "4"});
            variables.put("assigneeList", assigneeList);

            if (null != list && list.size() > 0) {
                for (Task task : list) {
                    System.out.println("任务ID:" + task.getId());
                    System.out.println("任务办理人:" + task.getAssignee());
                    System.out.println("执行实例ID:" + task.getExecutionId());
                    System.out.println("任务名称:" + task.getName());
                    System.out.println("formKey:" + task.getFormKey());
                    System.out.println("流程定义ID:" + task.getProcessDefinitionId());
                    System.out.println("流程实例ID:" + task.getProcessInstanceId());
                    System.out.println("任务创建时间:" + task.getCreateTime());
                    System.out.println("####################");
                    taskServiceImpl.complete(task.getId(), variables);
                }
            } else {
                throw new CustomException("任务列表为空！");
            }


            list = taskServiceImpl.createTaskQuery().processInstanceBusinessKey(businessKey).orderByTaskCreateTime().desc().list();

            if (null != list && list.size() > 0) {
                for (Task task : list) {
                    System.out.println("任务ID:" + task.getId());
                    System.out.println("任务办理人:" + task.getAssignee());
                    System.out.println("执行实例ID:" + task.getExecutionId());
                    System.out.println("任务名称:" + task.getName());
                    System.out.println("formKey:" + task.getFormKey());
                    System.out.println("流程定义ID:" + task.getProcessDefinitionId());
                    System.out.println("流程实例ID:" + task.getProcessInstanceId());
                    System.out.println("任务创建时间:" + task.getCreateTime());
                    System.out.println("####################");
                }
            } else {
                throw new CustomException("任务列表为空！");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String str :listMassage) {
            System.out.println(str);
        }
    }


    /**
     * 查询我的个人任务act_ru_task
     */
    @Test
    public void queryMyTask1() {
        TaskService taskService = taskServiceImpl;
        String assignee = "ZHANSAN";
        List<Task> list = taskService.createTaskQuery()
                //条件
                .taskAssignee(assignee)//根据任务办理人查询任务
                //.taskId("275005")
//		.deploymentId(deploymentId)//根据部署ID查询 where id=id
//		.deploymentIdIn(deploymentIds)//根据部署ID集合查询   where id in (1,2,3,4)
//		.executionId(executionId)//根据执行实例ID
//		.processDefinitionId(processDefinitionId)//根据流程定义ID
//		.processDefinitionKey(processDefinitionKey)//根据流程定义的key
//		.processDefinitionKeyIn(processDefinitionKeys)
//		.processDefinitionKeyLike(processDefinitionKeyLike)
//		.processDefinitionName(processDefinitionName)
//		.processDefinitionNameLike(processDefinitionNameLike)
//		.processInstanceBusinessKey(processInstanceBusinessKey)
                //排序
                .orderByTaskCreateTime().desc()
                //结果集
                .list();
//		.listPage(firstResult, maxResults)
//		.count();
//		.singleResult()

        if (null != list && list.size() > 0) {
            for (Task task : list) {
                System.out.println("任务ID:" + task.getId());
                System.out.println("任务办理人:" + task.getAssignee());
                System.out.println("执行实例ID:" + task.getExecutionId());
                System.out.println("任务名称:" + task.getName());
                System.out.println("formKey:" + task.getFormKey());
                System.out.println("流程定义ID:" + task.getProcessDefinitionId());
                System.out.println("流程实例ID:" + task.getProcessInstanceId());
                System.out.println("任务创建时间:" + task.getCreateTime());
                System.out.println("####################");
            }
        }
    }

    @Test
    public void ssssss() {
        Map<String, Object> map = new HashMap<>();
        map.put("assigneeList", "jjh,yxc");


// 完成任务
        taskServiceImpl.complete("552520", map);
        /*Task task = taskServiceImpl.createTaskQuery().processInstanceBusinessKey(businessKey).orderByTaskCreateTime().desc().singleResult();
        taskServiceImpl.complete(task.getId(), map);
        task = taskServiceImpl.createTaskQuery().processInstanceBusinessKey(businessKey).orderByTaskCreateTime().desc().singleResult();

                System.out.println("任务ID:" + task.getId());
                System.out.println("任务办理人:" + task.getAssignee());
                System.out.println("执行实例ID:" + task.getExecutionId());
                System.out.println("任务名称:" + task.getName());
                System.out.println("formKey:" + task.getFormKey());
                System.out.println("流程定义ID:" + task.getProcessDefinitionId());
                System.out.println("流程实例ID:" + task.getProcessInstanceId());
                System.out.println("任务创建时间:" + task.getCreateTime());
                System.out.println("####################");*/
    }

    /**
     * 因公出国流程
     */
    @Test
    public void cese1() {
        List<String> listMassage = new ArrayList<>();
        try {
            //13.删除实例
            List<ProcessInstance> listhi = runtimeServiceImpl.createProcessInstanceQuery().list();
            listMassage.add("13.删除实例");
            if (null != listhi && listhi.size() > 0) {
                for (ProcessInstance pr : listhi) {
                    listMassage.add("执行实例ID:" + pr.getId());
                    listMassage.add("执行实例ID:" + pr.getProcessInstanceId());
                    listMassage.add("流程定义ID:" + pr.getProcessDefinitionId());
                    listMassage.add("########################");
                    runtimeServiceImpl.deleteProcessInstance(pr.getProcessInstanceId(), "删除原因");//删除流程
                }
            }

            //1.设定基础参数
            String modelId = "10002";
            String taskId = "";
            String assignee = "";
            String nextNode = "";
            String comformInfo = "";
            String opinion = "测试意见。";
            String businessKey = IdWorker.getIdWorkerNext().toString();
            Map<String, Object> mapNode = null;//选择节点
            Map<String, String> mapAssignees = null;//选择处理人

            listMassage.add("1.设置基础参数");
            listMassage.add("modelId:" + modelId);
            listMassage.add("taskId:" + taskId);
            listMassage.add("assignee:" + assignee);
            listMassage.add("nextNode:" + nextNode);
            listMassage.add("comformInfo:" + comformInfo);
            listMassage.add("opinion:" + opinion);
            System.out.println("########################");

            //申请人填报

            //2.查看下一节点及处理人
            Task task = taskServiceImpl.createTaskQuery().processInstanceBusinessKey(businessKey).orderByTaskCreateTime().desc().singleResult();
            if (task != null)
                taskId = task.getId();
            else
                taskId = "";
            List<Map<String, Object>> list = processServiceImpl.getNextNode(taskId, modelId, businessKey);
            listMassage.add("1、申请人填报");
            listMassage.add("获取节点信息：" + JSON.toJSONString(list, SerializerFeature.WriteMapNullValue));

            //3.选择节点与处理人
            if (list == null || list.size() <= 0) {
                throw new Exception("获取下一节点处理人");
            } //获取节点失败结束流程

            mapNode = list.get(0/*new Random().nextInt(list.size())*/);//选择节点

            if ("Event".equals(mapNode.get("flowElementType"))) {//结束节点
                Map<String, Object> stringObjectMap = processServiceImpl.completeTask(taskId, modelId, assignee, nextNode, comformInfo, null, businessKey
                        , opinion, "流水号", "1");
                listMassage.add("流程审结！");
                return;
            } else if (((List<Map<String, String>>) mapNode.get("assignees")) == null || ((List<Map<String, String>>) mapNode.get("assignees")).size() <= 0) {
                listMassage.add("没有找到下一节点或节点处理人，流程结束");
                return;//获取节点失败结束流程
            }
            //选择流程处理人
            mapAssignees = (Map<String, String>) ((List<Map<String, String>>) mapNode.get("assignees")).get(new Random().nextInt(((List<Map<String, String>>) mapNode.get("assignees")).size()));

            System.out.println("随机选择 " + "节点name：" + mapNode.get("nextNodeName") + "，节点value：" + mapNode.get("nextNodeValue") + "，节点处理人name：" + mapAssignees.get("assigneeName") + "，节点处理人value：" + mapAssignees.get("assigneeValue"));
            listMassage.add("随机选择 " + "节点name：" + mapNode.get("nextNodeName") + "，节点value：" + mapNode.get("nextNodeValue") + "，节点处理人name：" + mapAssignees.get("assigneeName") + "，节点处理人value：" + mapAssignees.get("assigneeValue"));
            System.out.println("#################################################################################");
            //4.办理任务
            assignee = mapAssignees.get("assigneeValue");
            nextNode = (String) mapNode.get("nextNodeValue");
            if (nextNode.equals("测试流程")) { //默认走第一条线
                nextNode = "外专科/交流科工作人员确认";
            }

            Map<String, Object> stringObjectMap = processServiceImpl.completeTask(taskId, modelId, assignee, nextNode, comformInfo, null, businessKey, opinion, "流水号", "1");

            for (String a : stringObjectMap.keySet()) {
                System.out.print(a + ":" + stringObjectMap.get(a));

            }
            System.out.println("");
            System.out.println("#################################################################################");
            //5.登录下一个节点处理人

            //下面开始第二个节点
            login(assignee, Md5Util.EncoderByMd5("1"));


            task = taskServiceImpl.createTaskQuery().processInstanceBusinessKey(businessKey).orderByTaskCreateTime().desc().singleResult();
            listMassage.add(task.getName());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (String item : listMassage) {
                System.out.println(item);
            }
        }


    }

    /**
     * 涉外活动测试用例
     *
     * @throws Exception
     */
    @Test
    public void cese() throws Exception {
        List<String> listMassage = new ArrayList<>();
        try {


            //13.删除实例
            List<ProcessInstance> listhi = runtimeServiceImpl.createProcessInstanceQuery().list();
            listMassage.add("13.删除实例");
            if (null != listhi && listhi.size() > 0) {
                for (ProcessInstance pr : listhi) {
                    listMassage.add("执行实例ID:" + pr.getId());
                    listMassage.add("执行实例ID:" + pr.getProcessInstanceId());
                    listMassage.add("流程定义ID:" + pr.getProcessDefinitionId());
                    listMassage.add("########################");
                    runtimeServiceImpl.deleteProcessInstance(pr.getProcessInstanceId(), "删除原因");//删除流程
                }
            }

            //1.设定基础参数
            String modelId = "10002";
            String taskId = "";
            String assignee = "";
            String nextNode = "";
            String comformInfo = "";
            String opinion = "测试意见。";
            String businessKey = IdWorker.getIdWorkerNext().toString();
            Map<String, Object> mapNode = null;//选择节点
            Map<String, String> mapAssignees = null;//选择处理人

            listMassage.add("1.设置基础参数");
            listMassage.add("modelId:" + modelId);
            listMassage.add("taskId:" + taskId);
            listMassage.add("assignee:" + assignee);
            listMassage.add("nextNode:" + nextNode);
            listMassage.add("comformInfo:" + comformInfo);
            listMassage.add("opinion:" + opinion);
            System.out.println("########################");

            while (true) {
                //2.查看下一节点及处理人
                Task task = taskServiceImpl.createTaskQuery()
                        .processInstanceBusinessKey(businessKey)
                        .orderByTaskCreateTime().desc()
                        .singleResult();
                if (task != null)
                    taskId = task.getId();
                else
                    taskId = "";
                List<Map<String, Object>> list = processServiceImpl.getNextNode(taskId, modelId, businessKey);
                listMassage.add("2.查看下一节点及处理人");
                listMassage.add("获取节点信息：" + JSON.toJSONString(list, SerializerFeature.WriteMapNullValue));

                //3.选择节点与处理人
                if (list == null || list.size() <= 0) {

                    break;
                } //获取节点失败结束流程

                mapNode = list.get(0/*new Random().nextInt(list.size())*/);//选择节点

                if ("Event".equals(mapNode.get("flowElementType"))) {//结束节点

                    Map<String, Object> stringObjectMap = processServiceImpl.completeTask(taskId, modelId, assignee, nextNode, comformInfo, null, businessKey
                            , opinion, "流水号", "1");
                    listMassage.add("流程审结！");
                    break;
                } else if (((List<Map<String, String>>) mapNode.get("assignees")) == null || ((List<Map<String, String>>) mapNode.get("assignees")).size() <= 0) {
                    listMassage.add("没有找到下一节点或节点处理人，流程结束");
                    break;
                }//获取节点失败结束流程
                mapAssignees = (Map<String, String>) ((List<Map<String, String>>) mapNode.get("assignees")).get(new Random().nextInt(((List<Map<String, String>>) mapNode.get("assignees")).size()));
                ;
                System.out.println("随机选择 " + "节点name：" + mapNode.get("nextNodeName") + "，节点value：" + mapNode.get("nextNodeValue") + "，节点处理人name：" + mapAssignees.get("assigneeName") + "，节点处理人value：" + mapAssignees.get("assigneeValue"));
                listMassage.add("随机选择 " + "节点name：" + mapNode.get("nextNodeName") + "，节点value：" + mapNode.get("nextNodeValue") + "，节点处理人name：" + mapAssignees.get("assigneeName") + "，节点处理人value：" + mapAssignees.get("assigneeValue"));
                System.out.println("#################################################################################");
                //4.办理任务
                assignee = mapAssignees.get("assigneeValue");
                nextNode = (String) mapNode.get("nextNodeValue");
                if (nextNode.equals("测试流程")) { //默认走第一条线
                    nextNode = "外专科/交流科工作人员确认";
                }

                Map<String, Object> stringObjectMap = processServiceImpl.completeTask(taskId, modelId, assignee, nextNode, comformInfo, null, businessKey, opinion, "流水号", "1");
                for (String a : stringObjectMap.keySet()) {
                    System.out.print(a + ":" + stringObjectMap.get(a));

                }
                System.out.println("");
                System.out.println("#################################################################################");
                //5.登录下一个节点处理人
                login(assignee, Md5Util.EncoderByMd5("1"));
            }


            //6.查看个人任务（随机事件）
        /*Task task = taskServiceImpl.createTaskQuery()
                //条件
                .processInstanceBusinessKey(businessKey)
                //排序
                .orderByTaskCreateTime().desc()
//		.listPage(firstResult, maxResults)
//		.count();
                //结果集
                .singleResult();

        System.out.println("任务ID:" + task.getId());
        System.out.println("任务办理人:" + task.getAssignee());
        System.out.println("执行实例ID:" + task.getExecutionId());
        System.out.println("任务名称:" + task.getName());
        System.out.println("formKey:" + task.getFormKey());
        System.out.println("流程定义ID:" + task.getProcessDefinitionId());
        System.out.println("流程实例ID:" + task.getProcessInstanceId());
        System.out.println("任务创建时间:" + task.getCreateTime());
        System.out.println("####################");*/

            //7.退回任务（随机事件）

            //8.驳回任务（随机事件）

            //9.查看意见记录

            //10.查看处理历史

            //11.查看个人已办任务

            //12.查看实例


        } catch (CustomException ce) {
            ce.printStackTrace();
            listMassage.add(ce.getMessage());
        } finally {
            for (String item : listMassage) {
                System.out.println(item);
            }
        }

    }


    /**
     * 根据modelId获取processDefinitionId
     */
    @Test
    public void ProcessInstanceByModelId() {
        String modelId = "10002";
        Model modelData = repositoryServiceImpl.getModel(modelId);
        ProcessDefinition processDefinition = repositoryServiceImpl.createProcessDefinitionQuery().deploymentId(modelData.getDeploymentId()).singleResult();
        System.out.println(processDefinition.getId());
        System.out.println(repositoryServiceImpl.createProcessDefinitionQuery().deploymentId(repositoryServiceImpl.getModel(modelId).getDeploymentId()).singleResult().getId());
    }






    //开启流程
    @Test
    public void startProcessInstanceByKey() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("请假天数", 5);//int
        variables.put("请假原因", "约会");
        variables.put("请假时间", new Date());
        //ProcessInstance processInstance = runtimeServiceImpl.startProcessInstanceById("process:10:72525");
        ProcessInstance processInstance = runtimeServiceImpl.startProcessInstanceByKey("process", "uuid2", variables);
        System.out.println(processInstance.getProcessInstanceId());
        //根据ProcessInstanceId获取teakId

        List<Task> list = taskServiceImpl.createTaskQuery().processDefinitionId(processInstance.getProcessDefinitionId()).processInstanceBusinessKey("uuid2").orderByTaskCreateTime().desc().list();
        if (null != list && list.size() > 0) {
            for (Task task : list) {
                System.out.println("任务ID:" + task.getId());
                System.out.println("任务办理人:" + task.getAssignee());
                System.out.println("执行实例ID:" + task.getExecutionId());
                System.out.println("任务名称:" + task.getName());
                System.out.println("流程定义ID:" + task.getProcessDefinitionId());
                System.out.println("流程实例ID:" + task.getProcessInstanceId());
                System.out.println("任务创建时间:" + task.getCreateTime());
                System.out.println("####################");
            }
        }
        System.out.println("流程开启成功");
    }


    /**
     * 查询我的个人任务act_ru_task
     */
    @Test
    public void queryMyTask() {
        TaskService taskService = taskServiceImpl;
        String assignee = "李四";
        List<Task> list = taskService.createTaskQuery()
                //条件
                .taskAssignee(assignee)//根据任务办理人查询任务
                //.taskId("275005")
                //.processInstanceBusinessKey("1319313213862772736")
//		.deploymentId(deploymentId)//根据部署ID查询 where id=id
//		.deploymentIdIn(deploymentIds)//根据部署ID集合查询   where id in (1,2,3,4)
//		.executionId(executionId)//根据执行实例ID
//		.processDefinitionId(processDefinitionId)//根据流程定义ID
//		.processDefinitionKey(processDefinitionKey)//根据流程定义的key
//		.processDefinitionKeyIn(processDefinitionKeys)
//		.processDefinitionKeyLike(processDefinitionKeyLike)
//		.processDefinitionName(processDefinitionName)
//		.processDefinitionNameLike(processDefinitionNameLike)
//		.processInstanceBusinessKey(processInstanceBusinessKey)
                //排序
                .orderByTaskCreateTime().desc()
                //结果集
                .list();
//		.listPage(firstResult, maxResults)
//		.count();
//		.singleResult()

        if (null != list && list.size() > 0) {
            for (Task task : list) {
                System.out.println("任务ID:" + task.getId());
                System.out.println("任务办理人:" + task.getAssignee());
                System.out.println("执行实例ID:" + task.getExecutionId());
                System.out.println("任务名称:" + task.getName());
                System.out.println("formKey:" + task.getFormKey());
                System.out.println("流程定义ID:" + task.getProcessDefinitionId());
                System.out.println("流程实例ID:" + task.getProcessInstanceId());
                System.out.println("任务创建时间:" + task.getCreateTime());
                System.out.println("####################");
            }
        }
    }


    @SneakyThrows
    @Test
    public void getNextNode() {
        String taskId = "";
        String modelId = "492501";
        List<Map<String, Object>> list = processServiceImpl.getNextNode(taskId, modelId, "");
        for (Map<String, Object> map : list) {
            System.out.println("节点name：" + map.get("nextNodeName"));
            System.out.println("节点value：" + map.get("nextNodeValue"));
            List<Map<String, String>> list1 = (List<Map<String, String>>) map.get("assignees");
            if (list1 != null && list1.size() > 0) {
                for (Map<String, String> item : list1) {
                    System.out.println("节点处理人name：" + item.get("assigneeName") + "," + "节点处理人value：" + item.get("assigneeValue"));
                }
            }
        }
    }

    /**
     * 办理任务
     */
    @Test
    public void completeTask() throws Exception {

        //办理任务需要的参数
        String taskId = "322525";
        String Assignee = "admin";
        String nextNode = "end1";
        String comformInfo = "{}";

        //新增流程需要的参数
        String uuid = IdWorker.getIdWorkerNext().toString();
        String businessKey = uuid;
        String modelId = "10002";
        String opinion = "测试意见。";
        Map<String, Object> stringObjectMap = processServiceImpl.completeTask(taskId, modelId, Assignee, nextNode, comformInfo, null, businessKey, opinion, "流水号", "1");
        for (String a : stringObjectMap.keySet()) {
            System.out.println(a + ":" + stringObjectMap.get(a));
        }
        System.out.println("任务完成");
    }


    /**
     * 7，附加功能，查询历史任务（后面讲）act_hi_taskinst
     */
    @Test
    public void queryHistoryTask() {
        HistoryService historyService = historyServiceImpl;
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().orderByHistoricTaskInstanceEndTime().asc()//排序
                .list();
        if (null != list && list.size() > 0) {
            for (HistoricTaskInstance hi : list) {
                System.out.println("任务ID:" + hi.getId());
                System.out.println("任务办理人:" + hi.getAssignee());
                System.out.println("执行实例ID:" + hi.getExecutionId());
                System.out.println("任务名称:" + hi.getName());
                System.out.println("流程定义ID:" + hi.getProcessDefinitionId());
                System.out.println("流程实例ID:" + hi.getProcessInstanceId());
                System.out.println("任务创建时间:" + hi.getCreateTime());
                System.out.println("任务结束时间:" + hi.getEndTime());
                System.out.println("任务持续时间:" + hi.getDurationInMillis());
                System.out.println("####################");
            }
        }

    }


    /**
     * 8，附加功能，查询历史流程实例（后面讲）
     */
    @Test
    public void queryHistoryProcessInstance() {
        HistoryService historyService = historyServiceImpl;
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().list();
        if (null != list && list.size() > 0) {
            for (HistoricProcessInstance hi : list) {
                System.out.println("执行实例ID:" + hi.getId());
                System.out.println("流程定义ID:" + hi.getProcessDefinitionId());
                System.out.println("流程启动时间:" + hi.getStartTime());

                System.out.println("########################");

            }
        }
    }

    /**
     * 8，附加功能，查询历史任务
     */
    @Test
    public void queryHistoricTaskInstance() {
        HistoryService historyService = historyServiceImpl;
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskId("120004").list();
        if (null != list && list.size() > 0) {
            for (HistoricTaskInstance hi : list) {
                System.out.println("执行实例ID:" + hi.getId());
                System.out.println("事件名称：" + hi.getName());
                System.out.println("处理人：" + hi.getAssignee());
                System.out.println("流程定义ID:" + hi.getProcessDefinitionId());
                System.out.println("流程启动时间:" + hi.getStartTime());
                System.out.println("########################");

            }
        }
    }


    /**
     * 查询流程实例
     */
    @Test
    public void createProcessInstanceQuery() {
        List<ProcessInstance> list = runtimeServiceImpl.createProcessInstanceQuery().list();
        if (null != list && list.size() > 0) {
            for (ProcessInstance pr : list) {
                System.out.println("执行实例ID:" + pr.getId());
                System.out.println("执行实例ID:" + pr.getProcessInstanceId());
                System.out.println("流程定义ID:" + pr.getProcessDefinitionId());

                System.out.println("########################");
            }
        }


    }


    /**
     * 删除流程实例
     */
    @Test
    public void deleteProcessInstance() {
        List<ProcessInstance> list = runtimeServiceImpl.createProcessInstanceQuery().list();
        if (null != list && list.size() > 0) {
            for (ProcessInstance pr : list) {

                System.out.println("执行实例ID:" + pr.getId());
                System.out.println("执行实例ID:" + pr.getProcessInstanceId());
                System.out.println("流程定义ID:" + pr.getProcessDefinitionId());

                System.out.println("########################");
                runtimeServiceImpl.deleteProcessInstance(pr.getProcessInstanceId(), "删除原因");//删除流程
            }
        }


    }


    /**
     * 获取所有的模型
     *
     * @return
     */
    @Test
    public void modelList() {
        HashMap<String, Object> result = new HashMap<>();
        List<Model> models = repositoryServiceImpl.createModelQuery().orderByCreateTime().asc().list();
        for (Model item : models) {
            System.out.println(item);
        }

    }


    /**
     * 获取模型中所有的节点
     * @return
     */
    /*@Test
    public void getModelImfo() throws IOException {
        JsonNode modelNode = null;
        String MODEL_ID="10002";
        String MODEL_NAME="name";
        Model model = repositoryServiceImpl.getModel("10002");
        byte[]   modelEditorSource = repositoryServiceImpl.getModelEditorSource(model.getId());
        modelNode = new  ObjectMapper().readTree(modelEditorSource);

        BpmnModel model1= new BpmnJsonConverter().convertToBpmnModel(modelNode);
        Process process=model1.getProcesses().get(0);
        Collection<FlowElement> flowElements=process.getFlowElements();
        for (FlowElement flowElement: flowElements) {

            if(flowElement instanceof UserTask){
                UserTask userTask=(UserTask)flowElement;
                if(userTask!=null){
                    System.out.println(userTask.getName());

                    break;
                }
            }
        }
    }*/
    /**
     * 获取模型中所有的节点2
     * @return
     */
    /*@Test
    public void getCurrentNextUserTaskAssign(){
        //流程定义Id
        String processDefId="process:1:7";
        //当前流程节点Id,在任务表里面对应TASK_DEF_KEY_字段，可以根据taskId获取该字段数据
        String flowElemetId = "leader";
        BpmnModel bpmnModel = repositoryServiceImpl.getBpmnModel("process");//   .getBpmnModel(processDefId);
        Process process = bpmnModel.getProcesses().get(0);
        //获取所有普通任务节点
        List<UserTask> UserTaskList = process.findFlowElementsOfType(UserTask.class);
        for(UserTask userTask:UserTaskList){
            //获取当前任务节点Id
            String id  = userTask.getId();
            if(id.equals(flowElemetId)){
                //获取当前任务节点的所有出线信息
                List<SequenceFlow> outgoingFlows = userTask.getOutgoingFlows();
                for(SequenceFlow sequenceFlow:outgoingFlows){
                    //获取出线连接的目标节点
                    FlowElement targetFlowElement = sequenceFlow.getTargetFlowElement();
                    //获取到了下一个节点的Id
                    String nextId = targetFlowElement.getId();
                    List<UserTask> UserTaskLists = process.findFlowElementsOfType(UserTask.class);
                    //再次遍历所有普通任务节点
                    for(UserTask userTasks:UserTaskLists) {
                        //获取任务节点Id
                        String flowId = userTasks.getId();
                        //如果遍历的某个任务节点Id等于下一个节点的Id
                        if (flowId.equals(nextId)) {
                            //获取下一个任务节点的执行人
                            String assignee = userTasks.getAssignee();
                            System.out.println("下一个节点的执行人:"+assignee);
                        }
                    }
                }
            }
        }
    }*/


    /**
     * 创建用户组
     */
    @Test
    public void saveUser() {


        SysUser user = new SysUser();
        user.setId("1");
        user.setAccountname("小明");

        List<SysRole> rlist = new ArrayList<>();
        SysRole sysRole = new SysRole();
        sysRole.setId("1");
        sysRole.setRoleName("测试角色");
        GroupEntity groupEntity = activitiIdentityServiceImpl.saveGroup(sysRole);

        rlist.add(sysRole);
        user.setRlist(rlist);

        UserEntity userEntity = activitiIdentityServiceImpl.saveUser(user);

        //activitiIdentityServiceImpl.deleteUser("1");
        //activitiIdentityServiceImpl.deleteGroup("1");



        /*UserEntity userEntity = new UserEntity("2");
        userEntity.setFirstName("小你好");
        identityServiceImpl.saveUser(userEntity);

        User user = identityServiceImpl.createUserQuery().userId("2").singleResult();
        if(user!=null){
            System.out.println("用户id"+user.getId());
            System.out.println("用户名称"+user.getFirstName());
        }else {
            System.out.println("用户为空！");
        }
        identityServiceImpl.deleteUser("2");*/

        /*UserEntity userEntity2 = new UserEntity("1");
        userEntity2.setFirstName("小红");
        identityServiceImpl.saveUser(userEntity2);*/

        /*GroupEntity groupEntity=new GroupEntity("2");
        groupEntity.setName("测试角色");
        identityServiceImpl.saveGroup(groupEntity);*/
        //identityServiceImpl.deleteGroup("2");

        /*GroupEntity groupEntity2=new GroupEntity("1");
        groupEntity2.setName("测试角色1");*/
        //identityServiceImpl.saveGroup(groupEntity2);
        //identityServiceImpl.deleteGroup("1");

        //identityServiceImpl.deleteUser("2");


        //identityServiceImpl.createMembership("2","1");
        //identityServiceImpl.deleteUser("2");
        System.out.println("成功");
    }


    @Test
    public void dtest() {

        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        System.out.println("成功");
    }


    @Autowired
    WsCityService wsCityServiceImpl;

    //导入城市表
    @Test
    public void dtest1() throws Exception {
        String fileName = "各国家和地区住宿费、伙食费、公杂费开支标准表.xlsx";
        File file = new File("F:\\ChuangZhi\\外事\\过程文件\\因公出国访问材料包（模板）及报销材料清单\\因公出国访问材料包（模板）及报销材料清单-2019年10月14日更新\\因公出国访问材料包（模板）-2019年10月14日更新\\因公出国访问材料包（模板）\\出访管理规定及补贴标准（经费标准在这里查）（2019年3月12日更新）\\" + fileName);
        if (!file.getParentFile().exists()) { //如果文件的目录不存在
            System.out.println("文件不存在。");
            //file.getParentFile().mkdirs(); //创建目录
            return;
        }
        //2: 实例化OutputString 对象
        //Workbook wb=new Workbook(fs);//创建了一个工作簿
        Workbook wb = ExcelUtil.getWorkbook(new FileInputStream(file), fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()));
        //导入数据数
        Sheet sheet = wb.getSheetAt(0); //获取第一个sheet页
        if (sheet == null) {
            System.out.println("sheet为空！");
            return;
        }
        List<List<String>> llist = ExcelUtil.parseExcel(sheet);
        //查询该洲有没有数据
        Map<String, WsCity> cmap = new LinkedHashMap();
        WsCity c1 = null;
        WsCity c2 = null;
        WsCity c3 = null;
        for (int i = 1; i < llist.size(); i++) {
            List<String> list = llist.get(i);
            //处理区域
            mapAddnewWsCity(cmap, list.get(2), "0", null);
            //处理国家
            mapAddnewWsCity(cmap, list.get(1), "1", list.get(2));
            //处理城市
            if (list.get(3) != null && !"".equals(list.get(3))) {
                String[] strs = list.get(3).split("、");
                for (String str : strs) {
                    mapAddnewWsCity(cmap, str, "2", list.get(1));
                }
            }

        }
        //数据库导入
        //获取全部子项目
        //全部execl第一列表头
        List<WsCity> clist = new ArrayList<>();
        for (String item : cmap.keySet()) {
            WsCity c = cmap.get(item);
            if (c.getArea().indexOf("其他城市") > -1) {
                c.setArea("其他城市");
            }
            if (c.getArea().indexOf("墨西哥1") > -1) {
                c.setArea("墨西哥");
            }
            clist.add(c);
            System.out.println(c);
        }
        wsCityServiceImpl.inserts(clist);
    }

    public void mapAddnewWsCity(Map<String, WsCity> map, String area, String level, String parentId) {
        if (!(area == null || "".equals(area)) && map.get(area) != null) {
            return;
        }
        WsCity c1 = new WsCity();
        c1.setArea(area);
        if ("其他城市".equals(area)) { //其他城市特殊处理
            c1.setArea(parentId + area);
        }
        /*if("墨西哥".equals(area)&&!"1".equals(level)){ //其他城市特殊处理
            c1.setArea(parentId+area);
        }*/
        c1.setLevel1(level);
        c1.setId(IdWorker.getIdWorkerNext().toString());
        WsCity c2 = null;
        if (parentId != null && !"".equals(parentId)) {
            c2 = map.get(parentId);
        }
        if (c2 != null) {
            c1.setParentId(c2.getId());
        }
        map.put(c1.getArea(), c1);
    }


    @Autowired
    WsVisitFundInfoService wsVisitFundInfoServiceImpl;

    //导入工杂费表
    @Test
    public void dtest2() throws Exception {
        String fileName = "各国家和地区住宿费、伙食费、公杂费开支标准表.xlsx";
        File file = new File("F:\\ChuangZhi\\外事\\过程文件\\因公出国访问材料包（模板）及报销材料清单\\因公出国访问材料包（模板）及报销材料清单-2019年10月14日更新\\因公出国访问材料包（模板）-2019年10月14日更新\\因公出国访问材料包（模板）\\出访管理规定及补贴标准（经费标准在这里查）（2019年3月12日更新）\\" + fileName);
        if (!file.getParentFile().exists()) { //如果文件的目录不存在
            System.out.println("文件不存在。");
            //file.getParentFile().mkdirs(); //创建目录
            return;
        }
        //2: 实例化OutputString 对象
        //Workbook wb=new Workbook(fs);//创建了一个工作簿
        Workbook wb = ExcelUtil.getWorkbook(new FileInputStream(file), fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()));
        //导入数据数
        Sheet sheet = wb.getSheetAt(0); //获取第一个sheet页
        if (sheet == null) {
            System.out.println("sheet为空！");
            return;
        }
        List<List<String>> llist = ExcelUtil.parseExcel(sheet);
        //查询该洲有没有数据
        Map<String, WsVisitFundInfo> cmap = new LinkedHashMap();
        for (int i = 1; i < llist.size(); i++) {
            List<String> list = llist.get(i);
            WsCity c1 = new WsCity();
            c1.setArea(list.get(2));
            c1 = wsCityServiceImpl.selectOne(c1);
            if (c1 == null) {
                throw new Exception();
            }
            WsCity c2 = new WsCity();
            c2.setArea(list.get(1));
            c2.setLevel1("1");
            c2 = wsCityServiceImpl.selectOne(c2);
            if (c2 == null) {
                throw new Exception();
            }

            if (list.get(3) == null || "".equals(list.get(3))) {
                WsVisitFundInfo wsVisitFundInfo = new WsVisitFundInfo(IdWorker.getIdWorkerNext().toString(), null, null, null, null
                        , list.get(2), list.get(1), null, list.get(4), list.get(5), list.get(6), list.get(7), c1.getId(), c2.getId(), null);
                cmap.put(wsVisitFundInfo.getId(), wsVisitFundInfo);
            } else {
                String[] strs = list.get(3).split("、");
                for (String str : strs) {
                    WsCity c3 = new WsCity();
                    c3.setArea(str);
                    c3.setParentId(c2.getId());
                    c3.setLevel1("2");
                    c3 = wsCityServiceImpl.selectOne(c3);
                    if (c3 == null) {
                        System.out.println(str + "," + c2.getId());
                        throw new Exception();
                    }
                    WsVisitFundInfo wsVisitFundInfo = new WsVisitFundInfo(IdWorker.getIdWorkerNext().toString(), null, null, null, null
                            , list.get(2), list.get(1), str, list.get(4), list.get(5), list.get(6), list.get(7), c1.getId(), c2.getId(), c3.getId());
                    cmap.put(wsVisitFundInfo.getId(), wsVisitFundInfo);
                }
            }
        }
        //数据库导入
        //获取全部子项目
        //全部execl第一列表头
        List<WsVisitFundInfo> clist = new ArrayList<>();
        for (String item : cmap.keySet()) {
            WsVisitFundInfo c = cmap.get(item);
            clist.add(c);
            System.out.println(c);
        }
        wsVisitFundInfoServiceImpl.inserts(clist);
    }


    @Autowired
    SysSerialNumberService sysSerialNumberServiceImpl;

    @Test
    public void getSerialNumber() throws Exception {
        List<String> list = new ArrayList<>();
        list.add(sysSerialNumberServiceImpl.getSerialNumber("1"));
        list.add(sysSerialNumberServiceImpl.getSerialNumber("默认类型"));
        list.add(sysSerialNumberServiceImpl.getSerialNumber("默认类型"));
        list.add(sysSerialNumberServiceImpl.getSerialNumber("默认类型"));
        System.out.println(list);
    }
}
