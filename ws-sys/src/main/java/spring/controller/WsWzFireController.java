package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsWzFire;
import spring.exception.CustomException;
import spring.service.WsWzFireService;
import spring.service.impl.WsWzFireServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "国际处外专科-长期外教聘用", tags = {"sys-WsWzFireController"})
@RequestMapping("/wzfire")
public class WsWzFireController {

    @Autowired
    WsWzFireService wsWzFireServiceImpl;

    @LogOperation(code = "2", name = "查询长期外教聘用", description = "查询长期外教聘用")
    @ApiOperation(value = "查询长期外教聘用")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "长期外教聘用") @RequestBody WsWzFire item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsWzFireServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsWzFireServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增长期外教聘用", description = "新增长期外教聘用")
    @ApiOperation(value = "新增长期外教聘用")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "长期外教聘用") @RequestBody WsWzFire item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsWzFireServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsWzFireServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增长期外教聘用", description = "批量新增长期外教聘用")
    @ApiOperation(value = "批量新增长期外教聘用")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "长期外教聘用") @RequestBody List<WsWzFire> items) throws Exception {
        try {
            List<WsWzFire> list = new ArrayList<>();
            for (WsWzFire item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsWzFireServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改长期外教聘用", description = "修改长期外教聘用")
    @ApiOperation(value = "修改长期外教聘用")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "长期外教聘用") @RequestBody WsWzFire item) throws Exception {
        try {
            if (wsWzFireServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsWzFireServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }


}
