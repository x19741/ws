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
@Table(name = "WS_VISIT_COUNTRY_ADDRESS")
public class WsVisitCountryAddress {
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
    @ApiModelProperty(value = "出访地")
    private String address;

    @Column(name = "INBOUND_TYPE")
    @ApiModelProperty(value = "入境类型")
    private String inboundType;

    @Column(name = "CITY")
    @ApiModelProperty(value = "城市")
    private String city;

    @Column(name = "STOP_TYPE")
    @ApiModelProperty(value = "停留类型")
    private String stopType;

    @Column(name = "INVITER_UNIT")
    @ApiModelProperty(value = "邀请单位")
    private String inviterUnit;

    @Column(name = "VISE_MODE")
    @ApiModelProperty(value = "签证方式")
    private String viseMode;

    @Column(name = "ADDRESS_TYPE")
    @ApiModelProperty(value = "出访类型")
    private String addressType;

    @Column(name = "INVITER_USER")
    @ApiModelProperty(value = "邀请人")
    private String inviterUser;

    @Column(name = "VISE_TYPE")
    @ApiModelProperty(value = "签证类型")
    private String viseType;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "INBOUND_DATE")
    @ApiModelProperty(value = "入境日期")
    private Date inboundDate;

    @Column(name = "STOP_DAY_NUM")
    @ApiModelProperty(value = "停留天数")
    private BigDecimal stopDayNum;

    public WsVisitCountryAddress(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String mainformid, String address, String inboundType, String city, String stopType, String inviterUnit, String viseMode, String addressType, String inviterUser, String viseType, Date inboundDate, BigDecimal stopDayNum) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.mainformid = mainformid;
        this.address = address;
        this.inboundType = inboundType;
        this.city = city;
        this.stopType = stopType;
        this.inviterUnit = inviterUnit;
        this.viseMode = viseMode;
        this.addressType = addressType;
        this.inviterUser = inviterUser;
        this.viseType = viseType;
        this.inboundDate = inboundDate;
        this.stopDayNum = stopDayNum;
    }

    public WsVisitCountryAddress() {
        super();
    }
}