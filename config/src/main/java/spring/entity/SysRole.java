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
import java.util.List;

@Data
@Table(name = "SYS_ROLE")
public class SysRole {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "ROLE_NAME")
    @ApiModelProperty(value="角色姓名")
    private String roleName;

    @Column(name = "ROLE_CODE")
    @ApiModelProperty(value="角色代码")
    private String roleCode;

    @Column(name = "REMARK")
    @ApiModelProperty(value="角色描述")
    private String remark;

    @Column(name = "CREATE_BY")
    @ApiModelProperty(value="创建人")
    private String createBy;

    @Column(name = "CREATE_DATE")
    @ApiModelProperty(value="创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @Column(name = "UPDATE_BY")
    @ApiModelProperty(value="更新人")
    private String updateBy;

    @Column(name = "UPDATE_DATE")
    @ApiModelProperty(value="更新时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    @Transient
    @ApiModelProperty(value="用户id")
    private String userId;

    @Transient
    private List<SysAction> sysActions;


}