package spring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.config.logs.LogOperation;
import spring.exception.CustomException;
import spring.service.ProcessService;
import spring.util.JsonUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@RestController
@Api(description = "流程通用方法接口", tags = {"web-ProcessController"})
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    ProcessService processServiceImpl;

    @LogOperation(code = "2", name = "获取流程下一个节点", description = "获取流程下一个节点")
    @ApiOperation(value = "获取流程下一个节点")
    @PostMapping("/getnextnode")
    @ResponseBody
    public String getNextNode(
            @ApiParam(value = "任务ID", required = false) @RequestParam(value = "taskId", required = false) String taskId
            , @ApiParam(value = "模型ID", required = true) @RequestParam(value = "modelId", required = true) String modelId
            , @ApiParam(value = "表单mainid", required = false) @RequestParam(value = "businessKey", required = false) String businessKey
    ) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        try {
            if(modelId==null||"".equals(modelId)){
                throw new CustomException(CustomException.EXCEPTION_MASSAGE);
            }
            return JSON.toJSONString(processServiceImpl.getNextNode(taskId, modelId, businessKey), SerializerFeature.WriteMapNullValue);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @LogOperation(code = "2", name = "办理任务", description = "办理任务")
    @ApiOperation(value = "办理任务")
    @PostMapping("/completeTask")
    @ResponseBody
    public String completeTask(
            @ApiParam(value = "任务ID", required = false) @RequestParam(required = false) String taskId
            , @ApiParam(value = "下一节点处理人", required = false) @RequestParam(required = false) String Assignee
            , @ApiParam(value = "下一节点", required = false) @RequestParam(required = false) String nextNode
            , @ApiParam(value = "资料", required = false) @RequestParam(required = false) String comformInfo
            , @ApiParam(value = "资料formid", required = false) @RequestParam(required = false) String comformId
            , @ApiParam(value = "业务ID(表单ID)", required = false) @RequestParam(required = false) String businessKey
            , @ApiParam(value = "模型ID", required = true) @RequestParam(required = false) String modelId
            , @ApiParam(value = "意见", required = false) @RequestParam(required = false) String opinion
            , @ApiParam(value = "流水号", required = false) @RequestParam(required = false) String serialNumber
            , @ApiParam(value = "紧急程度", required = false) @RequestParam(required = false) String urgencyDegree) throws Exception {
        try {
            return JsonUtil.sucess("成功",processServiceImpl.completeTask(taskId, modelId, Assignee, nextNode, comformInfo,comformId, businessKey, opinion, serialNumber, urgencyDegree));
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @ApiOperation(value = "查询")
    @PostMapping("/get")
    @ResponseBody
    public String selectFodderPage(@ApiParam(value = "表单信息") @RequestParam(value = "comformInfo", defaultValue = "{'formId':'cz_pro'}") String comformInfo
            , @ApiParam(value = "页码", required = true) @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        /*try{
            return JSON.toJSONString( publicServiceImpl.selectComfromTable(comformInfo,pageNumber,pageSize), SerializerFeature.WriteMapNullValue);
        }catch (CustomException ce){
            return JsonUtil.error(ce.getMessage());
        }*/
        return null;
    }

    @ApiOperation(value = "查询当前流程图")
    @PostMapping("/processimagedate")
    @ResponseBody
    public String getprocessImageDate(@ApiParam(value = "任务ID", required = false) @RequestParam(value = "taskId", required = false) String taskId,@ApiParam(value = "模型id", required = false) @RequestParam(value = "modelId", required = false) String modelId) {
        try {
            String id=null;
            if(taskId==null||"".equals(taskId)){
                if(modelId==null||"".equals(modelId)){
                    throw new CustomException("模型id和任务id不能同时为空！");
                }
                id=modelId;
            }else{
                id=taskId;
            }
            Map map = processServiceImpl.getprocessImageDate(taskId,modelId);
            String imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/process/processimage/")
                    .path(id)
                    .toUriString();
            map.put("imageUri", imageUri);
            return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        } catch (CustomException ce) {
            return JsonUtil.error(ce.getMessage());
        }
    }

    @ApiOperation(value = "流程图by任务Id")
    @GetMapping("/processimage/{taskId}")
    public void image(HttpServletResponse response, @PathVariable String taskId) {
        try {
            InputStream is = processServiceImpl.getProcessImage(taskId);
            if (is == null)
                return;
            response.setContentType("image/png");
            BufferedImage image = ImageIO.read(is);
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "png", out);
            is.close();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            //log.error("查看流程图失败", ex);
        }
    }
}
