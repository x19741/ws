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
@Table(name="WS_EXCHANGE_ACTIVITI")
public class WsExchangeActiviti {

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

    @Column(name = "REPLACE_APPLY_UNIT")
    @ApiModelProperty(value="申报单位")
    private String replaceApplyUnit;

    @Column(name = "APPLY_UNIT")
    @ApiModelProperty(value="承办单位")
    private String applyUnit;

    @Column(name = "APPLY_USER")
    @ApiModelProperty(value="申报单位联系人")
    private String applyUser;

    @Column(name = "PHONE")
    @ApiModelProperty(value="手机")
    private String phone;

    @Column(name = "PHONE_CALL")
    @ApiModelProperty(value="办公电话")
    private String phoneCall;

    @Column(name = "TITLE")
    @ApiModelProperty(value="活动名称（中文）")
    private String title;

    @Column(name = "TITLE_ENGLISH")
    @ApiModelProperty(value="活动名称（英文）")
    private String titleEnglish;

    @Column(name = "ADDRESS")
    @ApiModelProperty(value="活动地点")
    private String address;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "ACTIVITY_START_DATE")
    @ApiModelProperty(value="活动时间:起")
    private Date activityStartDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "ACTIVITY_END_DATE")
    @ApiModelProperty(value="活动时间:止")
    private Date activityEndDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "APPLY_DATE")
    @ApiModelProperty(value="申报时间")
    private Date applyDate;

    @Column(name = "ACTIVITY_TYPE")
    @ApiModelProperty(value="活动类型")
    private String activityType;

    @Column(name = "ACTIVITY_FORM")
    @ApiModelProperty(value="活动形式")
    private String activityForm;

    @Column(name = "ORG_ADDRESS")
    @ApiModelProperty(value="活动涉及国/境外机构名称")
    private String orgAddress;

    @Column(name = "ORG_NAME")
    @ApiModelProperty(value="涉国（境）外国家及地区")
    private String orgName;

    @Column(name = "ORG_CONTINENT")
    @ApiModelProperty(value="涉国（境）外国家及地区所属大洲")
    private String orgContinent;

    @Column(name = "IS_CONSULATE")
    @ApiModelProperty(value="是否涉及领事馆： 1是0否")
    private String isConsulate;

    @Column(name = "IS_SENSITIVITY")
    @ApiModelProperty(value="是否涉及敏感内容：1是0否")
    private String isSensitivity;

    @Column(name = "ORG_ATTACH")
    @ApiModelProperty(value="活动涉及国/境外人员（简介可另附件）：")
    private String orgAttach;

    @Column(name = "MAINLAND_NUM")
    @ApiModelProperty(value="预计参加人数：大陆")
    private String mainlandNum;

    @Column(name = "TAIWAN_NUM")
    @ApiModelProperty(value="预计参加人数：台")
    private String taiwanNum;

    @Column(name = "HONGKONG_NUM")
    @ApiModelProperty(value="预计参加人数：港澳")
    private String hongkongNum;

    @Column(name = "FOREIGN_NUM")
    @ApiModelProperty(value="预计参加人数：国外")
    private String foreignNum;

    @Column(name = "SUM_NUM")
    @ApiModelProperty(value="总数")
    private String sumNum;

    @Column(name = "IS_SCHOOL_USERS")
    @ApiModelProperty(value="是否邀请校领导出席")
    private String isSchoolUsers;

    @Column(name = "SCHOOL_USERS")
    @ApiModelProperty(value="我校拟出席人员姓名及职位")
    private String schoolUsers;

    @Column(name = "NEWS_UNIT")
    @ApiModelProperty(value="拟邀请新闻单位")
    private String newsUnit;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="活动内容，日程及目的")
    private String content;

    @Column(name = "AMOUNT_PUR")
    @ApiModelProperty(value="经费预算及用途")
    private String amountPur;

    @Column(name = "APPLY_OPINION")
    @ApiModelProperty(value="申报或承办单位对活动参与人员及活动内容的审核意见")
    private String applyOpinion;

    @Column(name = "AMOUNT_SOU")
    @ApiModelProperty(value="经费来源")
    private String amountSou;

    @Column(name = "AMOUNT_ELSE")
    @ApiModelProperty(value="其他经费")
    private String amountElse;

    public WsExchangeActiviti(String id, String createBy, Date createDate, String updateBy, Date updateDate, String status, String replaceApplyUnit
            , String applyUnit, String applyUser, String phone, String phoneCall, String title, String titleEnglish, String address, Date activityStartDate
            , Date activityEndDate, Date applyDate, String activityType, String activityForm, String orgAddress, String orgName, String orgContinent
            , String isConsulate, String isSensitivity, String orgAttach, String mainlandNum, String taiwanNum, String hongkongNum
            , String foreignNum, String sumNum,String isSchoolUsers, String schoolUsers, String newsUnit, String content, String amountPur, String applyOpinion
            , String amountSou, String amountElse) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.status = status;
        this.replaceApplyUnit = replaceApplyUnit;
        this.applyUnit = applyUnit;
        this.applyUser = applyUser;
        this.phone = phone;
        this.phoneCall = phoneCall;
        this.title = title;
        this.titleEnglish = titleEnglish;
        this.address = address;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        this.applyDate = applyDate;
        this.activityType = activityType;
        this.activityForm = activityForm;
        this.orgAddress = orgAddress;
        this.orgName = orgName;
        this.orgContinent = orgContinent;
        this.isConsulate = isConsulate;
        this.isSensitivity = isSensitivity;
        this.orgAttach = orgAttach;
        this.mainlandNum = mainlandNum;
        this.taiwanNum = taiwanNum;
        this.hongkongNum = hongkongNum;
        this.foreignNum = foreignNum;
        this.sumNum = sumNum;
        this.isSchoolUsers=isSchoolUsers;
        this.schoolUsers = schoolUsers;
        this.newsUnit = newsUnit;
        this.content = content;
        this.amountPur = amountPur;
        this.applyOpinion = applyOpinion;
        this.amountSou = amountSou;
        this.amountElse = amountElse;
    }

    public WsExchangeActiviti(){
        super();
    }

}