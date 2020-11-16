package spring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.WsExchangeActiviti;
import spring.entity.WsExchangePresent;
import spring.exception.CustomException;
import spring.service.WsExchangeActivitiReturnService;
import spring.service.WsExchangeActivitiService;
import spring.service.WsExchangePresentService;
import spring.service.impl.WsExchangePresentServiceImpl;
import spring.util.JsonUtil;
import spring.util.ReType;

/**
 * @author shen
 * @date 2020/8/18 16:56
 */
@RestController
@Api(description = "交流科-涉外礼品", tags = {"sys-WsExchangePresentController"})
@RequestMapping("/exchangepresent")
public class WsExchangePresentController {

    @Autowired
    WsExchangePresentService wsExchangePresentServiceImpl;

    @LogOperation(code = "2", name = "查询涉外礼品", description = "查询涉外礼品")
    @ApiOperation(value = "查询涉外礼品")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "涉外礼品") @RequestBody WsExchangePresent item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        return wsExchangePresentServiceImpl.show(item, pageNumber, pageSize);
    }


    @ApiOperation(value = "查询单个数据")
    @PostMapping("/getbyid")
    @ResponseBody
    public String getbyid(@ApiParam(value = "id") @RequestParam(value = "id") String id) throws Exception {
        try {
            return JSON.toJSONString(wsExchangePresentServiceImpl.selectByPrimaryKey(id), SerializerFeature.WriteMapNullValue);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }
}
