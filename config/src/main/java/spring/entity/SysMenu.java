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
@Table(name="SYS_MENU")
public class SysMenu {
    @Id
    @ApiModelProperty(value="ID")
    private String id;

    @Column(name = "CREATE_BY")
    @ApiModelProperty(value="创建人(也用于查询用户拥有的菜单)")
    private String createBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "CREATE_DATE")
    @ApiModelProperty(value="创建日期")
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

    @Column(name = "MENU_NAME")
    @ApiModelProperty(value="菜单名称")
    private String menuName;

    @Column(name = "MENU_URL")
    @ApiModelProperty(value="链接")
    private String menuUrl;

    @Column(name = "ORDER_NUM")
    @ApiModelProperty(value="排序")
    private String orderNum;

    @Column(name = "PARENT_ID")
    @ApiModelProperty(value="父id")
    private String parentId;

    @Column(name = "MENU_ICON")
    @ApiModelProperty(value="图标")
    private String menuIcon;

    @Column(name = "IS_END_LEVEL")
    @ApiModelProperty(value="是否末级数：0是，1否")
    private String isEndLevel;

    @Transient
    List<SysMenu > mlist;
 }