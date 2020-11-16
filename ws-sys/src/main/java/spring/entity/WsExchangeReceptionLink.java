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
@Table(name="WS_EXCHANGE_RECEPTION_LINK")
public class WsExchangeReceptionLink {
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
    @ApiModelProperty(value="父id")
    private String mainformid;

    @Column(name = "TITLE")
    @ApiModelProperty(value="环节名称")
    private String title;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "STRAT_END_DATE")
    @ApiModelProperty(value="时间")
    private Date stratEndDate;

    @Column(name = "ADDRESS")
    @ApiModelProperty(value="地点 ")
    private String address;

    @Column(name = "SCHOOL_USER")
    @ApiModelProperty(value="我校出席者")
    private String schoolUser;

    @Column(name = "LANGUAGE")
    @ApiModelProperty(value="会谈语种")
    private String language;

    @Column(name = "SUITING")
    @ApiModelProperty(value="着装")
    private String suiting;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="备注")
    private String content;

    public WsExchangeReceptionLink(String id, String createBy, Date createDate, String updateBy, Date updateDate, String mainformid, String title, Date stratEndDate, String address, String schoolUser, String language, String suiting, String content) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.mainformid = mainformid;
        this.title = title;
        this.stratEndDate = stratEndDate;
        this.address = address;
        this.schoolUser = schoolUser;
        this.language = language;
        this.suiting = suiting;
        this.content = content;
    }

    public WsExchangeReceptionLink() {
        super();
    }
}