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

/**
 * 流程变量fromKey的一个标准
 */
@Data
public class SysActivitiFormKey {

    //开始事件处理类
    //任务事件处理类
    //结束事件处理类

    //节点选择处理类
    private String  nextNodeClass;
    //处理人选择处理类
    private String  assigneeClass;
    //抄送人选择处理类
    private String  ccClass;

    //流程类型  1，普通节点，2并行节点  暂时只有两种以后有需求再加
    private String activitiType="1";

    //处理人选择方式  1该节点确认   2系统分配   3父节点确定
    private String isAutoDispose="1";

    //当前处理人学院
    private String disposeCollege;

    //当前处理人部门
    private String disposeDept;

    //当前处理人角色
    private String disposeRole;

    //当前处理人单位
    private String disposeUnit;

    //当前处理人查询备用字段
    private String disposeBy1;

    //抄送人学院
    private String ccCollege;

    //抄送人部门
    private String ccDept;

    //抄送人角色
    private String ccRole;

    //抄送人单位
    private String ccUnit;

    //抄送人查询备用字段2
    private String ccBy1;

    //抄送人用户名ID
    private String ccUserId;

    //抄送人用户名
    private String ccUserName;

}