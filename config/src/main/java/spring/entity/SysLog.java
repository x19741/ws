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
@Table(name = "SYS_LOG")
public class SysLog {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE_NAME")
    @ApiModelProperty(value="日志码1:登录日志 2,系统日志")
    private String codeName;

    @Column(name = "LOG_NAME")
    @ApiModelProperty(value="日志名称")
    private String logName;

    @Column(name = "LOG_TYPE")
    @ApiModelProperty(value="日志类型")
    private String logType;

    @Column(name = "CLASS_NAME")
    @ApiModelProperty(value="日志class")
    private String className;

    @Column(name = "METHOD_NAME")
    @ApiModelProperty(value="日志mothod")
    private String methodName;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="备注")
    private String content;

    @Column(name = "USER_ID")
    @ApiModelProperty(value="用户ID")
    private String userId;

    @Column(name = "IP")
    @ApiModelProperty(value="ip地址")
    private String ip;

    @Column(name = "CREATE_TIME")
    @ApiModelProperty(value="创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Column(name = "USER_NAME")
    @ApiModelProperty(value="用户名")
    private String userName;


}