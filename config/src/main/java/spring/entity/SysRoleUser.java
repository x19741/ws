package spring.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="SYS_ROLE_USER")
public class SysRoleUser {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_ID")
    @ApiModelProperty(value="用户id")
    private String userId;

    @Column(name = "ROLE_ID")
    @ApiModelProperty(value="角色id")
    private String roleId;

}