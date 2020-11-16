package spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "WS_VISIT_COUNTRY_USER")
public class WsVisitCountryUser {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CREATE_BY")
    @ApiModelProperty(value = "创建人")
    private String createBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "CREATE_DATE")
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @Column(name = "UPDATE_BY")
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "UPDATE_DATE")
    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @Column(name = "STATUS")
    @ApiModelProperty(value = "状态")
    private String status;

    @Column(name = "MAINFORMID")
    @ApiModelProperty(value = "父id")
    private String mainformid;

    @Column(name = "JOB_CODE")
    @ApiModelProperty(value = "工号")
    private String jobCode;

    @Column(name = "USER_ID")
    @ApiModelProperty(value = "用户id")
    private String userId;

    @Column(name = "USER_NAME")
    @ApiModelProperty(value = "姓名")
    private String userName;

    @Column(name = "SEX")
    @ApiModelProperty(value = "性别")
    private String sex;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "BIRTHDAY")
    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @Column(name = "USERNAME_ID")
    @ApiModelProperty(value = "身份证")
    private String usernameId;

    @Column(name = "START_ADDRESS")
    @ApiModelProperty(value = "出生地")
    private String startAddress;

    @Column(name = "CENSUS_ADDRESS")
    @ApiModelProperty(value = "户籍地")
    private String censusAddress;

    @Column(name = "FITNESS")
    @ApiModelProperty(value = "健康状况")
    private String fitness;

    @Column(name = "PHONE")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @Column(name = "PHONE_CALL")
    @ApiModelProperty(value = "办公电话")
    private String phoneCall;

    @Column(name = "EMAIL")
    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @Column(name = "UNIT_NAME")
    @ApiModelProperty(value = "所在单位")
    private String unitName;

    @Column(name = "POLITICS")
    @ApiModelProperty(value = "政治面貌")
    private String politics;

    @Column(name = "DEPT")
    @ApiModelProperty(value = "职务职称")
    private String dept;

    @Column(name = "DEPT_LEVEL")
    @ApiModelProperty(value = "职级")
    private String deptLevel;

    @Column(name = "MARRIED")
    @ApiModelProperty(value = "婚姻状况")
    private String married;

    @Column(name = "IS_SECRET")
    @ApiModelProperty(value = "是否为涉密人员及涉密等级")
    private String isSecret;

    @Column(name = "GROUP_DEPT")
    @ApiModelProperty(value = "在团组中拟任职务")
    private String groupDept;

    @Column(name = "START_ADDRESS_DATE")
    @ApiModelProperty(value = "最近一次因公出访时间、所赴国家/地区")
    private String startAddressDate;

    public WsVisitCountryUser(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String mainformid, String jobCode, String userName, String sex, Date birthday, String usernameId, String startAddress, String censusAddress, String fitness, String phone, String phoneCall, String email, String unitName, String politics, String dept, String deptLevel, String married, String isSecret, String groupDept, String startAddressDate,String userId) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.mainformid = mainformid;
        this.jobCode = jobCode;
        this.userName = userName;
        this.sex = sex;
        this.birthday = birthday;
        this.usernameId = usernameId;
        this.startAddress = startAddress;
        this.censusAddress = censusAddress;
        this.fitness = fitness;
        this.phone = phone;
        this.phoneCall = phoneCall;
        this.email = email;
        this.unitName = unitName;
        this.politics = politics;
        this.dept = dept;
        this.deptLevel = deptLevel;
        this.married = married;
        this.isSecret = isSecret;
        this.groupDept = groupDept;
        this.startAddressDate = startAddressDate;
        this.userId=userId;
    }

    public WsVisitCountryUser() {
        super();
    }
}