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
@Table(name="WS_PRESENT")
public class WsPresent {

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

    @Column(name = "PRESENT_NAME")
    @ApiModelProperty(value="礼品名称")
    private String presentName;

    @Column(name = "PRESENT_TYPE")
    @ApiModelProperty(value="礼品类型")
    private String presentType;

    @Column(name = "ISSUE_USER")
    @ApiModelProperty(value="发布人")
    private String issueUser;

    @Column(name = "ISSUE_DEPT")
    @ApiModelProperty(value="发布部门")
    private String issueDept;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "ISSUE_DATE")
    @ApiModelProperty(value="发布时间")
    private Date issueDate;

    @Column(name = "NUM")
    @ApiModelProperty(value="剩余数量")
    private Long num;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="描述")
    private String content;

    @Column(name = "ISSUE_STATUS")
    @ApiModelProperty(value="发布状态")
    private String issueStatus;



    @Transient
    @ApiModelProperty(value="搜索用：搜索时间段起")
    private String searchDateStart;

    @Transient
    @ApiModelProperty(value="搜索用：搜索时间段止")
    private String searchDateEnd;

    public WsPresent(String id, String createBy, Date createDate, String updateBy, Date updateDate, String presentName, String presentType, String issueUser, String issueDept, Date issueDate, Long num, String content, String issueStatus) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.presentName = presentName;
        this.presentType = presentType;
        this.issueUser = issueUser;
        this.issueDept = issueDept;
        this.issueDate = issueDate;
        this.num = num;
        this.content = content;
        this.issueStatus=issueStatus;
    }

    public WsPresent() {
        super();
    }


}