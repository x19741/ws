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
@Table(name = "WS_KONGZI_VOLUNTEER")
public class WsKongziVolunteer {

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

    @Column(name = "NATION")
    @ApiModelProperty(value="民族")
    private String nation;

    @Column(name = "CADRE")
    @ApiModelProperty(value="政治面貌")
    private String cadre;

    @Column(name = "POLITICS")
    @ApiModelProperty(value="学生干部职务")
    private String politics;

    @Column(name = "COLLEGE")
    @ApiModelProperty(value="所在学院")
    private String college;

    @Column(name = "CLASS1")
    @ApiModelProperty(value="专业/班级")
    private String class1;

    @Column(name = "ICON")
    @ApiModelProperty(value="头像")
    private String icon;

    @Column(name = "STUDENT_CODE")
    @ApiModelProperty(value="学号")
    private String studentCode;

    @Column(name = "SPECIALITY")
    @ApiModelProperty(value="才艺专长")
    private String speciality;

    @Column(name = "EMAIL")
    @ApiModelProperty(value="email")
    private String email;

    @Column(name = "PHONE")
    @ApiModelProperty(value="电话/手机")
    private String phone;

    public WsKongziVolunteer(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String username, String sex, Date birthday, String nation, String cadre, String politics, String college, String class1, String icon, String studentCode, String speciality, String email, String phone) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.nation = nation;
        this.cadre = cadre;
        this.politics = politics;
        this.college = college;
        this.class1 = class1;
        this.icon = icon;
        this.studentCode = studentCode;
        this.speciality = speciality;
        this.email = email;
        this.phone = phone;
    }

    public WsKongziVolunteer() {
        super();
    }
}