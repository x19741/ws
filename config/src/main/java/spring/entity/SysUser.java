package spring.entity;



import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Table(name="SYS_USER")//设置数据库中表名字 @Table注解中可以写成“{数据库名}.{架构名}.{表名}”，如：@Table(name="db.dbo.tableName")
public class SysUser {


    /**
     * 登录秘钥
     */
    @Transient
    private String token;

    //@Id    //主键
    //@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    //@Transient  冗余字段
    //@ColumnType
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

    @NotNull(message = "账号不能为空")
    @Column(name = "USERNAME")
    @Length(max = 16,min = 5,message = "请输入5-16长的有效账号")
    @ApiModelProperty(value="请输入5-16长的有效账号",name="username",required = true)
    private String username;

    @Column(name="ACCOUNTNAME")
    @ApiModelProperty(value="用户名（昵称）")
    private String accountname;

    @Column(name = "REAL_NAME")
    @ApiModelProperty(value="真实姓名",name="realName")
    private String realName;

    @Column(name = "PASSWORD")
    @ApiModelProperty(value = "6-16长的有效密码",required = true)
    private String password;

    @Column(name = "PHOTO")
    @ApiModelProperty(value="头像id",name="photo")
    private String photo;

    @Column(name = "AGE")
    @ApiModelProperty(value="年龄",name="age")
    private Integer age;

    @Column(name = "SEX")
    @ApiModelProperty(value="性别")
    private String sex;

    @Column(name = "PHONE")
    @ApiModelProperty(value="手机",name="phone")
    private String phone;


    @Column(name = "MAIL")
    @ApiModelProperty(value="邮箱")
    private String mail;


    @Column(name="BIRTHDAY")
    @ApiModelProperty(value="生日")
    private String birthday;


    @Column(name="REMARK")
    @ApiModelProperty(value="个人描述")
    private String remark;

    @Column(name = "POSITION_ID")
    @ApiModelProperty(value="职务")
    private String positionId;

    @Column(name = "DEPARTMENT_ID")
    @ApiModelProperty(value="部门/系/班")
    private String departmentId;

    @Column(name = "COLLEGE_ID")
    @ApiModelProperty(value="学院id")
    private String collegeId;

    @Column(name="COUNTRY")
    @ApiModelProperty(value="国籍")
    private String country;

    @Column(name="WX_BIND_ONE")
    @ApiModelProperty(value="微信绑定字段1")
    private String  wxBindOne;

    @Column(name="WX_BIND_TWO")
    @ApiModelProperty(value="微信绑定字段2")
    private String  wxBindTwo;

    @Column(name = "DEL_FLAG")
    @ApiModelProperty(value="删除标志0可用1封禁")
    private Integer delFlag=0;

    @Transient
    private List<SysRole> rlist;

}