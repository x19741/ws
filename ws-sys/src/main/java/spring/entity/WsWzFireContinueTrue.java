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
@Table(name="WS_WZ_FIRE_CONTINUE_TRUE")
public class WsWzFireContinueTrue {
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

    @Column(name = "MAINFORMID")
    @ApiModelProperty(value="父ID")
    private String mainformid;

    @Column(name = "ORDER_ID")
    @ApiModelProperty(value="序号")
    private String orderId;

    @Column(name = "NAME")
    @ApiModelProperty(value="姓名")
    private String name;

    @Column(name = "SEX")
    @ApiModelProperty(value="性别")
    private String sex;

    @Column(name = "COUNTRY")
    @ApiModelProperty(value="国籍")
    private String country;

    @Column(name = "AGE")
    @ApiModelProperty(value="年龄")
    private Integer age;

    @Column(name = "START_DATE")
    @ApiModelProperty(value="聘期：开始时间")
    private Date startDate;

    @Column(name = "END_DATE")
    @ApiModelProperty(value="聘期：结束时间")
    private Date endDate;

    public WsWzFireContinueTrue(String id, String createBy, Date createDate, String updateBy, Date updateDate, String mainformid, String orderId, String name, String sex, String country, Integer age, Date startDate, Date endDate) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.mainformid = mainformid;
        this.orderId = orderId;
        this.name = name;
        this.sex = sex;
        this.country = country;
        this.age = age;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WsWzFireContinueTrue() {
        super();
    }
}