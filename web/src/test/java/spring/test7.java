package spring;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
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
import org.springframework.web.context.WebApplicationContext;
import spring.entity.*;
import spring.exception.CustomException;
import spring.service.*;
import spring.service.impl.SysActivitiTaskServiceImpl;
import spring.service.impl.WsVisitCountryServiceImpl;
import spring.service.impl.WsVisitCountryUserServiceImpl;
import spring.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class test7 {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    WebApplicationContext webApplicationContext;


    @Autowired
    RuntimeService runtimeServiceImpl;
    @Autowired
    TaskServiceImpl taskServiceImpl;

    @Autowired
    ProcessService processServiceImpl;

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
    public void cese2() throws Exception {

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

            sysActivitiTask.setCreateBy("test2");
            sysActivitiTaskServiceImpl.delete(sysActivitiTask);

            listMassage.add("删除表单内容");
            WsVisitCountryUser wsVisitCountryUser=new WsVisitCountryUser();
            wsVisitCountryUser.setCreateBy("test1");
            wsVisitCountryUserServiceImpl.delete(wsVisitCountryUser);
            WsVisitCountry wsVisitCountry=new WsVisitCountry();
            wsVisitCountry.setCreateBy("test1");
            wsVisitCountryServiceImpl.delete(wsVisitCountry);

            List<SysActivitiTask> taskList=null;
            List<Map<String, Object>> nextNodeList=null;
            Random random = new Random();//随机数

            //申请人/代填人填写团组信息
            Map<String,Object> cmap=new HashMap<>();
            cmap.put("taskId","");//任务ID
            cmap.put("Assignee","test1");//下一节点处理人
            cmap.put("nextNode","申请人/代填人填写团组信息");//下一节点
            cmap.put("comformInfo","{\"formId\":\"1318993721143001088\",\"date\":{\"createBy\":\"1\"},\"mainformdates\":[{\"formId\":\"1318993825551536133\",\"dates\":[{\"date\":{\"createBy\":\"1\",\"userId\":\"01\"}},{\"date\":{\"createBy\":\"1\",\"userId\":\"02\"}}]}]}");//资料
            cmap.put("comformId","1318993721143001088");//资料formid
            cmap.put("businessKey","");//业务ID(表单ID)
            cmap.put("modelId","360001");//模型ID
            cmap.put("opinion","测试修改意见");//意见
            cmap.put("serialNumber",IdWorker.getIdWorkerNext().toString());//流水号
            cmap.put("urgencyDegree","1");//紧急程度
            cmap=processServiceImpl.completeTask((String)cmap.get("taskId"), (String)cmap.get("modelId"), (String)cmap.get("Assignee"), (String)cmap.get("nextNode")
                    , (String)cmap.get("comformInfo"),(String)cmap.get("comformId"), (String)cmap.get("businessKey"), (String)cmap.get("opinion"), (String)cmap.get("serialNumber"), (String)cmap.get("urgencyDegree"));
            cmap.put("comformInfo", "{\"formId\":\"1318993721143001088\",\"date\":{\"id\":\""+cmap.get("businessKey")+"\",\"createBy\":\"1\"},\"mainformdates\":[{\"formId\":\"1318993825551536133\",\"dates\":[{\"date\":{\"createBy\":\"1\",\"userId\":\"01\"}},{\"date\":{\"createBy\":\"1\",\"userId\":\"02\"}}]}]}");
            listMassage.add("申请人/代填人填写团组信息："+JSON.toJSONString(cmap,SerializerFeature.WriteMapNullValue));
            listMassage.add("#######################################################################################");
            listMassage.add("查询下一节点信息");

            sysActivitiTask=new SysActivitiTask();
            sysActivitiTask.setBusinessKey((String) cmap.get("businessKey"));
            sysActivitiTask.setActivitiStatus("003");
            taskList=sysActivitiTaskServiceImpl.selectListByPage(sysActivitiTask);
            for (SysActivitiTask item :taskList) {
                if("test1".equals(item.getDisposeUserId())){
                    login(  "admin", Md5Util.EncoderByMd5("1"));
                }else if("test2".equals(item.getDisposeUserId())){
                    login(  "student", Md5Util.EncoderByMd5("1"));
                }else{
                    throw new CustomException("没有找到该用户。");
                }

                listMassage.add(item.toString());
                nextNodeList=  processServiceImpl.getNextNode(item.getTaskId(),(String)cmap.get("modelId"),(String)cmap.get("businessKey"));
                //随机节点
                if(nextNodeList==null||nextNodeList.size()<=0){
                    throw new CustomException(item.getActivitiName()+"信息 nextNode错误");
                }
                int nextNodeIndex1=nextNodeList.size()<=1?0:Integer.valueOf(random.nextInt(nextNodeList.size()-1));
                String nextNodeValue1= (String) nextNodeList.get(nextNodeIndex1).get("nextNodeValue");
                List<Map<String, Object>> assignees = (List<Map<String, Object>>)nextNodeList.get(nextNodeIndex1).get("assignees");
                String nextNodeAssignee1=(String)assignees.get(assignees.size()<=1?0:Integer.valueOf(random.nextInt(assignees.size()-1))).get("assigneeValue");
                cmap.put("nextNode",nextNodeValue1);//随机节点
                cmap.put("Assignee",nextNodeAssignee1);//随机处理人
                cmap.put("taskId",item.getTaskId());
                cmap=processServiceImpl.completeTask((String)cmap.get("taskId"), (String)cmap.get("modelId"), (String)cmap.get("Assignee"), (String)cmap.get("nextNode")
                        , (String)cmap.get("comformInfo"),(String)cmap.get("comformId"), (String)cmap.get("businessKey"), (String)cmap.get("opinion"), (String)cmap.get("serialNumber"), (String)cmap.get("urgencyDegree"));
                listMassage.add(item.getActivitiProcess()+"信息："+JSON.toJSONString(cmap,SerializerFeature.WriteMapNullValue));
                listMassage.add("#######################################################################################");
                listMassage.add("查询下一节点信息");
            }

            for (int i = 0; i <13 ; i++) {
                if(i==3){
                    System.out.println(i+"循环节点断点");
                }
                taskList = sysActivitiTaskServiceImpl.selectListByPage(sysActivitiTask);
                for (SysActivitiTask item : taskList) {
                    if("test1".equals(item.getDisposeUserId())){
                        login(  "admin", Md5Util.EncoderByMd5("1"));
                    }else if("test2".equals(item.getDisposeUserId())){
                        login(  "student", Md5Util.EncoderByMd5("1"));
                    }else{
                        throw new CustomException("没有找到该用户。");
                    }
                    listMassage.add(item.toString());
                    if("申请人/代填人提交材料".equals(item.getActivitiProcess())){
                        System.out.println("测试拦截点");
                    }
                    nextNodeList = processServiceImpl.getNextNode(item.getTaskId(), (String) cmap.get("modelId"), (String) cmap.get("businessKey"));
                    //随机节点
                    if (nextNodeList == null || nextNodeList.size() <= 0) {
                        throw new CustomException(item.getActivitiName() + "信息 nextNode错误");
                    }
                    /*for (Map<String, Object> item2: nextNodeList) {
                        if("出访科科长分配经办人".equals(delegateTask.getName())){
                            System.out.println("测试拦截点");
                        }
                    }*/

                    int nextNodeIndex1 = nextNodeList.size() <= 1 ? 0 : Integer.valueOf(random.nextInt(nextNodeList.size() - 1));
                    String nextNodeValue1 = (String) nextNodeList.get(nextNodeIndex1).get("nextNodeValue");
                    if("校长/书记审批".equals(nextNodeValue1)){
                        System.out.println("测试拦截点");
                    }
                    if("经营负责人审批".equals(nextNodeValue1)){
                        System.out.println("测试拦截点");
                    }
                    String nextNodeAssignee1 =null;
                    if(!"Event".equals(nextNodeList.get(nextNodeIndex1).get("flowElementType"))){
                        List<Map<String, Object>> assignees =(List<Map<String, Object>>) nextNodeList.get(nextNodeIndex1).get("assignees");
                        nextNodeAssignee1 = (String) assignees.get(assignees.size() <= 1 ? 0 : Integer.valueOf(random.nextInt(assignees.size() - 1))).get("assigneeValue");
                    }
                    cmap.put("nextNode", nextNodeValue1);//随机节点
                    cmap.put("Assignee", nextNodeAssignee1);//随机处理人
                    cmap.put("taskId", item.getTaskId());
                    cmap = processServiceImpl.completeTask((String) cmap.get("taskId"), (String) cmap.get("modelId"), (String) cmap.get("Assignee"), (String) cmap.get("nextNode")
                            , (String) cmap.get("comformInfo"), (String) cmap.get("comformId"), (String) cmap.get("businessKey"), (String) cmap.get("opinion"), (String) cmap.get("serialNumber"), (String) cmap.get("urgencyDegree"));
                    listMassage.add(item.getActivitiProcess() + "信息：" + JSON.toJSONString(cmap, SerializerFeature.WriteMapNullValue));
                    listMassage.add("#######################################################################################");
                    listMassage.add("查询下一节点信息");
                }
            }

            /*List<Task> list = taskServiceImpl.createTaskQuery().processInstanceBusinessKey((String)cmap.get("businessKey")).orderByTaskCreateTime().desc().list();
            if (null != list && list.size() > 0) {
                for (Task task : list) {
                    listMassage.add("任务ID:" + task.getId()+",任务办理人:" + task.getAssignee()+",执行实例ID:" + task.getExecutionId()+",任务名称:"
                            + task.getName()+",formKey:" + task.getFormKey()+",流程定义ID:" + task.getProcessDefinitionId()
                            +",流程实例ID:" + task.getProcessInstanceId()+",任务创建时间:" + task.getCreateTime());
                }
            } else {
                throw new CustomException("任务列表为空！");
            }*/
        } catch (Exception e) {
            throw e;
        }finally {
            for (String str :listMassage) {
                System.out.println(str);
            }
        }
    }





    @Test
    public void nihao(){
        ProcessEngine processEngine = ApplicationContextUtil.getInstance().getApplicationContext().getBean(ProcessEngine.class);
        RepositoryService repositoryServiceImpl = processEngine.getRepositoryService();
        TaskService taskServiceImpl = processEngine.getTaskService();
        RuntimeService runtimeServiceImpl = processEngine.getRuntimeService();
        List<Map<String, Object>> list = new ArrayList<>();
        //1.首先拿到配置文件：
        Model modelData = repositoryServiceImpl.getModel("360001");
        ProcessDefinition pd = repositoryServiceImpl.createProcessDefinitionQuery().deploymentId(modelData.getDeploymentId()).singleResult();
        //System.out.println("===模板相应版本ID：===" + pd.getDeploymentId() + ",  " + pd.getId() + "," + pd.getVersion());
        BpmnModel model = repositoryServiceImpl.getBpmnModel(pd.getId());
        //获取所有节点

        Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
        for (FlowElement e:  flowElements) {
            System.out.println(e.getId());
            /*if(e instanceof org.activiti.bpmn.model.SubProcess){
                Collection<FlowElement> subFlowElements = model.getMainProcess().getFlowElements();
                for (FlowElement e2 : subFlowElements) {

                    //System.out.println("循环节点key：" + forEachId);
                    if (e2.getId().equals(activitiId)) {//当前节点==循环到的节点
                        if (e instanceof org.activiti.bpmn.model.UserTask) {//节点
                            //如果formKey中有class要做镜像处理 //直接返回镜像信息
                            sequenceFlowList = ((org.activiti.bpmn.model.UserTask) e).getOutgoingFlows();//流出信息
                            nowNode = e.getId();
                            break;
                        }
                    }
                }
            }

             else if (forEachId.equals(activitiId)) {//当前节点==循环到的节点
                if (e instanceof org.activiti.bpmn.model.UserTask) {//节点
                    //如果formKey中有class要做镜像处理 //直接返回镜像信息
                    sequenceFlowList = ((org.activiti.bpmn.model.UserTask) e).getOutgoingFlows();//流出信息
                    nowNode = e.getId();
                    break;
                }
            }
            if*/
        }
    }








}
