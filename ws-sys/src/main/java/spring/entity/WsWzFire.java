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
@Table(name="WS_WZ_FIRE")
public class WsWzFire {
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

    @Column(name = "APPLY_UNIT")
    @ApiModelProperty(value="申报单位")
    private String applyUnit;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "APPLY_DATE")
    @ApiModelProperty(value="申报时间")
    private Date applyDate;

    @Column(name = "NAME")
    @ApiModelProperty(value="姓名")
    private String name;

    @Column(name = "COUNTRY")
    @ApiModelProperty(value="国籍地区")
    private String country;

    @Column(name = "TEACHER_CODE")
    @ApiModelProperty(value="护照号")
    private String teacherCode;

    @Column(name = "START_END_DATE")
    @ApiModelProperty(value="拟任职位及计划聘期")
    private String startEndDate;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="简历")
    private String content;

    @Column(name = "SCHOOL_AMOUNT")
    @ApiModelProperty(value="国际处：月薪")
    private BigDecimal schoolAmount;

    @Column(name = "AGE")
    @ApiModelProperty(value="年龄")
    private String age;

    @Column(name = "UNIT_LEVEL")
    @ApiModelProperty(value="二级单位：教学与学术水平")
    private String unitLevel;

    @Column(name = "UNIT_CHARACTER")
    @ApiModelProperty(value="二级单位：品德与性格")
    private String unitCharacter;

    @Column(name = "UNIT_RETRUN")
    @ApiModelProperty(value="二级单位：评估程序与结果")
    private String unitRetrun;

    @Column(name = "UNIT_OPINION")
    @ApiModelProperty(value="二级单位：聘用意见")
    private Object unitOpinion;

    @Column(name = "UNIT_USER")
    @ApiModelProperty(value="二级单位：主管负责人签字")
    private String unitUser;

    @Column(name = "UNIT_DATE")
    @ApiModelProperty(value="二级单位：日期")
    private String unitDate;

    @Column(name = "SCHOOL_OPINION")
    @ApiModelProperty(value="国际处：意见")
    private String schoolOpinion;

    @Column(name = "SCHOOL_USER")
    @ApiModelProperty(value="国际处：签字")
    private String schoolUser;

    @Column(name = "SCHOOL2_OPINION")
    @ApiModelProperty(value="国际处：分管：意见")
    private String school2Opinion;

    @Column(name = "SCHOOL2_USER")
    @ApiModelProperty(value="国际处：分管：签字")
    private String school2User;

    @Column(name = "SCHOOL3_OPINION")
    @ApiModelProperty(value="国际处：处长：意见")
    private String school3Opinion;

    @Column(name = "SCHOOL3_USER")
    @ApiModelProperty(value="国际处：处长：签名")
    private String school3User;

    @Column(name = "JOP_RETURN")
    @ApiModelProperty(value="工作证件办理结果")
    private String jopReturn;


    @Transient
    @ApiModelProperty(value="搜索用：申报时间段起")
    private String applyDateStart;

    @Transient
    @ApiModelProperty(value="搜索用：申报时间段止")
    private String applyDateEnd;




    public WsWzFire(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String applyUnit, Date applyDate, String name, String country, String teacherCode, String startEndDate, String content, BigDecimal schoolAmount, String age, String unitLevel, String unitCharacter, String unitRetrun, Object unitOpinion, String unitUser, String unitDate, String schoolOpinion, String schoolUser, String school2Opinion, String school2User, String school3Opinion, String school3User, String jopReturn) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.applyUnit = applyUnit;
        this.applyDate = applyDate;
        this.name = name;
        this.country = country;
        this.teacherCode = teacherCode;
        this.startEndDate = startEndDate;
        this.content = content;
        this.schoolAmount = schoolAmount;
        this.age = age;
        this.unitLevel = unitLevel;
        this.unitCharacter = unitCharacter;
        this.unitRetrun = unitRetrun;
        this.unitOpinion = unitOpinion;
        this.unitUser = unitUser;
        this.unitDate = unitDate;
        this.schoolOpinion = schoolOpinion;
        this.schoolUser = schoolUser;
        this.school2Opinion = school2Opinion;
        this.school2User = school2User;
        this.school3Opinion = school3Opinion;
        this.school3User = school3User;
        this.jopReturn = jopReturn;
    }

    public WsWzFire() {
        super();
    }
}