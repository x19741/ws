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
@Table(name="WS_PRO_PROTOCOL")
public class WsProProtocol {

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

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "APPLY_DATE")
    @ApiModelProperty(value="申报时间")
    private Date applyDate;

    @Column(name = "PROTOCOL_TYPE")
    @ApiModelProperty(value="协议类型")
    private String protocolType;

    @Column(name = "PROTOCOL_NAME")
    @ApiModelProperty(value="协议名称")
    private String protocolName;

    @Column(name = "SCHOOL")
    @ApiModelProperty(value="合作高校")
    private String school;

    @Column(name = "IS_SIGN")
    @ApiModelProperty(value="是否签署 ：1是 0否")
    private String isSign;

    @Column(name = "SIGN_TYPE")
    @ApiModelProperty(value="协议签署方式说明 1现场签署 2,寄签")
    private String signType;

    @Column(name = "PROTOCOL_NUM")
    @ApiModelProperty(value="所需协议份数")
    private String protocolNum;

    @Column(name = "APPLY_USER")
    @ApiModelProperty(value="经办人")
    private String applyUser;

    @Column(name = "PHONE")
    @ApiModelProperty(value="联系电话")
    private String phone;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="内容摘要：")
    private String content;

    @Column(name = "单位领导审批意见（签字、盖章）：")
    @ApiModelProperty(value="DEAN")
    private String dean;

    @Column(name = "DEAN_DATE")
    @ApiModelProperty(value="单位领导审批意见（签字、盖章）：日期")
    private String deanDate;

    @Column(name = "SUM_SCHOOL_")
    @ApiModelProperty(value="研究生审批意见（签字、盖章）：")
    private String sumSchool;

    @Column(name = "SUM_SCHOOL_DATE")
    @ApiModelProperty(value="研究生审批意见（签字、盖章）：：日期")
    private String sumSchoolDate;

    @Transient
    @ApiModelProperty(value="搜索用：申报时间段起")
    private String applyDateStart;

    @Transient
    @ApiModelProperty(value="搜索用：申报时间段止")
    private String applyDateEnd;



    public WsProProtocol(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String applyUnit, Date applyDate, String protocolType, String protocolName, String school, String isSign, String signType, String protocolNum, String applyUser, String phone, String content, String dean, String deanDate, String sumSchool, String sumSchoolDate) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.applyUnit = applyUnit;
        this.applyDate = applyDate;
        this.protocolType = protocolType;
        this.protocolName = protocolName;
        this.school = school;
        this.isSign = isSign;
        this.signType = signType;
        this.protocolNum = protocolNum;
        this.applyUser = applyUser;
        this.phone = phone;
        this.content = content;
        this.dean = dean;
        this.deanDate = deanDate;
        this.sumSchool = sumSchool;
        this.sumSchoolDate = sumSchoolDate;
    }

    public WsProProtocol() {
        super();
    }
}