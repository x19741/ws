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
@Table(name="WS_KONGZI_VOLUNTEER_SOCIETY")
public class WsKongziVolunteerSociety {

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

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "SOCIETY_START_DATE")
    @ApiModelProperty(value="工作及社会实践经历：起时间")
    private Date societyStartDate;

    @Column(name = "SOCIETY_UNIT")
    @ApiModelProperty(value="工作及社会实践经历：工作单位")
    private String societyUnit;

    @Column(name = "SOCIETY_CONTENT")
    @ApiModelProperty(value="工作及社会实践经历：工作内容")
    private String societyContent;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "SOCIETY_END_DATE")
    @ApiModelProperty(value="工作及社会实践经历：止时间")
    private Date societyEndDate;

    public WsKongziVolunteerSociety(String id, String createBy, Date createDate, String updateBy, Date updateDate, String mainformid, Date societyStartDate, String societyUnit, String societyContent, Date societyEndDate) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.mainformid = mainformid;
        this.societyStartDate = societyStartDate;
        this.societyUnit = societyUnit;
        this.societyContent = societyContent;
        this.societyEndDate = societyEndDate;
    }

    public WsKongziVolunteerSociety() {
        super();
    }
}