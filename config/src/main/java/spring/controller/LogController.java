package spring.controller;

import spring.config.logs.LogOperation;
import spring.entity.SysLog;
import spring.service.SysLogService;
import spring.util.JsonUtil;
import spring.util.ReType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Api(description = "日志管理",tags = {"config-logController"})
public class LogController {

    private Logger log = Logger.getLogger(LogController.class);

    @Autowired
    private SysLogService sysLogServiceImpl;

    //用户模糊分页查询   当前页数 每页多少个  一共多少个
    @ApiOperation(value = "/admin/selectlog",notes="管理员查询日志")
    @LogOperation(code = "2",name = "管理员查询日志日志",description = "管理员查询日志")
    @PostMapping("/admin/selectlog")
    @ResponseBody
    public ReType selectLog(@ApiParam(value = "日志名称",required = false)  @RequestBody(required = false) SysLog sysLog
            , @ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") String pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "10") String pageSize  ) throws Exception {
        try {
            return sysLogServiceImpl.show(sysLog,Integer.valueOf(pageNumber),Integer.valueOf(pageSize))  ;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            //日志记录
            throw new Exception("出现了意外的错误。");
        }
    }


    @ApiOperation(value = "/admin/selectlogbyid",notes="管理员查询单个日志")
    @LogOperation(code = "2",name = "管理员查询单个日志日志",description = "管理员查询单个日志")
    @PostMapping("/admin/selectlogbyid")
    @ResponseBody
    public String selectlogbyid( @ApiParam(value = "日志id",required = true) @RequestParam(value="id") String id  ) throws Exception {
        try {
            return JsonUtil.sucess("成功",sysLogServiceImpl.selectByPrimaryKey(id)) ;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            //日志记录
            throw new Exception("出现了意外的错误。");
        }
    }




}
