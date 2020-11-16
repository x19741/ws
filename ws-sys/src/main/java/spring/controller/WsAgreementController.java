package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsAgreement;
import spring.entity.WsVisitFundInfo;
import spring.exception.CustomException;
import spring.service.WsAgreementService;
import spring.service.impl.WsAgreementServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "协议管理", tags = {"sys-WsAgreementController"})
@RequestMapping("/agreement")
public class WsAgreementController {

    @Autowired
    WsAgreementService wsAgreementServiceImpl;

    @LogOperation(code = "2", name = "查询协议管理", description = "查询协议管理")
    @ApiOperation(value = "查询协议管理")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "协议管理") @RequestBody WsAgreement item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsAgreementServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsAgreementServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增协议管理", description = "新增协议管理")
    @ApiOperation(value = "新增协议管理")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "协议管理") @RequestBody WsAgreement item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsAgreementServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsAgreementServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增协议管理", description = "批量新增协议管理")
    @ApiOperation(value = "批量新增协议管理")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "协议管理") @RequestBody List<WsAgreement> items) throws Exception {
        try {
            List<WsAgreement> list = new ArrayList<>();
            for (WsAgreement item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsAgreementServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改协议管理", description = "修改协议管理")
    @ApiOperation(value = "修改协议管理")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "协议管理") @RequestBody WsAgreement item) throws Exception {
        try {
            if (wsAgreementServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsAgreementServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2",name = "查询form协议管理",description = "查询form协议管理")
    @ApiOperation(value = "查询form协议管理")
    @PostMapping("/getform")
    @ResponseBody
    public ReType getForm(@ApiParam(value = "协议管理") @RequestBody WsAgreement item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsAgreementServiceImpl.showForm(item, pageNumber, pageSize);
    }


    // 删除
    @LogOperation(code = "2",name = "查询协议管理",description = "查询协议管理")
    @ApiOperation(value = "删除各个国家和地区开支标准")
    @PostMapping("/del")
    @ResponseBody
    public String dateleFodderPage( String ids ) throws Exception {
        try{
            return JsonUtil.sucess("成功修改了"+wsAgreementServiceImpl.deleteByIds(ids)+"条数据",null);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }

}
