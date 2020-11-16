package spring.controller;

import spring.config.logs.LogOperation;
import spring.entity.SysDic;
import spring.entity.SysDicType;
import spring.entity.SysUser;
import spring.exception.CustomException;
import spring.service.SysDicService;
import spring.service.SysDicTypeService;
import spring.service.impl.SysDicTypeServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
@Validated
@Api(description = "字典/下拉框管理",tags = {"congif-DicController"})
public class DicController {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SysDicService sysDicServiceImpl;

    @Autowired
    SysDicTypeService sysDicTypeServiceImpl;


    //用户模糊分页查询   当前页数 每页多少个  一共多少个
    @ApiOperation(value = "查询字典表(按类型)")
    @PostMapping("/selectdic")
    @ResponseBody
    public String selectdic(@RequestParam @NotNull(message = "字典类型不能为空") String dictype /*,String dicvalue ,String dicname*/) throws Exception {
            return JsonUtil.sucess("成功",sysDicServiceImpl.select(dictype));
    }

    @ApiOperation(value = "/查询单个字典表(按照id)")
    @PostMapping("/selectdicbyid")
    @ResponseBody
    public String selectdicById(@RequestParam(required = false) String id){
        return JsonUtil.sucess("成功",sysDicServiceImpl.selectByPrimaryKey(id));

    }

    //@LogOperation(code = "2",name = "查询字典表",description = "查询字典表")
    @ApiOperation(value = "查询字典表")
    @PostMapping("/admin/selectdicpage")
    @ResponseBody
    public ReType selectdicpage(@RequestBody @ApiParam(value="字典类:dicType:字典类型") SysDic dic
            ,@ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            ,@ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "100") Integer pageSize  ) throws Exception {
            return sysDicServiceImpl.show(dic,pageNumber,pageSize);
    }


    @ApiOperation(value = "删除字典表")
    @LogOperation(code = "2",name = "管理员删除账户日志",description = "管理员删除账户")
    @PostMapping("/admin/deletedic")
    @ResponseBody
    public String deletedic( @RequestParam String dics) throws Exception {
            return JsonUtil.sucess("删除了"+sysDicServiceImpl.deleteByIds(dics)+"个字典。",null);
    }

    @ApiOperation(value = "修改/添加字典表")
    @LogOperation(code = "2",name = "修改/添加字典表日志",description = "修改/添加字典表")
    @PostMapping("/admin/updatedic")
    @ResponseBody
    public String updatedic(@Validated @RequestBody SysDic dic) throws Exception {
        try {
            if(dic.getDicName()==null)
                throw new Exception("dicName不能为空");
            SysUser user=(SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
            if(user!=null)
                dic.setDicUser(user.getId());
            dic.setUpdateDate(new Date());
            if(dic.getDicId()==null){
                if(dic.getDicValue()==null)
                    dic.setDicValue(IdWorker.getIdWorkerNext().toString());
                int i =sysDicServiceImpl.add(dic);
                if(i<=0){
                    throw new Exception("失败");
                }
            }else {
                int i =sysDicServiceImpl.updateByPrimaryKeySelective(dic);
                if(i<=0){
                    throw new Exception("失败");
                }
            }
            return  JsonUtil.sucess("成功",null);
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询单个字典")
    @PostMapping("/selectbyid")
    @ResponseBody
    public String selectBbyId(@RequestParam(required = false) String id){
        return JsonUtil.sucess("成功",sysDicServiceImpl.selectByPrimaryKey(id));

    }

    @ApiOperation(value = "查询类型包")
    @PostMapping("/selectdictype")
    @ResponseBody
    public String selectDicType( /*,String dicvalue ,String dicname*/) throws Exception {
        return JsonUtil.sucess("成功",sysDicServiceImpl.selectType());
    }

    @ApiOperation(value = "查询字典包类型-树形")
    @PostMapping("/admin/selectdictypepage")
    @ResponseBody
    public ReType selectDicTypePage(@RequestBody @ApiParam(value="字典类:dicType:字典类型") SysDicType dicType
            ,@ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            ,@ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "9999") Integer pageSize  ) throws Exception {
        return sysDicTypeServiceImpl.show3(dicType,pageNumber,pageSize);
    }

    @ApiOperation(value = "添加字典类型")
    @PostMapping("/admin/insertdictype")
    @ResponseBody
    public String insertDicType(@ApiParam(value = "字典类型") @RequestBody() SysDicType dicType ) throws Exception {
        try{
            dicType.setTypeid(IdWorker.getIdWorkerNext().toString());
            return JsonUtil.sucess("添加了"+sysDicTypeServiceImpl.insert(dicType)+"条数据",null);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }

    @ApiOperation(value = "查询单个字段类型")
    @PostMapping("/selectdictypbyid")
    @ResponseBody
    public String selectDicTypById(@RequestParam(required = false) String id){
        return JsonUtil.sucess("成功",sysDicTypeServiceImpl.selectByPrimaryKey(id));

    }

    @ApiOperation(value = "修改字典类型")
    @PostMapping("/admin/updatedictype")
    @ResponseBody
    public String updateDicType(@ApiParam(value = "字典类型") @RequestBody() SysDicType dicType ) throws Exception {
        try{
            return JsonUtil.sucess("修改了"+sysDicTypeServiceImpl.updateByPrimaryKeySelective(dicType)+"条数据",null);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }

    @ApiOperation(value = "删除字典类型")
    @PostMapping("/admin/deletedictypepage")
    @ResponseBody
    public String deleteDicTypePage(@ApiParam(value = "ids") @RequestParam String ids ) throws Exception {
        try{
            return JsonUtil.sucess("删除了"+sysDicTypeServiceImpl.deleteByIds(ids)+"条数据",null);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }





}
