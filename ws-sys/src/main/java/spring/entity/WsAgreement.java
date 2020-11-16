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
import java.util.Date;

@Data
@Table(name="WS_AGREEMENT")
public class WsAgreement {
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

    @Column(name = "TITLE")
    @ApiModelProperty(value="标题")
    private String title;

    @Column(name = "AGREEMENT_TYPE")
    @ApiModelProperty(value="协议类型")
    private String agreementType;

    @Column(name = "USER_ID")
    @ApiModelProperty(value="发布人")
    private String userId;

    @Column(name = "USER_DEPT")
    @ApiModelProperty(value="发布部门")
    private String userDept;

    @Column(name = "START_DATE")
    @ApiModelProperty(value="协议签署时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDate;

    @Column(name = "END_DATE")
    @ApiModelProperty(value="到期时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;

    @Column(name = "IMPORTANCE_TYPE")
    @ApiModelProperty(value="重要程度1:一般，2重要")
    private String importanceType;

    @Column(name = "ATTACH_ID")
    @ApiModelProperty(value="附件")
    private String attachId;

    @Column(name = "STATUS")
    @ApiModelProperty(value="状态1：启用，2：禁用")
    private String status;


    @Column(name = "CONTENT")
    @ApiModelProperty(value="内容")
    private String content;


    @Transient
    @ApiModelProperty(value="搜索用：搜索时间段起")
    private String searchDateStart;

    @Transient
    @ApiModelProperty(value="搜索用：搜索时间段止")
    private String searchDateEnd;

    public WsAgreement(String id, String createBy, Date createDate, String updateBy, Date updateDate, String title, String agreementType, String userId, String userDept, Date startDate, Date endDate, String importanceType, String attachId, String status, String content) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.title = title;
        this.agreementType = agreementType;
        this.userId = userId;
        this.userDept = userDept;
        this.startDate = startDate;
        this.endDate = endDate;
        this.importanceType = importanceType;
        this.attachId = attachId;
        this.status = status;
        this.content = content;
    }

    public WsAgreement() {
        super();
    }
}