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
@Table(name="SYS_POST")
public class SysPost {
    @Id
    @Column(name = "ID")
    private String id;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "CREATE_DATE")
    @ApiModelProperty(value="创建日期")
    private Date createDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "UPDATE_DATE")
    @ApiModelProperty(value="更新日期")
    private Date updateDate;

    @Column(name = "UPDATE_BY")
    @ApiModelProperty(value="更新人")
    private String updateBy;

    @Column(name = "CREATE_BY")
    @ApiModelProperty(value="创建人")
    private String createBy;

    @Column(name = "STATUS")
    @ApiModelProperty(value="状态")
    private String status;

    @Column(name = "ORDER_ID")
    @ApiModelProperty(value="序号")
    private String orderId;

    @Column(name = "NAME")
    @ApiModelProperty(value="职务名称")
    private String name;

    public SysPost(String id, Date createDate, Date updateDate, String updateBy, String createBy, String status, String orderId, String name) {
        this.id = id;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
        this.createBy = createBy;
        this.status = status;
        this.orderId = orderId;
        this.name = name;
    }

    public SysPost() {
        super();
    }
}