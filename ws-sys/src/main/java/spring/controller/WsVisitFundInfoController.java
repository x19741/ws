package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsCity;
import spring.entity.WsPresent;
import spring.entity.WsVisitFundInfo;
import spring.exception.CustomException;
import spring.service.WsCityService;
import spring.service.WsVisitFundInfoService;
import spring.service.impl.WsVisitFundInfoServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "各个国家和地区开支标准", tags = {"sys-WsVisitFundInfoController"})
@RequestMapping("/visitfundinfo")
public class WsVisitFundInfoController {

    @Autowired
    WsVisitFundInfoService wsVisitFundInfoServiceImpl=new WsVisitFundInfoServiceImpl();

    @LogOperation(code = "2", name = "查询各个国家和地区开支标准", description = "查询各个国家和地区开支标准")
    @ApiOperation(value = "查询各个国家和地区开支标准")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "各个国家和地区开支标准") @RequestBody WsVisitFundInfo item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsVisitFundInfoServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsVisitFundInfoServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增各个国家和地区开支标准", description = "新增各个国家和地区开支标准")
    @ApiOperation(value = "新增各个国家和地区开支标准")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "各个国家和地区开支标准") @RequestBody WsVisitFundInfo item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsVisitFundInfoServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsVisitFundInfoServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增各个国家和地区开支标准", description = "批量新增各个国家和地区开支标准")
    @ApiOperation(value = "批量新增各个国家和地区开支标准")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "各个国家和地区开支标准") @RequestBody List<WsVisitFundInfo> items) throws Exception {
        try {
            List<WsVisitFundInfo> list = new ArrayList<>();
            for (WsVisitFundInfo item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsVisitFundInfoServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改各个国家和地区开支标准", description = "修改各个国家和地区开支标准")
    @ApiOperation(value = "修改各个国家和地区开支标准")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "各个国家和地区开支标准") @RequestBody WsVisitFundInfo item) throws Exception {
        try {
            if (wsVisitFundInfoServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsVisitFundInfoServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2",name = "查询form各个国家和地区开支标准",description = "查询form各个国家和地区开支标准")
    @ApiOperation(value = "查询form各个国家和地区开支标准")
    @PostMapping("/getform")
    @ResponseBody
    public ReType getForm(@ApiParam(value = "各个国家和地区开支标准") @RequestBody WsVisitFundInfo item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsVisitFundInfoServiceImpl.showForm(item, pageNumber, pageSize);
    }


    // 删除
    @LogOperation(code = "2",name = "查询各个国家和地区开支标准",description = "查询各个国家和地区开支标准")
    @ApiOperation(value = "删除各个国家和地区开支标准")
    @PostMapping("/del")
    @ResponseBody
    public String dateleFodderPage( String ids ) throws Exception {
        try{
            return JsonUtil.sucess("成功修改了"+wsVisitFundInfoServiceImpl.deleteByIds(ids)+"条数据",null);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }


}
