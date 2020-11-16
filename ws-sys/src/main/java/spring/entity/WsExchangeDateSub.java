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
@Table(name="WS_EXCHANGE_DATE_SUB")
public class WsExchangeDateSub {
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

    @Column(name = "START_DATE")
    @ApiModelProperty(value="开始时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDate;

    @Column(name = "END_DATE")
    @ApiModelProperty(value="结束时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;

    @Column(name = "CREATE_USER_ID")
    @ApiModelProperty(value="发起人")
    private String createUserId;

    @Column(name = "TITLE")
    @ApiModelProperty(value="资料名称")
    private String title;

    @Column(name = "SUB_USER_ID")
    @ApiModelProperty(value="发送人员")
    private String subUserId;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="备注")
    private String content;

    @Column(name = "ATTACH_ID")
    @ApiModelProperty(value="附件")
    private String attachId;

    @Column(name = "CONTENT_SUM")
    @ApiModelProperty(value="内容摘要")
    private String contentSum;


    public WsExchangeDateSub(String id, String createBy, Date createDate, String updateBy, Date updateDate, Date startDate, Date endDate, String createUserId, String title, String subUserId, String content, String attachId,String contentSum) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createUserId = createUserId;
        this.title = title;
        this.subUserId = subUserId;
        this.content = content;
        this.attachId = attachId;
        this.contentSum = contentSum;
    }

    public WsExchangeDateSub() {
        super();
    }
}