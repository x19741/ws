package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsWzFire;
import spring.entity.WsWzFireContinue;
import spring.exception.CustomException;
import spring.service.WsWzFireContinueService;
import spring.service.WsWzFireService;
import spring.service.impl.WsWzFireContinueServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "国际处外专科-外教续聘", tags = {"sys-WsWzFireContinueController"})
@RequestMapping("/wzfirecontinue")
public class WsWzFireContinueController {

    @Autowired
    WsWzFireContinueService wsWzFireContinueServiceImpl;

    @LogOperation(code = "2", name = "查询外教续聘", description = "查询外教续聘")
    @ApiOperation(value = "查询外教续聘")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "外教续聘") @RequestBody WsWzFireContinue item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsWzFireContinueServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsWzFireContinueServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增外教续聘", description = "新增外教续聘")
    @ApiOperation(value = "新增外教续聘")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "外教续聘") @RequestBody WsWzFireContinue item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsWzFireContinueServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsWzFireContinueServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增外教续聘", description = "批量新增外教续聘")
    @ApiOperation(value = "批量新增外教续聘")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "外教续聘") @RequestBody List<WsWzFireContinue> items) throws Exception {
        try {
            List<WsWzFireContinue> list = new ArrayList<>();
            for (WsWzFireContinue item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsWzFireContinueServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改外教续聘", description = "修改外教续聘")
    @ApiOperation(value = "修改外教续聘")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "外教续聘") @RequestBody WsWzFireContinue item) throws Exception {
        try {
            if (wsWzFireContinueServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsWzFireContinueServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }


}
