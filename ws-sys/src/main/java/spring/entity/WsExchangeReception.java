package spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name="WS_EXCHANGE_RECEPTION")
public class WsExchangeReception {
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

    @Column(name = "GROUP_NAME")
    @ApiModelProperty(value="来访团组")
    private String groupName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "GROUP_DATE")
    @ApiModelProperty(value="来访时间")
    private Date groupDate;

    @Column(name = "ADDRESS")
    @ApiModelProperty(value="来访地点")
    private String address;

    @Column(name = "GROUP_USER")
    @ApiModelProperty(value="团长")
    private String groupUser;

    @Column(name = "GROUP_USERS")
    @ApiModelProperty(value="来访成员")
    private String groupUsers;

    @Column(name = "GROUP_THING")
    @ApiModelProperty(value="来访目的")
    private String groupThing;

    @Column(name = "SCHOOL_USER")
    @ApiModelProperty(value="国际处联系人")
    private String schoolUser;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="内容")
    private String content;

    @Column(name = "IS_ENGLISH")
    @ApiModelProperty(value="是否英文表单 1：是 0否")
    private String isEnglish;



    @Transient
    @ApiModelProperty(value="搜索用：来访时间段起")
    private String groupDateStart;

    @Transient
    @ApiModelProperty(value="搜索用：来访时间段止")
    private String groupDateEnd;

    public WsExchangeReception(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String groupName, Date groupDate, String address, String groupUser, String groupUsers, String groupThing, String schoolUser, String content,String isEnglish) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.groupName = groupName;
        this.groupDate = groupDate;
        this.address = address;
        this.groupUser = groupUser;
        this.groupUsers = groupUsers;
        this.groupThing = groupThing;
        this.schoolUser = schoolUser;
        this.content = content;
        this.isEnglish=isEnglish;
    }

    public WsExchangeReception() {
        super();
    }
}