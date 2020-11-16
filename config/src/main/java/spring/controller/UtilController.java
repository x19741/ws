package spring.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.SysDepartment;
import spring.exception.CustomException;
import spring.service.SysDepartmentService;
import spring.service.SysSerialNumberService;
import spring.service.impl.SysSerialNumberServiceImpl;
import spring.util.IdWorker;
import spring.util.JsonUtil;
import spring.util.ReType;

import java.util.UUID;

@RestController
@Api(description = "工具", tags = {"config-UtilController"})
@RequestMapping("/util")
public class UtilController {

    @Autowired
    SysSerialNumberService sysSerialNumberServiceImpl;

    @ApiOperation(value = "流水号")
    @PostMapping("/getserialnumber")
    public String getSerialNumber(@ApiParam(value = "流水号类型",required = true) @RequestParam(value="type",defaultValue = "默认类型") String type) throws Exception {
        return JsonUtil.sucess("成功", sysSerialNumberServiceImpl.getSerialNumber(type));
    }
}
