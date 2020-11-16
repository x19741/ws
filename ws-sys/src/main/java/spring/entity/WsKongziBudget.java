package spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Table(name="WS_KONGZI_BUDGET")
public class WsKongziBudget {

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

    @Column(name = "SERIAL_CODE")
    @ApiModelProperty(value="流水号")
    private String serialCode;

    @Column(name = "TITLE")
    @ApiModelProperty(value="标题")
    private String title;



    @Column(name = "TYPE")
    @ApiModelProperty(value="项目属性 1：常规，执行1年及以上；2：新设项目")
    private String type;

    @Column(name = "TARGET")
    @ApiModelProperty(value="项目目标")
    private String target;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="项目内容")
    private String content;

    @Column(name = "ADDRESS")
    @ApiModelProperty(value="场地安排")
    private String address;

    @Column(name = "NNT")
    @ApiModelProperty(value="参加人数")
    private String nnt;

    @Column(name = "PRO_THRONG")
    @ApiModelProperty(value="项目对应人群")
    private String proThrong;

    @Column(name = "START_DATE")
    @ApiModelProperty(value="项目开始时间")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    @Column(name = "END_DATE")
    @ApiModelProperty(value="项目结束时间")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    @Column(name = "DISBURSE_DETAIL")
    @ApiModelProperty(value="支出明细（文本框）")
    private String disburseDetail;

    @Column(name = "DISBURSE_AMOUNT")
    @ApiModelProperty(value="支出金额")
    private BigDecimal disburseAmount;

    @Column(name = "AMOUNT")
    @ApiModelProperty(value="支出总额")
    private BigDecimal amount;

    @Column(name = "ATTACH_ID")
    @ApiModelProperty(value="附件id")
    private String attachId;

    public WsKongziBudget(String id, String createBy, Date createDate, String updateBy, Date updateDate,String status,String serialCode,String title, String type, String target, String content, String address, String nnt, String proThrong, Date startDate, Date endDate, String disburseDetail, BigDecimal disburseAmount, BigDecimal amount,String attachId) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status=status;
        this.title=title;
        this.serialCode=serialCode;
        this.type = type;
        this.target = target;
        this.content = content;
        this.address = address;
        this.nnt = nnt;
        this.proThrong = proThrong;
        this.startDate = startDate;
        this.endDate = endDate;
        this.disburseDetail = disburseDetail;
        this.disburseAmount = disburseAmount;
        this.amount = amount;
        this.attachId=attachId;
    }

    public WsKongziBudget() {
        super();
    }
}