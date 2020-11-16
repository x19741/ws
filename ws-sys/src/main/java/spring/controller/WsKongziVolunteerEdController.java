package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsKongziVolunteerEd;
import spring.exception.CustomException;
import spring.service.WsKongziVolunteerEdService;
import spring.service.impl.WsKongziVolunteerEdServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "孔子学院-志愿者申请表-教育背景", tags = {"sys-WsKongziVolunteerEdController"})
@RequestMapping("/kongzivolunteered")
public class WsKongziVolunteerEdController {

    @Autowired
    WsKongziVolunteerEdService wsKongziVolunteerEdServiceImpl;

    @LogOperation(code = "2", name = "查询志愿者申请表-教育背景", description = "查询志愿者申请表-教育背景")
    @ApiOperation(value = "查询志愿者申请表-教育背景")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "志愿者申请表-教育背景") @RequestBody WsKongziVolunteerEd item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsKongziVolunteerEdServiceImpl.show(item, pageNumber, pageSize);
    }

    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsKongziVolunteerEdServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增志愿者申请表-教育背景", description = "新增志愿者申请表-教育背景")
    @ApiOperation(value = "新增志愿者申请表-教育背景")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "志愿者申请表-教育背景") @RequestBody WsKongziVolunteerEd item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsKongziVolunteerEdServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsKongziVolunteerEdServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增志愿者申请表-教育背景", description = "批量新增志愿者申请表-教育背景")
    @ApiOperation(value = "批量新增志愿者申请表-教育背景")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "志愿者申请表-教育背景") @RequestBody List<WsKongziVolunteerEd> items) throws Exception {
        try {
            List<WsKongziVolunteerEd> list = new ArrayList<>();
            for (WsKongziVolunteerEd item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsKongziVolunteerEdServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改志愿者申请表-教育背景", description = "修改志愿者申请表-教育背景")
    @ApiOperation(value = "修改志愿者申请表-教育背景")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "志愿者申请表-教育背景") @RequestBody WsKongziVolunteerEd item) throws Exception {
        try {
            if (wsKongziVolunteerEdServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsKongziVolunteerEdServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }


}
