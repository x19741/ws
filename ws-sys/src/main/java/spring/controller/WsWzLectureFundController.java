package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsWzLectureEndFund;
import spring.entity.WsWzLectureFund;
import spring.exception.CustomException;
import spring.service.WsWzLectureEndFundService;
import spring.service.WsWzLectureFundService;
import spring.service.impl.WsWzLectureFundServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "国际处外专科-校海外名师项目-工作及社会实践经历", tags = {"sys-WsWzLectureFundController"})
@RequestMapping("/wzlecturefund")
public class WsWzLectureFundController {

    @Autowired
    WsWzLectureFundService wsWzLectureFundServiceImpl;

    @LogOperation(code = "2", name = "查询校海外名师项目-工作及社会实践经历", description = "查询校海外名师项目-工作及社会实践经历")
    @ApiOperation(value = "查询校海外名师项目-工作及社会实践经历")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "校海外名师项目-工作及社会实践经历") @RequestBody WsWzLectureFund item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsWzLectureFundServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsWzLectureFundServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增校海外名师项目-工作及社会实践经历", description = "新增校海外名师项目-工作及社会实践经历")
    @ApiOperation(value = "新增校海外名师项目-工作及社会实践经历")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "校海外名师项目-工作及社会实践经历") @RequestBody WsWzLectureFund item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsWzLectureFundServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsWzLectureFundServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增校海外名师项目-工作及社会实践经历", description = "批量新增校海外名师项目-工作及社会实践经历")
    @ApiOperation(value = "批量新增校海外名师项目-工作及社会实践经历")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "校海外名师项目-工作及社会实践经历") @RequestBody List<WsWzLectureFund> items) throws Exception {
        try {
            List<WsWzLectureFund> list = new ArrayList<>();
            for (WsWzLectureFund item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsWzLectureFundServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改校海外名师项目-工作及社会实践经历", description = "修改校海外名师项目-工作及社会实践经历")
    @ApiOperation(value = "修改校海外名师项目-工作及社会实践经历")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "校海外名师项目-工作及社会实践经历") @RequestBody WsWzLectureFund item) throws Exception {
        try {
            if (wsWzLectureFundServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsWzLectureFundServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }


}
