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
@Table(name="WS_WZ_MAINTAIN")
public class WsWzMaintain {
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

    @Column(name = "APPLY_USER")
    @ApiModelProperty(value="外教名称")
    private String applyUser;

    @Column(name = "ADDRESS")
    @ApiModelProperty(value="住址")
    private String address;

    @Column(name = "PHONE")
    @ApiModelProperty(value="联系电话")
    private String phone;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "START_DATE")
    @ApiModelProperty(value="可接受维修时间段：起")
    private Date startDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "END_DATE")
    @ApiModelProperty(value="可接受维修时间段：止")
    private Date endDate;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="问题描述")
    private String content;

    @Column(name = "PHOTO")
    @ApiModelProperty(value="照片")
    private String photo;

    public WsWzMaintain(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String applyUser, String address, String phone, Date startDate, Date endDate, String content, String photo) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.applyUser = applyUser;
        this.address = address;
        this.phone = phone;
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
        this.photo = photo;
    }

    public WsWzMaintain() {
        super();
    }
}