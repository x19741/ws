package spring.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.SysDepartment;
import spring.entity.SysRole;
import spring.exception.CustomException;
import spring.service.SysDepartmentService;
import spring.service.impl.SysDepartmentServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.UUID;

@RestController
@Api(description = "部门管理",tags = {"config-DeptController"})
@RequestMapping("/dept")
public class DeptController {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SysDepartmentService sysDepartmentServiceImpl;

    @LogOperation(code = "2",name = "查询部门",description = "查询部门")
    @ApiOperation(value = "查询部门")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "部门") @RequestBody SysDepartment item
            , @ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize  ) throws Exception {
        return sysDepartmentServiceImpl.show(item,pageNumber,pageSize);
    }

    @LogOperation(code = "2",name = "查询部门表单",description = "查询部门表单")
    @ApiOperation(value = "查询部门表单")
    @PostMapping("/getform")
    @ResponseBody
    public ReType selectForm(@ApiParam(value = "部门") @RequestBody SysDepartment item
            ,@ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize  ) throws Exception {
        return sysDepartmentServiceImpl.showForm(item,pageNumber,pageSize);
    }

    @ApiOperation(value = "查询单个部门")
    @PostMapping("/getById")
    @ResponseBody
    public String getById(@ApiParam(value = "id") @RequestParam String id){
        return JsonUtil.sucess("成功",sysDepartmentServiceImpl.selectByPrimaryKey(id));

    }
    @ApiOperation(value = "添加部门")
    @LogOperation(code = "2",name = "添加部门",description = "添加部门")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "部门")  @RequestBody SysDepartment item) throws Exception {
        try{
            String uuid = IdWorker.getIdWorkerNext().toString();
            item.setId(uuid);
            if(sysDepartmentServiceImpl.insert(item)>0)
                return  JsonUtil.sucess("成功",sysDepartmentServiceImpl.selectByPrimaryKey(uuid));
            return  JsonUtil.error("失败");
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }


    @ApiOperation(value = "修改部门")
    @LogOperation(code = "2",name = "修改部门",description = "修改部门")
    @PostMapping("/update")
    @ResponseBody
    public String update(@ApiParam(value = "部门")  @RequestBody SysDepartment item) throws Exception {
        try{
            if(sysDepartmentServiceImpl.updateByPrimaryKeySelective(item)<=0){
                throw  new CustomException("修改失败");
            }
            return  JsonUtil.sucess("成功",sysDepartmentServiceImpl.selectByPrimaryKey(item.getId()));
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除部门")
    @LogOperation(code = "2",name = "删除部门",description = "删除部门")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@ApiParam(value = "部门id")  @RequestParam String ids ) throws Exception {
        //删除权限
        return JsonUtil.sucess("删除了"+sysDepartmentServiceImpl.deleteByIds(ids)+"个数据。",null);
    }
}
