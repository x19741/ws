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
@Table(name = "WS_VISIT_COUNTRY_REPORT_TRA")
public class WsVisitCountryReportTra {
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

    @Column(name = "AIRPLANE")
    @ApiModelProperty(value = "航班车次")
    private String airplane;

    @Column(name = "START_CITY")
    @ApiModelProperty(value = "出发城市")
    private String startCity;

    @Column(name = "END_CITY")
    @ApiModelProperty(value = "抵达城市")
    private String endCity;

    @Column(name = "START_DATE")
    @ApiModelProperty(value = "出发时间")
    private Date startDate;

    @Column(name = "END_DATE")
    @ApiModelProperty(value = "抵达时间")
    private Date endDate;

    public WsVisitCountryReportTra(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String mainformid, String airplane, String startCity, String endCity, Date startDate, Date endDate) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.mainformid = mainformid;
        this.airplane = airplane;
        this.startCity = startCity;
        this.endCity = endCity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WsVisitCountryReportTra() {
        super();
    }
}