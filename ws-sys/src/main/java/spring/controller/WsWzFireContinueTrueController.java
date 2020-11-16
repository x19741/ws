package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsWzFireContinueTrue;
import spring.exception.CustomException;
import spring.service.WsWzFireContinueTrueService;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "国际处外专科-外教续聘-拟续聘外教基本情况", tags = {"sys-WsWzFireContinueTrueController"})
@RequestMapping("/wzfirecontinuetrue")
public class WsWzFireContinueTrueController {

    @Autowired
    WsWzFireContinueTrueService wsWzFireContinueTrueServiceImpl;

    @LogOperation(code = "2", name = "查询外教续聘-拟续聘外教基本情况", description = "查询外教续聘-拟续聘外教基本情况")
    @ApiOperation(value = "查询外教续聘-拟续聘外教基本情况")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "外教续聘-拟续聘外教基本情况") @RequestBody WsWzFireContinueTrue item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return wsWzFireContinueTrueServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) {
        try {
            return JsonUtil.sucess("成功", wsWzFireContinueTrueServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增外教续聘-拟续聘外教基本情况", description = "新增外教续聘-拟续聘外教基本情况")
    @ApiOperation(value = "新增外教续聘-拟续聘外教基本情况")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "外教续聘-拟续聘外教基本情况") @RequestBody WsWzFireContinueTrue item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsWzFireContinueTrueServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsWzFireContinueTrueServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增外教续聘-拟续聘外教基本情况", description = "批量新增外教续聘-拟续聘外教基本情况")
    @ApiOperation(value = "批量新增外教续聘-拟续聘外教基本情况")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "外教续聘-拟续聘外教基本情况") @RequestBody List<WsWzFireContinueTrue> items) throws Exception {
        try {
            List<WsWzFireContinueTrue> list = new ArrayList<>();
            for (WsWzFireContinueTrue item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsWzFireContinueTrueServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改外教续聘-拟续聘外教基本情况", description = "修改外教续聘-拟续聘外教基本情况")
    @ApiOperation(value = "修改外教续聘-拟续聘外教基本情况")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "外教续聘-拟续聘外教基本情况") @RequestBody WsWzFireContinueTrue item) {
        try {
            if (wsWzFireContinueTrueServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsWzFireContinueTrueServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }


}
