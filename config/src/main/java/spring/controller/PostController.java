package spring.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.SysDepartment;
import spring.entity.SysPost;
import spring.exception.CustomException;
import spring.service.SysDepartmentService;
import spring.service.SysPostService;
import spring.service.impl.SysPostServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.UUID;

@RestController
@Api(description = "职务管理",tags = {"config-PostController"})
@RequestMapping("/post")
public class PostController {

    @Autowired
    SysPostService sysPostServiceImpl=new SysPostServiceImpl();

    @LogOperation(code = "2",name = "查询职务",description = "查询职务")
    @ApiOperation(value = "查询职务")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "职务") @RequestBody SysPost item
            , @ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize  ) throws Exception {
        return sysPostServiceImpl.show(item,pageNumber,pageSize);
    }

    @LogOperation(code = "2",name = "查询职务表单",description = "查询职务表单")
    @ApiOperation(value = "查询职务表单")
    @PostMapping("/getform")
    @ResponseBody
    public ReType selectForm(@ApiParam(value = "职务") @RequestBody SysPost item
            ,@ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize  ) throws Exception {
        return sysPostServiceImpl.showForm(item,pageNumber,pageSize);
    }

    @ApiOperation(value = "查询单个职务")
    @PostMapping("/getById")
    @ResponseBody
    public String getById(@ApiParam(value = "id") @RequestParam String id){
        return JsonUtil.sucess("成功",sysPostServiceImpl.selectByPrimaryKey(id));

    }
    @ApiOperation(value = "添加职务")
    @LogOperation(code = "2",name = "添加职务",description = "添加职务")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "职务")  @RequestBody SysPost item) throws Exception {
        try{
            String uuid = IdWorker.getIdWorkerNext().toString();
            item.setId(uuid);
            if(sysPostServiceImpl.insert(item)>0)
                return  JsonUtil.sucess("成功",sysPostServiceImpl.selectByPrimaryKey(uuid));
            return  JsonUtil.error("失败");
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }


    @ApiOperation(value = "修改职务")
    @LogOperation(code = "2",name = "修改职务",description = "修改职务")
    @PostMapping("/update")
    @ResponseBody
    public String update(@ApiParam(value = "职务")  @RequestBody SysPost item) throws Exception {
        try{
            if(sysPostServiceImpl.updateByPrimaryKeySelective(item)<=0){
                throw  new CustomException("修改失败");
            }
            return  JsonUtil.sucess("成功",sysPostServiceImpl.selectByPrimaryKey(item.getId()));
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除职务")
    @LogOperation(code = "2",name = "删除职务",description = "删除职务")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@ApiParam(value = "职务id")  @RequestParam String ids ) throws Exception {
        //删除权限
        return JsonUtil.sucess("删除了"+sysPostServiceImpl.deleteByIds(ids)+"个数据。",null);
    }
}
