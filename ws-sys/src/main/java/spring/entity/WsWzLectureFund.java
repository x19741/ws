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
@Table(name="WS_WZ_LECTURE_FUND")
public class WsWzLectureFund {
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

    @Column(name = "MAINFORMID")
    @ApiModelProperty(value="父id")
    private String mainformid;

    @Column(name = "SUBJECT")
    @ApiModelProperty(value="申请资助科目")
    private String subject;

    @Column(name = "AMOUNT")
    @ApiModelProperty(value="金额（万元）")
    private BigDecimal amount;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="测算依据")
    private String content;

    public WsWzLectureFund(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String mainformid, String subject, BigDecimal amount, String content) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.mainformid = mainformid;
        this.subject = subject;
        this.amount = amount;
        this.content = content;
    }

    public WsWzLectureFund() {
        super();
    }
}