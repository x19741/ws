package spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Table(name="SYS_DIC")
public class SysDic {
    @Id
    @Column(name = "DIC_ID")
    @ApiModelProperty(value="id")
    private String dicId;

    @Column(name = "DIC_NAME")
    @ApiModelProperty(value="字典名称")
    private String dicName;

    @Column(name = "DIC_VALUE")
    @ApiModelProperty(value="字典值")
    private String dicValue;

    @Column(name = "DIC_TYPE")
    @ApiModelProperty(value="所属类别")
    @NotNull(message = "字典类型不能为空")
    private String dicType;

    @Column(name = "DIC_USER")
    @ApiModelProperty(value="添加用户")
    private String dicUser;

    @Column(name = "DIC_ORDER")
    @ApiModelProperty(value="排序")
    private Integer dicOrder;

    @Column(name = "PARENTID")
    @ApiModelProperty(value="parentid")
    private String parentid;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "UPDATE_DATE")
    @ApiModelProperty(value="更新时间")
    private Date updateDate;

}