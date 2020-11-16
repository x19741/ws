package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsAccredit;
import spring.entity.WsExchangeDateSub;
import spring.exception.CustomException;
import spring.service.WsAccreditService;
import spring.service.WsExchangeDateSubService;
import spring.service.impl.WsExchangeDateSubServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "交流科-资料报送", tags = {"sys-WsExchangeDateSubController"})
@RequestMapping("/exchangedatesub")
public class WsExchangeDateSubController {

    @Autowired
    WsExchangeDateSubService wsExchangeDateSubServiceImpl;

    @LogOperation(code = "2", name = "查询资料报送", description = "查询资料报送")
    @ApiOperation(value = "查询资料报送")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "资料报送") @RequestBody WsExchangeDateSub item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsExchangeDateSubServiceImpl.show(item, pageNumber, pageSize);
    }

    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsExchangeDateSubServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增资料报送", description = "新增资料报送")
    @ApiOperation(value = "新增资料报送")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "资料报送") @RequestBody WsExchangeDateSub item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsExchangeDateSubServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsExchangeDateSubServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增资料报送", description = "批量新增资料报送")
    @ApiOperation(value = "批量新增资料报送")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "资料报送") @RequestBody List<WsExchangeDateSub> items) throws Exception {
        try {
            List<WsExchangeDateSub> list = new ArrayList<>();
            for (WsExchangeDateSub item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsExchangeDateSubServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改资料报送", description = "修改资料报送")
    @ApiOperation(value = "修改资料报送")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "资料报送") @RequestBody WsExchangeDateSub item) throws Exception {
        try {
            if (wsExchangeDateSubServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsExchangeDateSubServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }


}
