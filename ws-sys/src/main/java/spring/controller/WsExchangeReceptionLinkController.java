package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsExchangeReceptionLink;
import spring.entity.WsKongziBudget;
import spring.exception.CustomException;
import spring.service.WsExchangeReceptionLinkService;
import spring.service.WsKongziBudgetService;
import spring.service.impl.WsExchangeReceptionLinkServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "交流科-来访接待-活动", tags = {"sys-WsExchangeReceptionLinkController"})
@RequestMapping("/exchangereceptionlink")
public class WsExchangeReceptionLinkController {

    @Autowired
    WsExchangeReceptionLinkService wsExchangeReceptionLinkServiceImpl;

    @LogOperation(code = "2", name = "查询来访接待-活动", description = "查询来访接待-活动")
    @ApiOperation(value = "查询来访接待-活动")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "来访接待-活动") @RequestBody WsExchangeReceptionLink item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsExchangeReceptionLinkServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsExchangeReceptionLinkServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增来访接待-活动", description = "新增来访接待-活动")
    @ApiOperation(value = "新增来访接待-活动")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "来访接待-活动") @RequestBody WsExchangeReceptionLink item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsExchangeReceptionLinkServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsExchangeReceptionLinkServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增来访接待-活动", description = "批量新增来访接待-活动")
    @ApiOperation(value = "批量新增来访接待-活动")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "来访接待-活动") @RequestBody List<WsExchangeReceptionLink> items) throws Exception {
        try {
            List<WsExchangeReceptionLink> list = new ArrayList<>();
            for (WsExchangeReceptionLink item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsExchangeReceptionLinkServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改来访接待-活动", description = "修改来访接待-活动")
    @ApiOperation(value = "修改来访接待-活动")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "来访接待-活动") @RequestBody WsExchangeReceptionLink item) throws Exception {
        try {
            if (wsExchangeReceptionLinkServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsExchangeReceptionLinkServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }


}
