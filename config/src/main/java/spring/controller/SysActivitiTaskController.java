package spring.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.SysActivitiTask;
import spring.exception.CustomException;
import spring.service.SysActivitiTaskService;
import spring.util.ReType;

@RestController
@Validated
@Api(description = "流程任务", tags = {"config-SysActivitiTaskController"})
@RequestMapping("/activitiTask")
public class SysActivitiTaskController {

    @Autowired
    SysActivitiTaskService sysActivitiTaskServiceImpl;

    @LogOperation(code = "2", name = "查询流程任务", description = "查询流程任务")
    @ApiOperation(value = "查询流程任务")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "流程任务") @RequestBody SysActivitiTask item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return sysActivitiTaskServiceImpl.show(item, pageNumber, pageSize);
    }


    @LogOperation(code = "2", name = "查询待办流程任务", description = "查询待办流程任务")
    @ApiOperation(value = "查询待办流程任务")
    @PostMapping("/getbacklog")
    @ResponseBody
    public ReType getBacklog(@ApiParam(value = "流程任务") @RequestBody SysActivitiTask item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return sysActivitiTaskServiceImpl.getBacklog(item, pageNumber, pageSize);
    }

    @LogOperation(code = "2", name = "查询已办流程任务", description = "查询已办流程任务")
    @ApiOperation(value = "查询已办流程任务")
    @PostMapping("/getunderway")
    @ResponseBody
    public ReType getUnderway(@ApiParam(value = "流程任务") @RequestBody SysActivitiTask item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return sysActivitiTaskServiceImpl.getUnderway(item, pageNumber, pageSize);
    }

    @LogOperation(code = "2", name = "查询已完成流程任务", description = "查询已完成流程任务")
    @ApiOperation(value = "查询已完成流程任务")
    @PostMapping("/getfinished")
    @ResponseBody
    public ReType getFinished(@ApiParam(value = "流程任务") @RequestBody SysActivitiTask item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return sysActivitiTaskServiceImpl.getFinished(item, pageNumber, pageSize);
    }

    @LogOperation(code = "2", name = "查询抄送流程任务", description = "查询抄送流程任务")
    @ApiOperation(value = "查询抄送流程任务")
    @PostMapping("/getcarbonCopy")
    @ResponseBody
    public ReType getCarbonCopy(@ApiParam(value = "流程任务") @RequestBody SysActivitiTask item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return sysActivitiTaskServiceImpl.getCarbonCopy(item, pageNumber, pageSize);
    }

    @LogOperation(code = "2", name = "查询我发起的流程任务", description = "查询我发起的流程任务")
    @ApiOperation(value = "查询我发起的流程任务")
    @PostMapping("/getinitiate")
    @ResponseBody
    public ReType getInitiate(@ApiParam(value = "流程任务") @RequestBody SysActivitiTask item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return sysActivitiTaskServiceImpl.getInitiate(item, pageNumber, pageSize);
    }

    @LogOperation(code = "2", name = "查询流程的最新任务", description = "查询流程的最新任务")
    @ApiOperation(value = "查询流程的最新任务")
    @PostMapping("/getthisprocess")
    @ResponseBody
    public ReType getThisProcess(@ApiParam(value = "流程任务") @RequestBody SysActivitiTask item
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        /*if(item.getBusinessKey()==null||"".equals(item.getBusinessKey())){
            throw  new CustomException("businessKey不能为空");
        }*/
        return sysActivitiTaskServiceImpl.getThisProcess(item, pageNumber, pageSize);
    }
}
