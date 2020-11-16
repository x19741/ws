package spring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsCity;
import spring.entity.WsNotice;
import spring.exception.CustomException;
import spring.service.WsCityService;
import spring.service.WsNoticeService;
import spring.service.impl.WsNoticeServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "公告表", tags = {"sys-WsNoticeController"})
@RequestMapping("/notice")
public class WsNoticeController {

    @Autowired
    WsNoticeService wsNoticeServiceImpl;

    @LogOperation(code = "2", name = "查询公告表", description = "查询公告表")
    @ApiOperation(value = "查询公告表")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "公告表") @RequestBody WsNotice item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsNoticeServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsNoticeServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增公告表", description = "新增公告表")
    @ApiOperation(value = "新增公告表")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "公告表") @RequestBody WsNotice item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsNoticeServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsNoticeServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增公告表", description = "批量新增公告表")
    @ApiOperation(value = "批量新增公告表")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "公告表") @RequestBody List<WsNotice> items) throws Exception {
        try {
            List<WsNotice> list = new ArrayList<>();
            for (WsNotice item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsNoticeServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改公告表", description = "修改公告表")
    @ApiOperation(value = "修改公告表")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "公告表") @RequestBody WsNotice item) throws Exception {
        try {
            if (wsNoticeServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsNoticeServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }



    @LogOperation(code = "2",name = "查询form公告表",description = "查询form公告表")
    @ApiOperation(value = "查询form公告表")
    @PostMapping("/getform")
    @ResponseBody
    public ReType getForm(@ApiParam(value = "公告表") @RequestBody WsNotice item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsNoticeServiceImpl.showForm(item, pageNumber, pageSize);
    }


    // 删除
    @LogOperation(code = "2",name = "查询公告表",description = "查询公告表")
    @ApiOperation(value = "删除公告表")
    @PostMapping("/del")
    @ResponseBody
    public String dateleFodderPage( String ids ) throws Exception {
        try{
            return JsonUtil.sucess("成功修改了"+wsNoticeServiceImpl.deleteByIds(ids)+"条数据",null);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }


}
