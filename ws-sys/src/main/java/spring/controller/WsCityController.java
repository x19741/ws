package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsCity;
import spring.entity.WsWzMaintain;
import spring.exception.CustomException;
import spring.service.WsCityService;
import spring.service.WsWzMaintainService;
import spring.service.impl.WsCityServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "城市表", tags = {"sys-WsCityController"})
@RequestMapping("/city")
public class WsCityController {

    @Autowired
    WsCityService wsCityServiceImpl;

    @LogOperation(code = "2", name = "查询城市表", description = "查询城市表")
    @ApiOperation(value = "查询城市表")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "城市表") @RequestBody WsCity item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsCityServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsCityServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增城市表", description = "新增城市表")
    @ApiOperation(value = "新增城市表")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "城市表") @RequestBody WsCity item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsCityServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsCityServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增城市表", description = "批量新增城市表")
    @ApiOperation(value = "批量新增城市表")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "城市表") @RequestBody List<WsCity> items) throws Exception {
        try {
            List<WsCity> list = new ArrayList<>();
            for (WsCity item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsCityServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改城市表", description = "修改城市表")
    @ApiOperation(value = "修改城市表")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "城市表") @RequestBody WsCity item) throws Exception {
        try {
            if (wsCityServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsCityServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }


}
