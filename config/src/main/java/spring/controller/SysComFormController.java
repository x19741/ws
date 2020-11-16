package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.SysComForm;
import spring.entity.SysComFormField;
import spring.exception.CustomException;
import spring.service.SysComFormFieldService;
import spring.service.SysComFormService;
import spring.service.impl.SysComFormFieldServiceImpl;
import spring.service.impl.SysComFormServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.UUID;


@RestController
@Validated
@Api(description = "表单配置管理",tags = {"config-sysComFormController"})
@RequestMapping("/com/form")
public class SysComFormController {

    @Autowired
    SysComFormFieldService sysComFormFieldServiceImpl;

    @Autowired
    SysComFormService      sysComFormServiceImpl;

    // -------------- 表单配置-------------
    @LogOperation(code = "2",name = "查询表单配置",description = "查询表单配置")
    @ApiOperation(value = "查询表单配置")
    @PostMapping("/getList")
    @ResponseBody
    public ReType selectFodderPage(@ApiParam(value = "表单配置") @RequestBody SysComForm comForm
            ,@ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize  ) throws Exception {
        return sysComFormServiceImpl.show(comForm,pageNumber,pageSize);
    }

    // 查看单个素材类型
    @ApiOperation(value = "查询表单配置")
    @PostMapping("/getById")
    @ResponseBody
    public String getById(@ApiParam(value = "id") @RequestParam String id){
        return JsonUtil.sucess("成功",sysComFormServiceImpl.selectByPrimaryKey(id));

    }
    //素材添加
    @ApiOperation(value = "添加表单配置")
    @LogOperation(code = "2",name = "添加表单配置",description = "添加表单配置")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "表单配置")  @RequestBody SysComForm comForm) throws Exception {
        try{
            String uuid = IdWorker.getIdWorkerNext().toString();
            comForm.setId(uuid);
            if(sysComFormServiceImpl.insert(comForm)>0)
                return  JsonUtil.sucess("成功",sysComFormServiceImpl.selectByPrimaryKey(uuid));
            return  JsonUtil.error("失败");
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }


    @ApiOperation(value = "修改表单配置")
    @LogOperation(code = "2",name = "修改表单配置",description = "修改表单配置")
    @PostMapping("/update")
    @ResponseBody
    public String update(@ApiParam(value = "表单配置")  @RequestBody SysComForm comForm) throws Exception {
        try{
            if(sysComFormServiceImpl.updateByPrimaryKeySelective(comForm)<=0){
                throw  new CustomException("修改失败");
            }
            return  JsonUtil.sucess("成功",sysComFormServiceImpl.selectByPrimaryKey(comForm.getId()));
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }
    @ApiOperation(value = "删除表单配置")
    @LogOperation(code = "2",name = "删除表单配置",description = "删除表单配置")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@ApiParam(value = "表单配置")  @RequestParam String ids ) throws Exception {
        return JsonUtil.sucess("删除了"+sysComFormServiceImpl.deleteByIds(ids)+"个数据。",null);
    }


    // -------------- 表单配置-菜单----------------------------------
    @LogOperation(code = "2",name = "查询表单配置-菜单",description = "查询表单配置-菜单")
    @ApiOperation(value = "查询表单-菜单")
    @PostMapping("/getfieldlist")
    @ResponseBody
    public ReType selectFodderPage(@ApiParam(value = "表单配置-菜单") @RequestBody SysComFormField comFormFiled
            ,@ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize  ) throws Exception {
        return sysComFormFieldServiceImpl.show(comFormFiled,pageNumber,pageSize);
    }

    // 查看单个素材类型
    @ApiOperation(value = "查询表单配置-菜单")
    @PostMapping("/getfieldbyid")
    @ResponseBody
    public String getfieldbyid(@ApiParam(value = "id") @RequestParam String id){
        return JsonUtil.sucess("成功",sysComFormFieldServiceImpl.selectByPrimaryKey(id));

    }
    //素材添加
    @ApiOperation(value = "添加表单配置-菜单")
    @LogOperation(code = "2",name = "添加表单配置-菜单",description = "添加表单配置-菜单")
    @PostMapping("/addfield")
    @ResponseBody
    public String addField(@ApiParam(value = "表单配置-菜单")  @RequestBody SysComFormField comFormField) throws Exception {
        try{
            String uuid = IdWorker.getIdWorkerNext().toString();
            comFormField.setId(uuid);
            if(sysComFormFieldServiceImpl.insert(comFormField)>0)
                return  JsonUtil.sucess("成功",sysComFormServiceImpl.selectByPrimaryKey(uuid));
            return  JsonUtil.error("失败");
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }


    @ApiOperation(value = "修改表单配置-菜单")
    @LogOperation(code = "2",name = "修改表单配置-菜单",description = "修改表单配置-菜单")
    @PostMapping("/updatefield")
    @ResponseBody
    public String update(@ApiParam(value = "表单配置-菜单")  @RequestBody SysComFormField comFormField) throws Exception {
        try{
            if(sysComFormFieldServiceImpl.updateByPrimaryKeySelective(comFormField)<=0){
                throw  new CustomException("修改失败");
            }
            return  JsonUtil.sucess("成功",sysComFormFieldServiceImpl.selectByPrimaryKey(comFormField.getId()));
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }
    @ApiOperation(value = "删除表单配置-菜单")
    @LogOperation(code = "2",name = "删除表单配置-菜单",description = "删除表单配置-菜单")
    @PostMapping("/deletefield")
    @ResponseBody
    public String deleteField(@ApiParam(value = "表单配置")  @RequestParam String ids ) throws Exception {
        return JsonUtil.sucess("删除了"+sysComFormFieldServiceImpl.deleteByIds(ids)+"个数据。",null);
    }



}
