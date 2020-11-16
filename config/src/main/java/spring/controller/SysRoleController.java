package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.SysRole;
import spring.exception.CustomException;
import spring.service.SysRoleService;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.UUID;

@RestController
@Validated
@Api(description = "角色管理", tags = {"config-SysRoleController"})
@RequestMapping("/role")
public class SysRoleController {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SysRoleService sysRoleServiceImpl;

    @LogOperation(code = "2", name = "查询角色", description = "查询角色")
    @ApiOperation(value = "查询角色")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "角色") @RequestBody SysRole item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return sysRoleServiceImpl.show(item, pageNumber, pageSize);
    }

    @LogOperation(code = "2", name = "查询角色表单", description = "查询角色表单")
    @ApiOperation(value = "查询角色表单")
    @PostMapping("/getform")
    @ResponseBody
    public ReType selectForm(@ApiParam(value = "角色") @RequestBody SysRole item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return sysRoleServiceImpl.showForm(item, pageNumber, pageSize);
    }

    @ApiOperation(value = "查询单个角色")
    @PostMapping("/getById")
    @ResponseBody
    public String getById(@ApiParam(value = "id") @RequestParam String id) {
        return JsonUtil.sucess("成功", sysRoleServiceImpl.selectByPrimaryKey(id));

    }

    @ApiOperation(value = "添加角色")
    @LogOperation(code = "2", name = "添加角色", description = "添加角色")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "角色") @RequestBody SysRole item) throws Exception {
        try {
            String uuid = IdWorker.getIdWorkerNext().toString();
            item.setId(uuid);
            if (sysRoleServiceImpl.insert(item) > 0)
                return JsonUtil.sucess("成功", sysRoleServiceImpl.selectByPrimaryKey(uuid));
            return JsonUtil.error("失败");
        } catch (CustomException e) {
            return JsonUtil.error(e.getMessage());
        }
    }

    @ApiOperation(value = "修改角色")
    @LogOperation(code = "2", name = "修改角色", description = "修改角色")
    @PostMapping("/update")
    @ResponseBody
    public String update(@ApiParam(value = "角色") @RequestBody SysRole item) throws Exception {
        try {
            if (sysRoleServiceImpl.updateByPrimaryKeySelective(item) <= 0) {
                throw new CustomException("修改失败");
            }
            return JsonUtil.sucess("成功", sysRoleServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException e) {
            return JsonUtil.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除角色")
    @LogOperation(code = "2", name = "删除角色", description = "删除角色")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@ApiParam(value = "角色") @RequestParam String ids) throws Exception {
        //删除权限
        return JsonUtil.sucess("删除了" + sysRoleServiceImpl.deleteByIds(ids) + "个数据。", null);
    }


}
