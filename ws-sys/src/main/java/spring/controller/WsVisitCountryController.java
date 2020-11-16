package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsVisitCountry;
import spring.exception.CustomException;
import spring.service.WsVisitCountryService;
import spring.service.impl.WsVisitCountryServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "出访科-因公临时出国申报表", tags = {"sys-WsVisitCountryController"})
@RequestMapping("/visitcountry")
public class WsVisitCountryController {

    @Autowired
    WsVisitCountryService wsVisitCountryServiceImpl;

    @LogOperation(code = "2", name = "查询出访科-因公临时出国申报表", description = "查询出访科-因公临时出国申报表")
    @ApiOperation(value = "查询出访科-因公临时出国申报表")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "出访科-因公临时出国申报表") @RequestBody WsVisitCountry item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsVisitCountryServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsVisitCountryServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增出访科-因公临时出国申报表", description = "新增出访科-因公临时出国申报表")
    @ApiOperation(value = "新增出访科-因公临时出国申报表")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "出访科-因公临时出国申报表") @RequestBody WsVisitCountry item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsVisitCountryServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsVisitCountryServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增出访科-因公临时出国申报表", description = "批量新增出访科-因公临时出国申报表")
    @ApiOperation(value = "批量新增出访科-因公临时出国申报表")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "出访科-因公临时出国申报表") @RequestBody List<WsVisitCountry> items) throws Exception {
        try {
            List<WsVisitCountry> list = new ArrayList<>();
            for (WsVisitCountry item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsVisitCountryServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改出访科-因公临时出国申报表", description = "修改出访科-因公临时出国申报表")
    @ApiOperation(value = "修改出访科-因公临时出国申报表")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "出访科-因公临时出国申报表") @RequestBody WsVisitCountry item) throws Exception {
        try {
            if (wsVisitCountryServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsVisitCountryServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }



    @LogOperation(code = "2",name = "查询form出访科-因公临时出国申报表",description = "查询form出访科-因公临时出国申报表")
    @ApiOperation(value = "查询form出访科-因公临时出国申报表")
    @PostMapping("/getform")
    @ResponseBody
    public ReType getForm(@ApiParam(value = "出访科-因公临时出国申报表") @RequestBody WsVisitCountry item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsVisitCountryServiceImpl.showForm(item, pageNumber, pageSize);
    }


    // 删除
    @LogOperation(code = "2",name = "查询出访科-因公临时出国申报表",description = "查询出访科-因公临时出国申报表")
    @ApiOperation(value = "删除出访科-因公临时出国申报表")
    @PostMapping("/del")
    @ResponseBody
    public String dateleFodderPage( String ids ) throws Exception {
        try{
            return JsonUtil.sucess("成功修改了"+wsVisitCountryServiceImpl.deleteByIds(ids)+"条数据",null);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }


}
