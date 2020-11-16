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
@Table(name="WS_PRO_MILLION_PLAN")
public class WsProMillionPlan {

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

    @Column(name = "USERNAME")
    @ApiModelProperty(value="姓名")
    private String username;

    @Column(name = "SEX")
    @ApiModelProperty(value="性别")
    private String sex;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "BIRTHDAY")
    @ApiModelProperty(value="出生日期")
    private Date birthday;

    @Column(name = "BIRTHPLACE_NATION")
    @ApiModelProperty(value="出生地-国")
    private String birthplaceNation;

    @Column(name = "BIRTHPLACE_PROVINCE")
    @ApiModelProperty(value="出生地-省")
    private String birthplaceProvince;

    @Column(name = "BIRTHPLACE")
    @ApiModelProperty(value="出生地-市")
    private String birthplace;

    @Column(name = "CADRE")
    @ApiModelProperty(value="宿舍地址")
    private String cadre;

    @Column(name = "POLITICS")
    @ApiModelProperty(value="身份证号")
    private String politics;

    @Column(name = "PHONE")
    @ApiModelProperty(value="电话/手机")
    private String phone;

    @Column(name = "EMAIL")
    @ApiModelProperty(value="电子邮箱")
    private String email;

    @Column(name = "COLLEGE")
    @ApiModelProperty(value="所属学院")
    private String college;

    @Column(name = "GRADE")
    @ApiModelProperty(value="年级")
    private String grade;

    @Column(name = "CLASS1")
    @ApiModelProperty(value="班级")
    private String class1;

    @Column(name = "PROFESSION")
    @ApiModelProperty(value="专业")
    private String profession;

    @Column(name = "PRO")
    @ApiModelProperty(value="申请项目")
    private String pro;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "PRO_DATE1")
    @ApiModelProperty(value="项目时间：1")
    private Date proDate1;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "PRO_DATE2")
    @ApiModelProperty(value="项目时间：2")
    private Date proDate2;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "PRO_DATE3")
    @ApiModelProperty(value="项目时间：3")
    private Date proDate3;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "PRO_DATE4")
    @ApiModelProperty(value="项目时间：4")
    private Date proDate4;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "PRO_DATE5")
    @ApiModelProperty(value="项目时间：5")
    private Date proDate5;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "PRO_DATE6")
    @ApiModelProperty(value="项目时间：6")
    private Date proDate6;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "PRO_DATE7")
    @ApiModelProperty(value="项目时间：7")
    private Date proDate7;

    @Column(name = "APPLY_OPINION")
    @ApiModelProperty(value="申请人描述")
    private String applyOpinion;

    @Column(name = "APPLY_USER")
    @ApiModelProperty(value="申请人签名：")
    private String applyUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "APPLY_USER_DATE")
    @ApiModelProperty(value="申请人签名：日期")
    private Date applyUserDate;

    @Column(name = "DEAN_OPINION")
    @ApiModelProperty(value="学校审核意见")
    private String deanOpinion;

    @Column(name = "DEAN")
    @ApiModelProperty(value="学院领导签字：")
    private String dean;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "DEAN_DATE")
    @ApiModelProperty(value="学院领导签字：：日期")
    private Date deanDate;

    @Column(name = "SUM_SCHOOL_OPINION")
    @ApiModelProperty(value="国际处（港澳台）意见：")
    private String sumSchoolOpinion;

    @Column(name = "SUM_SCHOOL")
    @ApiModelProperty(value="国际处（港澳台）领导签字：")
    private String sumSchool;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "SUM_SCHOOL_DATE")
    @ApiModelProperty(value="国际处（港澳台）领导签字：日期")
    private Date sumSchoolDate;


    @Column(name = "DAY_NUM")
    @ApiModelProperty(value="天数")
    private Integer dayNum;

    public WsProMillionPlan(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String username, String sex, Date birthday, String birthplaceNation, String birthplaceProvince, String birthplace, String cadre, String politics, String phone, String email, String college, String grade, String class1, String profession, String pro, Date proDate1, Date proDate2, Date proDate3, Date proDate4, Date proDate5, Date proDate6, Date proDate7, String applyOpinion, String applyUser, Date applyUserDate, String deanOpinion, String dean, Date deanDate, String sumSchoolOpinion, String sumSchool, Date sumSchoolDate,Integer dayNum) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.birthplaceNation = birthplaceNation;
        this.birthplaceProvince = birthplaceProvince;
        this.birthplace = birthplace;
        this.cadre = cadre;
        this.politics = politics;
        this.phone = phone;
        this.email = email;
        this.college = college;
        this.grade = grade;
        this.class1 = class1;
        this.profession = profession;
        this.pro = pro;
        this.proDate1 = proDate1;
        this.proDate2 = proDate2;
        this.proDate3 = proDate3;
        this.proDate4 = proDate4;
        this.proDate5 = proDate5;
        this.proDate6 = proDate6;
        this.proDate7 = proDate7;
        this.applyOpinion = applyOpinion;
        this.applyUser = applyUser;
        this.applyUserDate = applyUserDate;
        this.deanOpinion = deanOpinion;
        this.dean = dean;
        this.deanDate = deanDate;
        this.sumSchoolOpinion = sumSchoolOpinion;
        this.sumSchool = sumSchool;
        this.sumSchoolDate = sumSchoolDate;
        this.dayNum=dayNum;
    }

    public WsProMillionPlan() {
        super();
    }


}