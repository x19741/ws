package spring.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.SysMenu;
import spring.entity.SysMenuRole;
import spring.exception.CustomException;
import spring.service.SysMenuService;
import spring.service.impl.SysMenuServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.UUID;

@RestController
@Validated
@Api(description = "菜单管理",tags = {"config-MenuController"})
@RequestMapping("/menu")
public class MenuController {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SysMenuService sysMenuServiceImpl;

    @LogOperation(code = "2",name = "查询菜单",description = "查询菜单")
    @ApiOperation(value = "查询菜单（登录用户查询）")
    @PostMapping("/get")
    @ResponseBody
    public ReType select() throws Exception {
        return sysMenuServiceImpl.show(new SysMenu(),0,999);
    }

    @LogOperation(code = "2",name = "管理员查询菜单",description = "查询菜单")
    @ApiOperation(value = "查询菜单（登录用户查询）")
    @PostMapping("/getadmin")
    @ResponseBody
    public ReType selectAdmin() throws Exception {
        return sysMenuServiceImpl.show2(new SysMenu(),0,999);
    }

    @ApiOperation(value = "查询单个菜单")
    @PostMapping("/getById")
    @ResponseBody
    public String getById(@ApiParam(value = "id") @RequestParam String id){
        return JsonUtil.sucess("成功",sysMenuServiceImpl.selectByPrimaryKey(id));

    }
    @ApiOperation(value = "添加菜单")
    @LogOperation(code = "2",name = "添加菜单",description = "添加菜单")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "菜单")  @RequestBody SysMenu item) throws Exception {
        try{
            String uuid = IdWorker.getIdWorkerNext().toString();
            item.setId(uuid);
            if(sysMenuServiceImpl.insert(item)>0)
                return  JsonUtil.sucess("成功",sysMenuServiceImpl.selectByPrimaryKey(uuid));
            return  JsonUtil.error("失败");
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }


    @ApiOperation(value = "修改菜单")
    @LogOperation(code = "2",name = "修改菜单",description = "修改菜单")
    @PostMapping("/update")
    @ResponseBody
    public String update(@ApiParam(value = "菜单")  @RequestBody SysMenu item) throws Exception {
        try{
            if(sysMenuServiceImpl.updateByPrimaryKeySelective(item)<=0){
                throw  new CustomException("修改失败");
            }
            return  JsonUtil.sucess("成功",sysMenuServiceImpl.selectByPrimaryKey(item.getId()));
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除菜单")
    @LogOperation(code = "2",name = "删除菜单",description = "删除菜单")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@ApiParam(value = "菜单")  @RequestParam String ids ) throws Exception {
        //删除权限

        return JsonUtil.sucess("删除了"+sysMenuServiceImpl.deleteByIds(ids)+"个数据。",null);
    }



    //查看角色列类型权限树
    //@LogOperation(code = "2",name = "查询项目Excel",description = "查询项目Excel")
    @ApiOperation(value = "查询角色菜单权限树")
    @PostMapping("/getroleaciontree")
    @ResponseBody
    public String getRoleAcionTree(@ApiParam(value = "角色id") @RequestParam String id) throws Exception {
        return sysMenuServiceImpl.getRoleAcionTree(id,1,999);
    }
    //修改角色列类型权限树
    @ApiOperation(value = "修改角色菜单权限树")
    @PostMapping("/updateroleaciontree")
    @ResponseBody
    public String  updateRoleAcionTree(@ApiParam(value = "角色列类型权限树") @RequestBody SysMenuRole sysMenuRole  ) throws Exception {
        try{
            if(sysMenuRole.getMrlist()==null||sysMenuRole.getRoleId()==null){
                throw new CustomException("数据格式错误.");
            }
            sysMenuServiceImpl.updateRoleAcionTree(sysMenuRole.getMrlist(),sysMenuRole.getRoleId());
            return  JsonUtil.sucess("成功",null);
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }
}
