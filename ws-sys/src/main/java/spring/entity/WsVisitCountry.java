package spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "WS_VISIT_COUNTRY")
public class WsVisitCountry {
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

    @Column(name = "SERIAL_NUMBER")
    @ApiModelProperty(value = "流水号")
    private String serialNumber;

    @Column(name = "URGENCY_DEGREE")
    @ApiModelProperty(value = "紧急程度")
    private String urgencyDegree;

    @Column(name = "USERNAME")
    @ApiModelProperty(value = "姓名")
    private String username;

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
    @ApiModelProperty(value = "健康情况")
    private String fitness;

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
    @ApiModelProperty(value = "结婚状态")
    private String married;

    @Column(name = "MARRIED_DATE")
    @ApiModelProperty(value = "结婚日期")
    private Date marriedDate;

    @Column(name = "HOME_ADDRESS")
    @ApiModelProperty(value = "家庭地址")
    private String homeAddress;

    @Column(name = "PHONE")
    @ApiModelProperty(value = "手机")
    private String phone;

    @Column(name = "办公电话")
    @ApiModelProperty(value = "办公电话")
    private String phoneCall;

    @Column(name = "PHONE_CALL_HOME")
    @ApiModelProperty(value = "家庭电话")
    private String phoneCallHome;

    @Column(name = "EMAIL")
    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @Column(name = "THING")
    @ApiModelProperty(value = "出访事由（50个字）")
    private String thing;

    @Column(name = "FUND")
    @ApiModelProperty(value = "预算")
    private String fund;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "START_DATE")
    @ApiModelProperty(value = "出访日期")
    private Date startDate;

    @Column(name = "START_DAY")
    @ApiModelProperty(value = "在外停留天数")
    private BigDecimal startDay;

    @Column(name = "START_FUND_CONTENT")
    @ApiModelProperty(value = "出访经费来源说明")
    private String startFundContent;

    @Column(name = "GROUP_TITLE")
    @ApiModelProperty(value = "团组名称")
    private String groupTitle;

    @Column(name = "GROUP_TYPE")
    @ApiModelProperty(value = "出访类型")
    private String groupType;

    @Column(name = "GROUP_NATURE")
    @ApiModelProperty(value = "出访性质")
    private String groupNature;

    @Column(name = "GROUP_TASK")
    @ApiModelProperty(value = "任务分类")
    private String groupTask;

    @Column(name = "GROUP_THING")
    @ApiModelProperty(value = "出访事由")
    private String groupThing;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "GROUP_DATE")
    @ApiModelProperty(value = "出访日期")
    private Date groupDate;

    @Column(name = "GROUP_START_DAY")
    @ApiModelProperty(value = "在外停留天数")
    private BigDecimal groupStartDay;

    @Column(name = "GROUP_START_FUND_CONTENT")
    @ApiModelProperty(value = "出访经费来源说明")
    private String groupStartFundContent;

    @Column(name = "GROUP_PRO_FUND")
    @ApiModelProperty(value = "经费项目名称及卡号")
    private String groupProFund;

    @Column(name = "GROUP_INVITE_UNIT")
    @ApiModelProperty(value = "邀请单位")
    private String groupInviteUnit;

    @Column(name = "GROUP_INVITE_NAME")
    @ApiModelProperty(value = "邀请人姓名及职务")
    private String groupInviteName;

    @Column(name = "GROUP_INVITE_PHONE")
    @ApiModelProperty(value = "邀请人联系方式")
    private String groupInvitePhone;

    @Column(name = "AMOUNT")
    @ApiModelProperty(value = "预算合计（人民币）")
    private BigDecimal amount;

    @Column(name = "AMOUNT_USD")
    @ApiModelProperty(value = "预算合计（美元）")
    private BigDecimal amountUsd;


    public WsVisitCountry(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String serialNumber, String urgencyDegree, String username, String sex, Date birthday, String usernameId, String startAddress, String censusAddress, String fitness, String unitName, String politics, String dept, String deptLevel, String married, Date marriedDate, String homeAddress, String phone, String phoneCall, String phoneCallHome, String email, String thing, String fund, Date startDate, BigDecimal startDay, String startFundContent, String groupTitle, String groupType, String groupNature, String groupTask, String groupThing, Date groupDate, BigDecimal groupStartDay, String groupStartFundContent, String groupProFund, String groupInviteUnit, String groupInviteName, String groupInvitePhone, BigDecimal amount, BigDecimal amountUsd) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.serialNumber = serialNumber;
        this.urgencyDegree = urgencyDegree;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.usernameId = usernameId;
        this.startAddress = startAddress;
        this.censusAddress = censusAddress;
        this.fitness = fitness;
        this.unitName = unitName;
        this.politics = politics;
        this.dept = dept;
        this.deptLevel = deptLevel;
        this.married = married;
        this.marriedDate = marriedDate;
        this.homeAddress = homeAddress;
        this.phone = phone;
        this.phoneCall = phoneCall;
        this.phoneCallHome = phoneCallHome;
        this.email = email;
        this.thing = thing;
        this.fund = fund;
        this.startDate = startDate;
        this.startDay = startDay;
        this.startFundContent = startFundContent;
        this.groupTitle = groupTitle;
        this.groupType = groupType;
        this.groupNature = groupNature;
        this.groupTask = groupTask;
        this.groupThing = groupThing;
        this.groupDate = groupDate;
        this.groupStartDay = groupStartDay;
        this.groupStartFundContent = groupStartFundContent;
        this.groupProFund = groupProFund;
        this.groupInviteUnit = groupInviteUnit;
        this.groupInviteName = groupInviteName;
        this.groupInvitePhone = groupInvitePhone;
        this.amount = amount;
        this.amountUsd = amountUsd;
    }

    public WsVisitCountry() {
        super();
    }
}