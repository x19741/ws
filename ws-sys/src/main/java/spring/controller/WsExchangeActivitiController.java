package spring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.SysUser;
import spring.entity.WsExchangeActiviti;
import spring.entity.WsExchangeActivitiReturn;
import spring.exception.CustomException;
import spring.service.WsExchangeActivitiReturnService;
import spring.service.WsExchangeActivitiService;
import spring.service.impl.WsExchangeActivitiReturnServiceImpl;
import spring.service.impl.WsExchangeActivitiServiceImpl;
import spring.util.JsonUtil;
import spring.util.ReType;

@RestController
@Api(description = "交流科-涉外活动", tags = {"sys-WsExchangeActivitiController"})
@RequestMapping("/exchangeactiviti")
public class WsExchangeActivitiController {

    @Autowired
    WsExchangeActivitiService wsExchangeActivitiServiceImpl;
    @Autowired
    WsExchangeActivitiReturnService wsExchangeActivitiReturnServiceImpl;


    @LogOperation(code = "2", name = "查询涉外活动", description = "查询涉外活动")
    @ApiOperation(value = "查询涉外活动")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "涉外活动") @RequestBody WsExchangeActiviti item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsExchangeActivitiServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JSON.toJSONString(wsExchangeActivitiServiceImpl.selectByPrimaryKey(id), SerializerFeature.WriteMapNullValue);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

}
