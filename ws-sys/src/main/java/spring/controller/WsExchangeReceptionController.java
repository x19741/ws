package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsExchangeReception;
import spring.exception.CustomException;
import spring.service.WsExchangeReceptionService;
import spring.service.impl.WsExchangeReceptionServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "交流科-来访接待", tags = {"sys-WsExchangeReceptionController"})
@RequestMapping("/exchangereception")
public class WsExchangeReceptionController {

    @Autowired
    WsExchangeReceptionService wsExchangeReceptionServiceImpl;

    @LogOperation(code = "2", name = "查询来访接待", description = "查询来访接待")
    @ApiOperation(value = "查询来访接待")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "来访接待") @RequestBody WsExchangeReception item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsExchangeReceptionServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsExchangeReceptionServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增来访接待", description = "新增来访接待")
    @ApiOperation(value = "新增来访接待")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "来访接待") @RequestBody WsExchangeReception item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsExchangeReceptionServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsExchangeReceptionServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增来访接待", description = "批量新增来访接待")
    @ApiOperation(value = "批量新增来访接待")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "来访接待") @RequestBody List<WsExchangeReception> items) throws Exception {
        try {
            List<WsExchangeReception> list = new ArrayList<>();
            for (WsExchangeReception item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsExchangeReceptionServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改来访接待", description = "修改来访接待")
    @ApiOperation(value = "修改来访接待")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "来访接待") @RequestBody WsExchangeReception item) throws Exception {
        try {
            if (wsExchangeReceptionServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsExchangeReceptionServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }


}
