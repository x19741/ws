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
@Table(name="SYS_SERIAL_NUMBER")
public class SysSerialNumber {

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

    @Column(name = "TYPE")
    @ApiModelProperty(value="流水号类型")
    private String type;

    @Column(name = "NUM")
    @ApiModelProperty(value="流水号编号")
    private String num;

    @Column(name = "SERIAL_NUMBER")
    @ApiModelProperty(value="流水号")
    private String serialNumber;

    public SysSerialNumber(String id, String createBy, Date createDate, String updateBy, Date updateDate, String type, String num, String serialNumber) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.type = type;
        this.num = num;
        this.serialNumber = serialNumber;
    }

    public SysSerialNumber() {
        super();
    }
}