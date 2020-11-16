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
@Table(name="WS_NOTICE")
public class WsNotice {
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

    @Column(name = "ORDER_ID")
    @ApiModelProperty(value="排序ID")
    private String orderId;

    @Column(name = "TITLE")
    @ApiModelProperty(value="标题")
    private String title;

    @Column(name = "ISSUE_STATUS")
    @ApiModelProperty(value="发布状态")
    private String issueStatus;

    @Column(name = "ATTACH_ID")
    @ApiModelProperty(value="附件ID")
    private String attachId;

    @Column(name = "IMAGE_ID")
    @ApiModelProperty(value="封面")
    private String imageId;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="内容")
    private String content;

    @Column(name = "NOTICE_TYPE")
    @ApiModelProperty(value="公告类型")
    private String noticeType;

    @Column(name = "MODEL_TYPE")
    @ApiModelProperty(value="模块类型")
    private String modelType;

    @Column(name = "ISSUE_DEPT")
    @ApiModelProperty(value="发布部门")
    private String issueDept;



    @Transient
    @ApiModelProperty(value="搜索用：搜索时间段起")
    private String searchDateStart;

    @Transient
    @ApiModelProperty(value="搜索用：搜索时间段止")
    private String searchDateEnd;

    public WsNotice(String id, String createBy, Date createDate, String updateBy, Date updateDate, String orderId, String title, String issueStatus, String attachId, String imageId, String content, String noticeType, String modelType, String issueDept) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.orderId = orderId;
        this.title = title;
        this.issueStatus = issueStatus;
        this.attachId = attachId;
        this.imageId = imageId;
        this.content = content;
        this.noticeType = noticeType;
        this.modelType = modelType;
        this.issueDept = issueDept;
    }

    public WsNotice() {
        super();
    }
}