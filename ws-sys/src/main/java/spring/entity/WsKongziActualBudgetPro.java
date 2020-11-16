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
@Table(name="WS_KONGZI_ACTUAL_BUDGET_PRO")
public class WsKongziActualBudgetPro {
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

    @Column(name = "MAINFORMID")
    @ApiModelProperty(value="父ID")
    private String mainformid;

    @Column(name = "NEW_PRO")
    @ApiModelProperty(value="新增项目")
    private String newPro;

    @Column(name = "NEW_PRO_AMOUNT")
    @ApiModelProperty(value="新增项目实际支出金额")
    private BigDecimal newProAmount;

    @Column(name = "NEW_PRO_REASON")
    @ApiModelProperty(value="新增项目原因")
    private String newProReason;

    public WsKongziActualBudgetPro(String id, String createBy, Date createDate, String updateBy, Date updateDate, String mainformid, String newPro, BigDecimal newProAmount, String newProReason) {
        this.id = id;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.mainformid = mainformid;
        this.newPro = newPro;
        this.newProAmount = newProAmount;
        this.newProReason = newProReason;
    }

    public WsKongziActualBudgetPro() {
        super();
    }
}