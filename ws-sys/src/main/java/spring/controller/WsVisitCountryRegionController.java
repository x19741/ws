package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsVisitCountryRegion;
import spring.exception.CustomException;
import spring.service.WsVisitCountryRegionService;
import spring.service.impl.WsVisitCountryRegionServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "因公临时出国申报表-地区", tags = {"sys-WsVisitCountryRegionController"})
@RequestMapping("/visitcountryregion")
public class WsVisitCountryRegionController {

    @Autowired
    WsVisitCountryRegionService wsVisitCountryRegionServiceImpl;

    @LogOperation(code = "2", name = "查询因公临时出国申报表-地区", description = "查询因公临时出国申报表-地区")
    @ApiOperation(value = "查询因公临时出国申报表-地区")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "因公临时出国申报表-地区") @RequestBody WsVisitCountryRegion item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsVisitCountryRegionServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsVisitCountryRegionServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增因公临时出国申报表-地区", description = "新增因公临时出国申报表-地区")
    @ApiOperation(value = "新增因公临时出国申报表-地区")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "因公临时出国申报表-地区") @RequestBody WsVisitCountryRegion item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsVisitCountryRegionServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsVisitCountryRegionServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增因公临时出国申报表-地区", description = "批量新增因公临时出国申报表-地区")
    @ApiOperation(value = "批量新增因公临时出国申报表-地区")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "因公临时出国申报表-地区") @RequestBody List<WsVisitCountryRegion> items) throws Exception {
        try {
            List<WsVisitCountryRegion> list = new ArrayList<>();
            for (WsVisitCountryRegion item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsVisitCountryRegionServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改因公临时出国申报表-地区", description = "修改因公临时出国申报表-地区")
    @ApiOperation(value = "修改因公临时出国申报表-地区")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "因公临时出国申报表-地区") @RequestBody WsVisitCountryRegion item) throws Exception {
        try {
            if (wsVisitCountryRegionServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsVisitCountryRegionServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }



    @LogOperation(code = "2",name = "查询form因公临时出国申报表-地区",description = "查询form因公临时出国申报表-地区")
    @ApiOperation(value = "查询form因公临时出国申报表-地区")
    @PostMapping("/getform")
    @ResponseBody
    public ReType getForm(@ApiParam(value = "因公临时出国申报表-地区") @RequestBody WsVisitCountryRegion item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsVisitCountryRegionServiceImpl.showForm(item, pageNumber, pageSize);
    }


    // 删除
    @LogOperation(code = "2",name = "查询因公临时出国申报表-地区",description = "查询因公临时出国申报表-地区")
    @ApiOperation(value = "删除因公临时出国申报表-地区")
    @PostMapping("/del")
    @ResponseBody
    public String dateleFodderPage( String ids ) throws Exception {
        try{
            return JsonUtil.sucess("成功修改了"+wsVisitCountryRegionServiceImpl.deleteByIds(ids)+"条数据",null);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }


}
