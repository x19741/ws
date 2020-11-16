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
@Table(name = "WS_VISIT_COUNTRY_FUND_INFO")
public class WsVisitCountryFundInfo {
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

    @Column(name = "AMOUNT_TYPE")
    @ApiModelProperty(value = "费用类型")
    private String amountType;

    @Column(name = "STANDARD")
    @ApiModelProperty(value = "标准")
    private String standard;

    @Column(name = "DAY")
    @ApiModelProperty(value = "天数")
    private BigDecimal day;

    @Column(name = "NUM")
    @ApiModelProperty(value = "人数")
    private Integer num;

    @Column(name = "AMOUNT_USD")
    @ApiModelProperty(value = "小计（美元）")
    private BigDecimal amountUsd;

    @Column(name = "AMOUNT")
    @ApiModelProperty(value = "小计（人民币）")
    private BigDecimal amount;

    @Column(name = "EXCHANGE_RATE")
    @ApiModelProperty(value = "汇率")
    private BigDecimal exchangeRate;

    public WsVisitCountryFundInfo(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String mainformid, String amountType, String standard, BigDecimal day, Integer num, BigDecimal amountUsd, BigDecimal amount, BigDecimal exchangeRate) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.mainformid = mainformid;
        this.amountType = amountType;
        this.standard = standard;
        this.day = day;
        this.num = num;
        this.amountUsd = amountUsd;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
    }

    public WsVisitCountryFundInfo() {
        super();
    }
}