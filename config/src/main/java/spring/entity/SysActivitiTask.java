package spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@Table(name="SYS_ACTIVITI_TASK")
public class SysActivitiTask {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CREATE_BY")
    @ApiModelProperty(value="创建人")
    private String createBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "CREATE_DATE")
    @ApiModelProperty(value="创建时间")
    private Date createDate;

    @Column(name = "UPDATE_BY")
    @ApiModelProperty(value="更新人")
    private String updateBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "UPDATE_DATE")
    @ApiModelProperty(value="更新时间")
    private Date updateDate;

    @Column(name = "ORDER_ID")
    @ApiModelProperty(value="排序ID")
    private String orderId;

    @Column(name = "SERIAL_NUMBER")
    @ApiModelProperty(value="流水号")
    private String serialNumber;

    @Column(name = "URGENCY_DEGREE")
    @ApiModelProperty(value="紧急程度")
    private String urgencyDegree;

    @Column(name = "TASK_ID")
    @ApiModelProperty(value="任务id")
    private String taskId;

    @Column(name = "BUSINESS_KEY")
    @ApiModelProperty(value="表单id")
    private String businessKey;

    @Column(name = "DISPOSE_COLLEGE")
    @ApiModelProperty(value="当前处理人学院")
    private String disposeCollege;

    @Column(name = "DISPOSE_DEPT")
    @ApiModelProperty(value="当前处理人部门")
    private String disposeDept;

    @Column(name = "DISPOSE_ROLE")
    @ApiModelProperty(value="当前处理人角色")
    private String disposeRole;

    @Column(name = "DISPOSE_UNIT")
    @ApiModelProperty(value="当前处理人单位")
    private String disposeUnit;

    @Column(name = "DISPOSE_BY1")
    @ApiModelProperty(value="当前处理人查询备用字段1")
    private String disposeBy1;

    @Column(name = "DISPOSE_USER_ID")
    @ApiModelProperty(value="当前处理人用户名ID")
    private String disposeUserId;

    @Column(name = "DISPOSE_USER_NAME")
    @ApiModelProperty(value="当前处理人用户名")
    private String disposeUserName;

    @Column(name = "AGENCY_DISPOSE_USER_ID")
    @ApiModelProperty(value="代办用户id")
    private String agencyDisposeUserId;

    @Column(name = "AGENCY_DISPOSE_USER_NAME")
    @ApiModelProperty(value="代办用户名")
    private String agencyDisposeUserName;

    @Column(name = "ACTIVITI_NAME")
    @ApiModelProperty(value="流程名称")
    private String activitiName;

    @Column(name = "ACTIVITI_ID")
    @ApiModelProperty(value="流程名称ID")
    private String activitiId;

    @Column(name = "ACTIVITI_PROCESS")
    @ApiModelProperty(value="当前步骤")
    private String activitiProcess;

    @Column(name = "ACTIVITI_TYPE")
    @ApiModelProperty(value="流程类型")
    private String activitiType;

    @Column(name = "ACTIVITI_STATUS")
    @ApiModelProperty(value="流程状态")
    private String activitiStatus;

    @Column(name = "INITIATE_USER_ID")
    @ApiModelProperty(value="发起人")
    private String initiateUserId;

    @Column(name = "INITIATE_USER_NAME")
    @ApiModelProperty(value="发起人用户名")
    private String initiateUserName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "INITIATE_DATE")
    @ApiModelProperty(value="发起时间")
    private Date initiateDate;

    @Column(name = "LAST_DISPOSE_USER_ID")
    @ApiModelProperty(value="上一步处理人")
    private String lastDisposeUserId;

    @Column(name = "LAST_DISPOSE_USER_NAME")
    @ApiModelProperty(value="上一步处理人用户名")
    private String lastDisposeUserName;

    @Column(name = "CC_COLLEGE")
    @ApiModelProperty(value="抄送人学院")
    private String ccCollege;

    @Column(name = "CC_DEPT")
    @ApiModelProperty(value="抄送人部门")
    private String ccDept;

    @Column(name = "CC_ROLE")
    @ApiModelProperty(value="抄送人角色")
    private String ccRole;

    @Column(name = "CC_UNIT")
    @ApiModelProperty(value="抄送人单位")
    private String ccUnit;

    @Column(name = "CC_BY1")
    @ApiModelProperty(value="抄送人查询备用字段2")
    private String ccBy1;

    @Column(name = "CC_USER_ID")
    @ApiModelProperty(value="抄送人用户名ID")
    private String ccUserId;

    @Column(name = "CC_USER_NAME")
    @ApiModelProperty(value="抄送人用户名")
    private String ccUserName;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="意见")
    private String content;

    /**
     *
     * @param id
     * @param createBy 创建人
     * @param createDate 创建时间
     * @param updateBy  更新人
     * @param updateDate 更新时间
     * @param orderId 排序id
     * @param serialNumber 流水号
     * @param urgencyDegree 紧急程度
     * @param taskId 任务id
     * @param businessKey
     * @param disposeCollege 办理人学院
     * @param disposeDept 办理人部门
     * @param disposeRole 办理人角色
     * @param disposeUnit 办理人单位
     * @param disposeBy1 办理人备用字段
     * @param disposeUserId 办理人id
     * @param disposeUserName 办理人name
     * @param agencyDisposeUserId 代办人id
     * @param agencyDisposeUserName 代办人name
     * @param activitiName 流程名称
     * @param activitiId 流程名称id
     * @param activitiProcess 当前步骤
     * @param activitiType 流程类型
     * @param activitiStatus 流程状态
     * @param initiateUserId  发起人id
     * @param initiateUserName 发起人name
     * @param initiateDate 发起人时间
     * @param lastDisposeUserId 上一步处理人id
     * @param lastDisposeUserName 上一步处理人
     * @param ccCollege 抄送人学院
     * @param ccDept 抄送人部门
     * @param ccRole 抄送人角色
     * @param ccUnit 抄送人单位
     * @param ccBy1 抄送人备用字段
     * @param ccUserId 抄送人id
     * @param ccUserName 抄送人name
     * @param content 意见
     */
    public SysActivitiTask(String id, String createBy, Date createDate, String updateBy, Date updateDate, String orderId, String serialNumber, String urgencyDegree, String taskId, String businessKey, String disposeCollege, String disposeDept, String disposeRole, String disposeUnit, String disposeBy1, String disposeUserId, String disposeUserName, String agencyDisposeUserId, String agencyDisposeUserName, String activitiName, String activitiId, String activitiProcess, String activitiType, String activitiStatus, String initiateUserId, String initiateUserName, Date initiateDate, String lastDisposeUserId, String lastDisposeUserName, String ccCollege, String ccDept, String ccRole, String ccUnit, String ccBy1, String ccUserId, String ccUserName, String content) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.orderId = orderId;
        this.serialNumber = serialNumber;
        this.urgencyDegree = urgencyDegree;
        this.taskId = taskId;
        this.businessKey = businessKey;
        this.disposeCollege = disposeCollege;
        this.disposeDept = disposeDept;
        this.disposeRole = disposeRole;
        this.disposeUnit = disposeUnit;
        this.disposeBy1 = disposeBy1;
        this.disposeUserId = disposeUserId;
        this.disposeUserName = disposeUserName;
        this.agencyDisposeUserId = agencyDisposeUserId;
        this.agencyDisposeUserName = agencyDisposeUserName;
        this.activitiName = activitiName;
        this.activitiId = activitiId;
        this.activitiProcess = activitiProcess;
        this.activitiType = activitiType;
        this.activitiStatus = activitiStatus;
        this.initiateUserId = initiateUserId;
        this.initiateUserName = initiateUserName;
        this.initiateDate = initiateDate;
        this.lastDisposeUserId = lastDisposeUserId;
        this.lastDisposeUserName = lastDisposeUserName;
        this.ccCollege = ccCollege;
        this.ccDept = ccDept;
        this.ccRole = ccRole;
        this.ccUnit = ccUnit;
        this.ccBy1 = ccBy1;
        this.ccUserId = ccUserId;
        this.ccUserName = ccUserName;
        this.content = content;
    }

    public SysActivitiTask(){
    }
}