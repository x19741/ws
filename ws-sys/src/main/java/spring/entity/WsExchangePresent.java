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
@Table(name="WS_EXCHANGE_PRESENT")
public class WsExchangePresent {

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

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "APPLY_DATE")
    @ApiModelProperty(value="申请时间")
    private Date applyDate;

    @Column(name = "APPLY_THING")
    @ApiModelProperty(value="申请事由")
    private String applyThing;

    @Column(name = "PRESENT_NAME")
    @ApiModelProperty(value="礼品名称")
    private String presentName;

    @Column(name = "PRESENT_NUM")
    @ApiModelProperty(value="数量")
    private String presentNum;

    @Column(name = "USERNAME")
    @ApiModelProperty(value="领用人")
    private String username;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="备注")
    private String content;


    @Transient
    @ApiModelProperty(value="搜索用：申请时间段起")
    private String applyDateStart;

    @Transient
    @ApiModelProperty(value="搜索用：申请时间段止")
    private String applyDateEnd;

    public WsExchangePresent(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, Date applyDate, String applyThing, String presentName, String presentNum, String username, String content) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.applyDate = applyDate;
        this.applyThing = applyThing;
        this.presentName = presentName;
        this.presentNum = presentNum;
        this.username = username;
        this.content = content;
    }

    public WsExchangePresent() {
        super();
    }

}