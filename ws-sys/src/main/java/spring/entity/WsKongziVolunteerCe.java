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
@Table(name = "WS_KONGZI_VOLUNTEER_CE")
public class WsKongziVolunteerCe {

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

    @Column(name = "CERTIFICATE_NAME")
    @ApiModelProperty(value="获得证书情况：证书名称")
    private String certificateName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "CERTIFICATE_DATE")
    @ApiModelProperty(value="获得证书情况：获得时间")
    private Date certificateDate;

    @Column(name = "CERTIFICATE_GRADE")
    @ApiModelProperty(value="获得证书情况：证书名称及等级/成绩")
    private String certificateGrade;

    public WsKongziVolunteerCe(String id, String createBy, Date createDate, String updateBy, Date updateDate, String mainformid, String certificateName, Date certificateDate, String certificateGrade) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.mainformid = mainformid;
        this.certificateName = certificateName;
        this.certificateDate = certificateDate;
        this.certificateGrade = certificateGrade;
    }

    public WsKongziVolunteerCe() {
        super();
    }
}