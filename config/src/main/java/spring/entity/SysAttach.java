package spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "SYS_ATTACH")
public class SysAttach {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "ATTACH_NAME")
    private String attachName;

    @Column(name = "ATTACH_FILE")
    private String attachFile;

    @Column(name = "ATTACH_FILEPATH")
    private String attachfFlepath;

    @Column(name = "ATTACH_TYPE")
    private String attachType;

    @Column(name = "ATTACH_SUFFIX")
    private String attach_suffix;

    @Column(name = "UPDATE_BY")
    private String updateBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "FILE_SIZE")
    private Integer fileSize;






}