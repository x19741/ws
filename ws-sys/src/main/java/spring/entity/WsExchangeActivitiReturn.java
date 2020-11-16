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
@Table(name="WS_EXCHANGE_ACTIVITI_RETURN")
public class WsExchangeActivitiReturn {
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

    @Column(name = "MAINFORMID")
    @ApiModelProperty(value="父id")
    private String mainformid;

    @Column(name = "GROUP_NAME")
    @ApiModelProperty(value="来访团组名称")
    private String groupName;

    @Column(name = "GROUP_USER_ENGLISH")
    @ApiModelProperty(value="姓名（中英文）")
    private String groipUserEnglish;

    @Column(name = "GROUP_POST")
    @ApiModelProperty(value="职务")
    private String groupPost;

    @Column(name = "GROUP_NUM")
    @ApiModelProperty(value="随行人数")
    private String groupNum;

    @Column(name = "GROUP_DATE")
    @ApiModelProperty(value="来访日期")
    private Date groupDate;

    @Column(name = "GROUP_USER")
    @ApiModelProperty(value="联系人")
    private String groupUser;

    @Column(name = "GROUP_PHONE")
    @ApiModelProperty(value="电话")
    private String groupPhone;

    @Column(name = "UNIT_LEAD")
    @ApiModelProperty(value="接待单位：主要领导")
    private String unitLead;

    @Column(name = "UNIT_POST")
    @ApiModelProperty(value="接待单位：职务")
    private String unitPost;

    @Column(name = "UNIT_USER")
    @ApiModelProperty(value="接待单位：联系人")
    private String unitUser;

    @Column(name = "UNIT_PHONE")
    @ApiModelProperty(value="接待单位：电话")
    private String unitPhone;

    @Column(name = "PURPOSE")
    @ApiModelProperty(value="来访目的")
    private String purpose;

    @Column(name = "RESULT")
    @ApiModelProperty(value="交流达成成果")
    private String result;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="批注")
    private String content;

    @Column(name = "UNIT")
    @ApiModelProperty(value="填表单位")
    private String unit;

    @Column(name = "START_DATE")
    @ApiModelProperty(value="填表时间")
    private Date startDate;

    public WsExchangeActivitiReturn(){};
    public WsExchangeActivitiReturn(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String mainformid, String groupName, String groipUserEnglish, String groupPost, String groupNum, Date groupDate, String groupUser, String groupPhone, String unitLead, String unitPost, String unitUser, String unitPhone, String purpose, String result, String content, String unit, Date startDate) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.mainformid = mainformid;
        this.groupName = groupName;
        this.groipUserEnglish = groipUserEnglish;
        this.groupPost = groupPost;
        this.groupNum = groupNum;
        this.groupDate = groupDate;
        this.groupUser = groupUser;
        this.groupPhone = groupPhone;
        this.unitLead = unitLead;
        this.unitPost = unitPost;
        this.unitUser = unitUser;
        this.unitPhone = unitPhone;
        this.purpose = purpose;
        this.result = result;
        this.content = content;
        this.unit = unit;
        this.startDate = startDate;
    }

}