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
@Table(name = "WS_VISIT_COUNTRY_REGION")
public class WsVisitCountryRegion {
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

    @Column(name = "ADDRESS")
    @ApiModelProperty(value = "地区")
    private String address;

    @Column(name = "DAY")
    @ApiModelProperty(value = "天数")
    private BigDecimal day;

    @Column(name = "TRAFFIC_CODE")
    @ApiModelProperty(value = "入境日期根据航班")
    private String trafficCode;

    @Column(name = "INVITER")
    @ApiModelProperty(value = "邀请/组团单位及邀请人姓名和职务（中文）")
    private String inviter;

    public WsVisitCountryRegion(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String mainformid, String address, BigDecimal day, String trafficCode, String inviter) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.mainformid = mainformid;
        this.address = address;
        this.day = day;
        this.trafficCode = trafficCode;
        this.inviter = inviter;
    }

    public WsVisitCountryRegion() {
        super();
    }
}