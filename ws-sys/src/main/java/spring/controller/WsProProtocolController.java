package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsProMillionPlan;
import spring.entity.WsProProtocol;
import spring.exception.CustomException;
import spring.service.WsProMillionPlanService;
import spring.service.WsProProtocolService;
import spring.service.impl.WsProProtocolServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "国际处项目科-合作协议", tags = {"sys-WsProProtocolController"})
@RequestMapping("/proprotocol")
public class WsProProtocolController {

    @Autowired
    WsProProtocolService wsProProtocolServiceImpl;

    @LogOperation(code = "2", name = "查询合作协议", description = "查询合作协议")
    @ApiOperation(value = "查询合作协议")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "合作协议") @RequestBody WsProProtocol item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsProProtocolServiceImpl.show(item, pageNumber, pageSize);
    }

    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsProProtocolServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增合作协议", description = "新增合作协议")
    @ApiOperation(value = "新增合作协议")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "合作协议") @RequestBody WsProProtocol item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsProProtocolServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsProProtocolServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增合作协议", description = "批量新增合作协议")
    @ApiOperation(value = "批量新增合作协议")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "合作协议") @RequestBody List<WsProProtocol> items) throws Exception {
        try {
            List<WsProProtocol> list = new ArrayList<>();
            for (WsProProtocol item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsProProtocolServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改合作协议", description = "修改合作协议")
    @ApiOperation(value = "修改合作协议")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "合作协议") @RequestBody WsProProtocol item) throws Exception {
        try {
            if (wsProProtocolServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsProProtocolServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }
}
