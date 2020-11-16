package spring.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.entity.SysDic;
import spring.entity.SysUser;
import spring.exception.CustomException;
import spring.service.PublicService;
import spring.service.SysDicService;
import spring.service.SysUserService;
import spring.util.IdWorker;
import spring.util.JsonUtil;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "通用方法接口",tags = {"web-publicController"})
@RequestMapping("/base")
public class PublicController {

    @Autowired
    PublicService publicServiceImpl;

    //下拉框接口
    @Autowired
    SysDicService sysDicServiceImpl;

    @Autowired
    SysUserService sysUserServiceImpl;

    //@LogOperation(code = "2",name = "查询项目",description = "查询项目")
    @ApiOperation(value = "查询")
    @PostMapping("/get")
    @ResponseBody
    public String selectFodderPage(@ApiParam(value = "表单信息") @RequestParam(value="comformInfo",defaultValue = "{'formId':'cz_pro'}") String comformInfo
            , @ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize  ) throws Exception {
        try{
            return JSON.toJSONString( publicServiceImpl.selectComfromTable(comformInfo,pageNumber,pageSize),SerializerFeature.WriteMapNullValue);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }



    //@LogOperation(code = "2",name = "查询项目",description = "查询项目")
    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "表单信息") @RequestParam(value="comformInfo",defaultValue = "{'formId':'cz_pro','id':''}") String comformInfo) throws Exception {
        try{
            return JSON.toJSONString( publicServiceImpl.selectByPrimaryKey(comformInfo),SerializerFeature.WriteMapNullValue);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }


    //@LogOperation(code = "2",name = "查询项目",description = "查询项目")
    @ApiOperation(value = "查询表单")
    @PostMapping("/getform")
    @ResponseBody
    public String selectFodderPageForm(@ApiParam(value = "表单信息") @RequestParam(value="comformInfo",defaultValue = "{'formId':'cz_pro'}") String comformInfo
            , @ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize  ) throws Exception {
        try{
            return JSON.toJSONString( publicServiceImpl.selectComfromTableForm(comformInfo,pageNumber,pageSize),SerializerFeature.WriteMapNullValue);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }
    // 添加
    @ApiOperation(value = "添加")
    @PostMapping("/add")
    @ResponseBody
    public String insertFodderPage(@ApiParam(value = "表单信息") @RequestParam(value="comformInfo",defaultValue = "{\"formId\":\"WsExchangeActiviti\",\"date\":{\"createBy\":\"1\"},\"mainformdates\":[{\"formId\":\"WsExchangeActivitiReturn\",\"dates\":[{\"date\":{\"createBy\":\"1\"},\"mainformdates\":[]}]}]}") String comformInfo ) throws Exception {
        try{
            String uuid= IdWorker.getIdWorkerNext().toString();
            return JsonUtil.sucess("成功添加了"+publicServiceImpl.insertComfromTable(comformInfo,null,uuid)+"条数据",uuid);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }

    // 修改
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    @ResponseBody
    public String updateFodderPage(@ApiParam(value = "表单信息") @RequestParam(value="comformInfo",defaultValue = "{'formId':'cz_pro'}") String comformInfo ) throws Exception {
        try{
            return JsonUtil.sucess("成功修改了"+publicServiceImpl.updateComfromTable(comformInfo,null)+"条数据",null);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }

    // 删除
    @ApiOperation(value = "删除")
    @PostMapping("/del")
    @ResponseBody
    public String dateleFodderPage(@ApiParam(value = "表单信息") @RequestParam(value="comformInfo",defaultValue = "{'formId':'cz_pro','date':{}}") String comformInfo ) throws Exception {
        try{
            return JsonUtil.sucess("成功修改了"+publicServiceImpl.deleteComfromTable(comformInfo)+"条数据",null);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }

    //使用自定义mapper 查询
    //@LogOperation(code = "2",name = "查询项目",description = "查询项目")
    @ApiOperation(value = "查询下拉字段")
    @PostMapping("/getformdic")
    @ResponseBody
    public String getFformDic(@ApiParam(value = "表单信息") @RequestParam(value="comformInfo",defaultValue = "formId|方法名|参数A~参数B~参数C") String comformInfo
            , @ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "9999") Integer pageSize  ) throws Exception {
        try{
            return JsonUtil.sucess("成功",publicServiceImpl.getFformDic(comformInfo,pageNumber,pageSize));
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }





    //用户模糊分页查询   当前页数 每页多少个  一共多少个
    @ApiOperation(value = "查询下拉框")
    @PostMapping("/select")
    @ResponseBody
    public String selectdic(@RequestParam @NotNull(message = "下拉框类型不能为空") String dictype /*,String dicvalue ,String dicname*/) throws Exception {
        String[] strs=dictype.split("|");

        if(dictype!=null&&dictype.indexOf("用户")>-1){
            List<SysUser> ulist=sysUserServiceImpl.selectAll();
            List<SysDic> dlist=new ArrayList<>();
            for (SysUser item:  ulist) {
                SysDic u=new SysDic();
                u.setDicValue(item.getAccountname());
                u.setDicName(item.getAccountname());
                dlist.add(u);
            }
            return JsonUtil.sucess("成功",dlist);
        }else{
            return JsonUtil.sucess("成功",sysDicServiceImpl.select(dictype));

        }



    }

}





