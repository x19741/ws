package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsKongziActualBudgetPro;
import spring.exception.CustomException;
import spring.service.WsKongziActualBudgetProService;
import spring.service.impl.WsKongziActualBudgetProServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "孔子学院-决算申请-项目表", tags = {"sys-WsKongziActualBudgetProController"})
@RequestMapping("/kongziactualbudgetpro")
public class WsKongziActualBudgetProController {

    @Autowired
    WsKongziActualBudgetProService wsKongziActualBudgetProServiceImpl;

    @LogOperation(code = "2", name = "查询孔子学院决算申请-项目表", description = "查询孔子学院决算申请-项目表")
    @ApiOperation(value = "查询孔子学院决算申请-项目表")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "孔子学院决算申请-项目表") @RequestBody WsKongziActualBudgetPro item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsKongziActualBudgetProServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsKongziActualBudgetProServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增孔子学院决算申请-项目表", description = "新增孔子学院决算申请-项目表")
    @ApiOperation(value = "新增孔子学院决算申请-项目表")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "孔子学院决算申请-项目表") @RequestBody WsKongziActualBudgetPro item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsKongziActualBudgetProServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsKongziActualBudgetProServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增孔子学院决算申请-项目表", description = "批量新增孔子学院决算申请-项目表")
    @ApiOperation(value = "批量新增孔子学院决算申请-项目表")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "孔子学院决算申请-项目表") @RequestBody List<WsKongziActualBudgetPro> items) throws Exception {
        try {
            List<WsKongziActualBudgetPro> list = new ArrayList<>();
            for (WsKongziActualBudgetPro item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsKongziActualBudgetProServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改孔子学院决算申请-项目表", description = "修改孔子学院决算申请-项目表")
    @ApiOperation(value = "修改孔子学院决算申请-项目表")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "孔子学院决算申请-项目表") @RequestBody WsKongziActualBudgetPro item) throws Exception {
        try {
            if (wsKongziActualBudgetProServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsKongziActualBudgetProServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }
}
