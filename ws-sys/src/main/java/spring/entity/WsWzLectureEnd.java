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
@Table(name="WS_WZ_LECTURE_END")
public class WsWzLectureEnd {
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

    @Column(name = "MAINFORMID")
    @ApiModelProperty(value="父id")
    private String mainformid;

    @Column(name = "DEAN_NAME")
    @ApiModelProperty(value="院校名称")
    private String deanName;

    @Column(name = "PRO_DEAN")
    @ApiModelProperty(value="项目实习院系")
    private String proDean;


    @Column(name = "START_DATE")
    @ApiModelProperty(value="填表日期")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDate;

    @Column(name = "PRO_USER")
    @ApiModelProperty(value="项目负责人姓名")
    private String proUser;

    @Column(name = "DEPT")
    @ApiModelProperty(value="职务职称")
    private String dept;

    @Column(name = "PHONE")
    @ApiModelProperty(value="手机号码")
    private String phone;

    @Column(name = "PHONE_CALL")
    @ApiModelProperty(value="办公电话")
    private String phoneCall;

    @Column(name = "EMAIL")
    @ApiModelProperty(value="电子邮箱")
    private String email;

    @Column(name = "FAX")
    @ApiModelProperty(value="传真号码")
    private String fax;

    @Column(name = "AMOUNT_FUND")
    @ApiModelProperty(value="申请报销资助经费：合计")
    private BigDecimal amountFund;

    @Column(name = "PRO_NAME")
    @ApiModelProperty(value="项目名称")
    private String proName;

    @Column(name = "PRO_DEPT")
    @ApiModelProperty(value="项目所在院系")
    private String proDept;

    @Column(name = "SUBJECT")
    @ApiModelProperty(value="所属学科")
    private String subject;

    @Column(name = "OPINION")
    @ApiModelProperty(value="意见")
    private String opinion;

    @Column(name = "EXPERT_NAME")
    @ApiModelProperty(value="专家：中文名称")
    private String expertName;

    @Column(name = "EXPERT_NAME_ENGLISH")
    @ApiModelProperty(value="专家：外文名称")
    private String expertNameEnglish;

    @Column(name = "EXPERT_SEX")
    @ApiModelProperty(value="专家：性别")
    private String expertSex;

    @Column(name = "EXPERT_DAY")
    @ApiModelProperty(value="专家：出生")
    private String expertDay;

    @Column(name = "EXPERT_COUNTRY")
    @ApiModelProperty(value="专家：国籍")
    private String expertCountry;

    @Column(name = "EXPERT_DEPT")
    @ApiModelProperty(value="专家：职务职称")
    private String expertDept;

    @Column(name = "EXPERT_SUBJECT")
    @ApiModelProperty(value="专家：专业")
    private String expertSubject;

    @Column(name = "EXPERT_UNIT")
    @ApiModelProperty(value="专家：海外工作单位")
    private String expertUnit;

    @Column(name = "EXPERT_SUM_DAY")
    @ApiModelProperty(value="专家：来粤工作天数")
    private BigDecimal expertSumDay;

    @Column(name = "EXPERT_PHONE")
    @ApiModelProperty(value="专家：联系方式")
    private String expertPhone;

    @Column(name = "EXPERT_CODE")
    @ApiModelProperty(value="专家：护照号码")
    private String expertCode;

    @Column(name = "EXPERT_CONTENT")
    @ApiModelProperty(value="专家：简述")
    private String expertContent;

    @Column(name = "ICON")
    @ApiModelProperty(value="头像")
    private String icon;

    public WsWzLectureEnd(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String mainformid, String deanName, String proDean, Date startDate, String proUser, String dept, String phone, String phoneCall, String email, String fax, BigDecimal amountFund, String proName, String proDept, String subject, String opinion, String expertName, String expertNameEnglish, String expertSex, String expertDay, String expertCountry, String expertDept, String expertSubject, String expertUnit, BigDecimal expertSumDay, String expertPhone, String expertCode, String expertConent, String icon) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.mainformid = mainformid;
        this.deanName = deanName;
        this.proDean = proDean;
        this.startDate = startDate;
        this.proUser = proUser;
        this.dept = dept;
        this.phone = phone;
        this.phoneCall = phoneCall;
        this.email = email;
        this.fax = fax;
        this.amountFund = amountFund;
        this.proName = proName;
        this.proDept = proDept;
        this.subject = subject;
        this.expertName = expertName;
        this.expertNameEnglish = expertNameEnglish;
        this.expertSex = expertSex;
        this.expertDay = expertDay;
        this.expertCountry = expertCountry;
        this.expertDept = expertDept;
        this.expertSubject = expertSubject;
        this.expertUnit = expertUnit;
        this.expertSumDay = expertSumDay;
        this.expertPhone = expertPhone;
        this.expertCode = expertCode;
        this.icon = icon;
        this.opinion = opinion;
        this.expertContent = expertConent;
    }

    public WsWzLectureEnd() {
        super();
    }
}