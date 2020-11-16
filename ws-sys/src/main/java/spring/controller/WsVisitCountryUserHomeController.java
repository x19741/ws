package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsVisitCountryUserHome;
import spring.exception.CustomException;
import spring.service.WsVisitCountryUserHomeService;
import spring.service.impl.WsVisitCountryUserHomeServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "出访科-因公临时出国申报表-人员公示-家庭", tags = {"sys-WsVisitCountryUserHomeController"})
@RequestMapping("/visitcountryuserhome")
public class WsVisitCountryUserHomeController {

    @Autowired
    WsVisitCountryUserHomeService wsVisitCountryUserHomeServiceImpl;

    @LogOperation(code = "2", name = "查询出访科-因公临时出国申报表-人员公示-家庭", description = "查询出访科-因公临时出国申报表-人员公示-家庭")
    @ApiOperation(value = "查询出访科-因公临时出国申报表-人员公示-家庭")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "出访科-因公临时出国申报表-人员公示-家庭") @RequestBody WsVisitCountryUserHome item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsVisitCountryUserHomeServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JsonUtil.sucess("成功", wsVisitCountryUserHomeServiceImpl.selectByPrimaryKey(id));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "新增出访科-因公临时出国申报表-人员公示-家庭", description = "新增出访科-因公临时出国申报表-人员公示-家庭")
    @ApiOperation(value = "新增出访科-因公临时出国申报表-人员公示-家庭")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "出访科-因公临时出国申报表-人员公示-家庭") @RequestBody WsVisitCountryUserHome item) throws Exception {
        try {
            item.setId(IdWorker.getIdWorkerNext().toString());
            if (wsVisitCountryUserHomeServiceImpl.insert(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsVisitCountryUserHomeServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "批量新增出访科-因公临时出国申报表-人员公示-家庭", description = "批量新增因公临时出国申报表-人员公示-家庭")
    @ApiOperation(value = "批量新增因公临时出国申报表-人员公示-家庭")
    @PostMapping("/adds")
    @ResponseBody
    public String adds(@ApiParam(value = "因公临时出国申报表-人员公示-家庭") @RequestBody List<WsVisitCountryUserHome> items) throws Exception {
        try {
            List<WsVisitCountryUserHome> list = new ArrayList<>();
            for (WsVisitCountryUserHome item : items) {
                item.setId(IdWorker.getIdWorkerNext().toString());
                list.add(item);
            }
            int i;
            i = wsVisitCountryUserHomeServiceImpl.inserts(list);
            if (i <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("成功添加！" + i + "个数据。", null);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "修改因公临时出国申报表-人员公示-家庭", description = "修改因公临时出国申报表-人员公示-家庭")
    @ApiOperation(value = "修改因公临时出国申报表-人员公示-家庭")
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ApiParam(value = "因公临时出国申报表-人员公示-家庭") @RequestBody WsVisitCountryUserHome item) throws Exception {
        try {
            if (wsVisitCountryUserHomeServiceImpl.updateBeanByPrimaryKeySelective(item) <= 0)
                throw new CustomException("失败");
            return JsonUtil.sucess("添加成功！", wsVisitCountryUserHomeServiceImpl.selectByPrimaryKey(item.getId()));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }



    @LogOperation(code = "2",name = "查询form因公临时出国申报表-人员公示-家庭",description = "查询form因公临时出国申报表-人员公示-家庭")
    @ApiOperation(value = "查询form因公临时出国申报表-人员公示-家庭")
    @PostMapping("/getform")
    @ResponseBody
    public ReType getForm(@ApiParam(value = "因公临时出国申报表-人员公示-家庭") @RequestBody WsVisitCountryUserHome item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsVisitCountryUserHomeServiceImpl.showForm(item, pageNumber, pageSize);
    }


    // 删除
    @LogOperation(code = "2",name = "查询因公临时出国申报表-人员公示-家庭",description = "查询因公临时出国申报表-人员公示-家庭")
    @ApiOperation(value = "删除因公临时出国申报表-人员公示-家庭")
    @PostMapping("/del")
    @ResponseBody
    public String dateleFodderPage( String ids ) throws Exception {
        try{
            return JsonUtil.sucess("成功修改了"+wsVisitCountryUserHomeServiceImpl.deleteByIds(ids)+"条数据",null);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }
    }


}
