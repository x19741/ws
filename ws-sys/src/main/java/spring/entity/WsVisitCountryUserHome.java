package spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rabbitmq.utility.IntAllocator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "WS_VISIT_COUNTRY_USER_HOME")
public class WsVisitCountryUserHome {
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

    @Column(name = "TITLE")
    @ApiModelProperty(value = "称谓")
    private String title;

    @Column(name = "NAME")
    @ApiModelProperty(value = "姓名")
    private String name;

    @Column(name = "AGE")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @Column(name = "POLITICS_STATUS")
    @ApiModelProperty(value = "政治面貌")
    private String politicsStatus;

    @Column(name = "JOB_UNIT")
    @ApiModelProperty(value = "工作单位")
    private String jobUnit;

    @Column(name = "DEPT")
    @ApiModelProperty(value = "职务")
    private String dept;

    @Column(name = "ADDRESS")
    @ApiModelProperty(value = "居住地")
    private String address;

    @Column(name = "IS_FOREIGN_NATIONALITY")
    @ApiModelProperty(value = "是否取得外国国籍、境外长期或永久居留权")
    private String isForeignNationality;

    public WsVisitCountryUserHome(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String mainformid, String title, String name, Integer age, String politicsStatus, String jobUnit, String dept, String address, String isForeignNationality) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.mainformid = mainformid;
        this.title = title;
        this.name = name;
        this.age = age;
        this.politicsStatus = politicsStatus;
        this.jobUnit = jobUnit;
        this.dept = dept;
        this.address = address;
        this.isForeignNationality = isForeignNationality;
    }

    public WsVisitCountryUserHome() {
        super();
    }
}