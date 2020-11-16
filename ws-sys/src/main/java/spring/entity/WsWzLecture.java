package spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name="WS_WZ_LECTURE")
public class WsWzLecture {
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

    @Column(name = "STATUS")
    @ApiModelProperty(value="状态")
    private String status;

    @Column(name = "PRO_NAME")
    @ApiModelProperty(value="项目名称")
    private String proName;

    @Column(name = "PRO_DEPT")
    @ApiModelProperty(value="项目申报部门")
    private String proDept;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "PRO_DATE")
    @ApiModelProperty(value="项目申报日期")
    private Date proDate;

    @Column(name = "PRO_DEAN")
    @ApiModelProperty(value="项目执行院（系）")
    private String proDean;

    @Column(name = "SUBJECT")
    @ApiModelProperty(value="所在学科")
    private String subject;

    @Column(name = "APPLE_USER")
    @ApiModelProperty(value="项目联系人")
    private String appleUser;

    @Column(name = "APPLE_DEPT")
    @ApiModelProperty(value="职务职称")
    private String appleDept;

    @Column(name = "APPLE_PHONE_CALL")
    @ApiModelProperty(value="电话")
    private String applePhoneCall;

    @Column(name = "APPLE_PHONE")
    @ApiModelProperty(value="手机")
    private String applePhone;

    @Column(name = "APPLE_EMAIL")
    @ApiModelProperty(value="电子邮箱")
    private String appleEmail;

    @Column(name = "FAX")
    @ApiModelProperty(value="传真")
    private String fax;

    @Column(name = "PRO_CONTENT")
    @ApiModelProperty(value="项目内容及预期效果")
    private String proContent;

    @Column(name = "PRO_PLAN")
    @ApiModelProperty(value="项目实施计划")
    private String proPlan;

    @Column(name = "UNDERGO_AMOUNT")
    @ApiModelProperty(value="工作及社会实践经历：合计")
    private BigDecimal undergoAmount;

    @Column(name = "FUND_AMOUNT")
    @ApiModelProperty(value="申请资助经费：合计")
    private BigDecimal fundAmount;

    @Column(name = "EXPERT_NAME")
    @ApiModelProperty(value="专家：中文名称")
    private String expertName;

    @Column(name = "EXPERT_NAME_ENGLISH")
    @ApiModelProperty(value="专家：外文名")
    private String expertNameEnglish;

    @Column(name = "EXPERT_SEX")
    @ApiModelProperty(value="专家：性别")
    private String expertSex;

    @Column(name = "EXPERT_DAY")
    @ApiModelProperty(value="专家：出生")
    private String expertDay;

    @Column(name = "EXPERT_COUNTRY")
    @ApiModelProperty(value="专家：国别地区")
    private String expertCountry;

    @Column(name = "EXPERT_DEPT")
    @ApiModelProperty(value="专家：职务职称")
    private String expertDept;

    @Column(name = "EXPERT_SPECIALTY")
    @ApiModelProperty(value="专家：专业")
    private String expertSpecialty;

    @Column(name = "EXPERT_UNIT")
    @ApiModelProperty(value="专家：海外工作单位")
    private String expertUnit;

    @Column(name = "EXPERT_ADDRESS")
    @ApiModelProperty(value="专家：通信地址")
    private String expertAddress;

    @Column(name = "EXPERT_EMAIL")
    @ApiModelProperty(value="专家：电子邮箱")
    private String expertEmail;

    @Column(name = "EXPERT_CALL")
    @ApiModelProperty(value="专家：电话")
    private String expertCall;

    @Column(name = "EXPERT_GAX")
    @ApiModelProperty(value="专家：传真")
    private String expertGax;

    @Column(name = "EXPERT_NUM_DAY")
    @ApiModelProperty(value="专家：来粤总天数")
    private BigDecimal expertNumDay;

    @Column(name = "EXPERT_CONTENT")
    @ApiModelProperty(value="专家：简述")
    private String expertContent;

    @Column(name = "EXPERT_ICON")
    @ApiModelProperty(value="专家：简述")
    private String expertIcon;

    @Transient
    @ApiModelProperty(value="搜索用：项目申报时间段起")
    private String applyDateStart;

    @Transient
    @ApiModelProperty(value="搜索用：项目申报时间段止")
    private String applyDateEnd;

    public WsWzLecture(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String proName, String proDept, Date proDate
            , String proDean, String subject, String appleUser, String appleDept, String applePhoneCall, String applePhone, String appleEmail, String fax
            , String proContent, String proPlan,BigDecimal undergoAmount,  BigDecimal fundAmount, String expertName, String expertNameEnglish, String expertSex, String expertDay
            , String expertCountry, String expertDept, String expertSpecialty, String expertUnit, String expertAddress, String expertEmail, String expertCall
            , String expertGax, BigDecimal expertNumDay, String expertContent,String expertIcon) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.proName = proName;
        this.proDept = proDept;
        this.proDate = proDate;
        this.proDean = proDean;
        this.subject = subject;
        this.appleUser = appleUser;
        this.appleDept = appleDept;
        this.applePhoneCall = applePhoneCall;
        this.applePhone = applePhone;
        this.appleEmail = appleEmail;
        this.fax = fax;
        this.undergoAmount = undergoAmount;
        this.fundAmount = fundAmount;
        this.expertName = expertName;
        this.expertNameEnglish = expertNameEnglish;
        this.expertSex = expertSex;
        this.expertDay = expertDay;
        this.expertCountry = expertCountry;
        this.expertDept = expertDept;
        this.expertSpecialty = expertSpecialty;
        this.expertUnit = expertUnit;
        this.expertAddress = expertAddress;
        this.expertEmail = expertEmail;
        this.expertCall = expertCall;
        this.expertGax = expertGax;
        this.expertNumDay = expertNumDay;
        this.proContent = proContent;
        this.proPlan = proPlan;
        this.expertContent = expertContent;
        this.expertIcon=expertIcon;
    }

    public WsWzLecture() {
        super();
    }
}