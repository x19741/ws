package spring.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Table(name = "SYS_DIC_TYPE")
public class SysDicType {
    @Id
    @Column(name = "TYPEID")
    private String typeid;

    @Column(name = "TYPENAME")
    @ApiModelProperty("类型名称")
    private String typename;

    @Column(name = "PARENTID")
    @ApiModelProperty("父id")
    private String parentid;

    @Transient
    List<SysDicType> dicTypeList;
}