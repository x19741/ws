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
@Table(name="WS_VISIT_FUND_INFO")
public class WsVisitFundInfo {
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

    @Column(name = "CONTINENT")
    @ApiModelProperty(value="区域")
    private String continent;

    @Column(name = "COUNTRY")
    @ApiModelProperty(value="国家(地区)")
    private String country;

    @Column(name = "CITY")
    @ApiModelProperty(value="城市")
    private String city;

    @Column(name = "CURRENCY")
    @ApiModelProperty(value="币种")
    private String currency;

    @Column(name = "ACCOMMODATION")
    @ApiModelProperty(value="住宿费（每人每天）")
    private String accommodation;

    @Column(name = "MEALS")
    @ApiModelProperty(value="伙食费（每人每天）")
    private String meals;

    @Column(name = "VARIED")
    @ApiModelProperty(value="公杂费（每人每天）")
    private String varied;

    @Column(name = "CONTINENT_ID")
    @ApiModelProperty(value="区域iD")
    private String continentId;

    @Column(name = "COUNTRY_ID")
    @ApiModelProperty(value="国家(地区)ID")
    private String countryId;

    @Column(name = "CITY_ID")
    @ApiModelProperty(value="城市ID")
    private String cityId;




    public WsVisitFundInfo(String id, String createBy, Date createDate, String updateBy, Date updateDate, String continent, String country, String city
            , String currency, String accommodation, String meals, String varied,String continentId,String countryId,String cityId
    ) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.continent = continent;
        this.country = country;
        this.city = city;
        this.currency = currency;
        this.accommodation = accommodation;
        this.meals = meals;
        this.varied = varied;
        this.continentId = continentId;
        this.countryId = countryId;
        this.cityId = cityId;
    }

    public WsVisitFundInfo() {
        super();
    }
}