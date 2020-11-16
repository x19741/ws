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
@Table(name="WS_WZ_FIRE_CONTINUE")
public class WsWzFireContinue {
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

    @Column(name = "APPLY_UNIT")
    @ApiModelProperty(value="申报单位")
    private String applyUnit;

    @Column(name = "APPLY_DATE")
    @ApiModelProperty(value="申报时间")
    private Date applyDate;

    @Column(name = "NAME")
    @ApiModelProperty(value="姓名")
    private String name;

    @Column(name = "TITLE")
    @ApiModelProperty(value="标题")
    private String title;

    public WsWzFireContinue(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String applyUnit, Date applyDate, String name, String title) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.applyUnit = applyUnit;
        this.applyDate = applyDate;
        this.name = name;
        this.title = title;
    }

    public WsWzFireContinue() {
        super();
    }
}