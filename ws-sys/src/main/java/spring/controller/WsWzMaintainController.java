package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsWzMaintain;
import spring.exception.CustomException;
import spring.service.WsWzMaintainService;
import spring.service.impl.WsWzMaintainServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "国际处外专科-后勤维修", tags = {"sys-WsWzMaintainController"})
@RequestMapping("/wzmaintain")
public class WsWzMaintainController {

    @Autowired
    WsWzMaintainService wsWzMaintainServiceImpl;

    @LogOperation(code = "2", name = "查询后勤维修", description = "查询后勤维修")
    @ApiOperation(value = "查询后勤维修")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "后勤维修") @RequestBody WsWzMaintain item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsWzMaintainServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsWzMaintainServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增后勤维修", description = "新增后勤维修")
    @ApiOperation(value = "新增后勤维修")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "后勤维修") @RequestBody WsWzMaintain item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsWzMaintainServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsWzMaintainServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增后勤维修", description = "批量新增后勤维修")
    @ApiOperation(value = "批量新增后勤维修")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "后勤维修") @RequestBody List<WsWzMaintain> items) throws Exception {
        try {
            List<WsWzMaintain> list = new ArrayList<>();
            for (WsWzMaintain item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsWzMaintainServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改后勤维修", description = "修改后勤维修")
    @ApiOperation(value = "修改后勤维修")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "后勤维修") @RequestBody WsWzMaintain item) throws Exception {
        try {
            if (wsWzMaintainServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsWzMaintainServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }


}
