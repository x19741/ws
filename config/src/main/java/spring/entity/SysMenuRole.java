package spring.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Table(name="SYS_MENU_ROLE")
public class SysMenuRole {
    @Id
    @ApiModelProperty(value="ID")
    private String id;

    @Column(name = "ROLE_ID")
    @ApiModelProperty(value="角色id")
    private String roleId;

    @Column(name = "MENU_ID")
    @ApiModelProperty(value="菜单id")
    private String menuId;

    @Transient
    @ApiModelProperty(value="菜单名称")
    private String menuName;

    @Transient
    @ApiModelProperty(value="菜单父id")
    private String parentId;

    @Transient
    @ApiModelProperty(value="是否拥有该权限：0是,1否")
    private String isAction;

    @Transient
    @ApiModelProperty(value="子集")
    List<SysMenuRole> mrlist;





}