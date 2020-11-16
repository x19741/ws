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
@Table(name = "WS_KONGZI_VOLUNTEER_ED")
public class WsKongziVolunteerEd {

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

    @Column(name = "MAINFORMID")
    @ApiModelProperty(value="父ID")
    private String mainformid;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "EDUCATION_START_DATE")
    @ApiModelProperty(value="教育背景：起时间")
    private Date educationStartDate;

    @Column(name = "EDUCATION_SCHOOL")
    @ApiModelProperty(value="教育背景：学校")
    private String educationSchool;

    @Column(name = "EDUCATION_SPECIALTY")
    @ApiModelProperty(value="教育背景：专业及学位")
    private String educationSpecialty;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "EDUCATION_END_DATE")
    @ApiModelProperty(value="教育背景：止时间")
    private Date educationEndDate;

    public WsKongziVolunteerEd(String id, String createBy, Date createDate, String updateBy, Date updateDate, String mainformid, Date educationStartDate, String educationSchool, String educationSpecialty, Date educationEndDate) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.mainformid = mainformid;
        this.educationStartDate = educationStartDate;
        this.educationSchool = educationSchool;
        this.educationSpecialty = educationSpecialty;
        this.educationEndDate = educationEndDate;
    }

    public WsKongziVolunteerEd() {
        super();
    }
}