package spring.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name ="SYS_COM_FORM")
public class SysComForm {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    @ApiModelProperty(value="表单名称")
    private String name;

    @Column(name = "ENNAME")
    private String enname;

    @Column(name = "CLASSNAME")
    @ApiModelProperty(value="实体类名称")
    private String classname;

    @Column(name = "TABLENAME")
    @ApiModelProperty(value="数据库名称")
    private String tablename;

    @Column(name = "CREATE_BY")
    @ApiModelProperty(value="创建人")
    private String createBy;

    @Column(name = "CREATE_DATE")
    @ApiModelProperty(value="创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @Column(name = "UPDATE_BY")
    @ApiModelProperty(value="更新人")
    private String updateBy;


    @Column(name = "UPDATE_DATE")
    @ApiModelProperty(value="更新时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;





    @Column(name = "TABLETYPE")
    private String tabletype;

    @Column(name = "EXTCLASS")
    private String extclass;

    @Column(name = "UDATE")
    private String udate;

    @Column(name = "UNAME")
    private String uname;

    @Column(name = "SELECTTYPE")
    private String selecttype;

    @Column(name = "PROPERTY")
    private String property;

    @Column(name = "PARENTFORMID")
    @ApiModelProperty(value="父ID")
    private String parentformid;

    @Column(name = "FORM_TYPE")
    private String formType;

    @Column(name = "REFERFORM")
    private String referform;

    @Column(name = "HINTSHOWTYPE")
    private String hintshowtype;

    @Column(name = "CANCELORDER")
    private String cancelorder;

    @Column(name = "GROUPING")
    private String grouping;

    @Column(name = "DATAMODEL")
    private String datamodel;

    @Column(name = "LAST_VERSION")
    private BigDecimal lastVersion;

    @Column(name = "TREEGRID")
    private String treegrid;

    @Column(name = "DATA_SRC")
    private String dataSrc;

    @Column(name = "TREE_EXPAND")
    private String treeExpand;

    @Column(name = "EDITGRID")
    private String editgrid;

    @Column(name = "GRIDSAVECLASS")
    private String gridsaveclass;

    @Column(name = "CANCELPAGE")
    private String cancelpage;

    @Column(name = "SYNC_TREE")
    private String syncTree;

    @Column(name = "QRY_CODE")
    private String qryCode;

    @Column(name = "MONEY_UNIT")
    private String moneyUnit;

    @Column(name = "PAGE_SIZE")
    private Integer pageSize;

    @Column(name = "BAK_TABLE")
    private String bakTable;

    @Column(name = "FORMWIDTH")
    private String formwidth;

    @Column(name = "FORMHEIGHT")
    private String formheight;

    @Column(name = "DATAPOPEDOMTYPE")
    private String datapopedomtype;

    @Column(name = "DATAPOPEDOMCOLUMN")
    private String datapopedomcolumn;

    @Column(name = "ISCURDEPT")
    private String iscurdept;


    @Column(name = "DEFINESTR")
    private String definestr;

    @Column(name = "LISTHTML")
    private String listhtml;

    @Column(name = "LISTSQL")
    private String listsql;

    @Column(name = "TABLE_HEAD")
    private String tableHead;


}