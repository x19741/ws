package spring.controller;


import spring.config.file.UploadFileResponse;
import spring.config.logs.LogOperation;
import spring.config.logs.RequestLogAspect;
import spring.service.FileService;

import spring.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Api(description = "文件管理接口",tags = {"config-FileController"})
public class FileController {

    private Logger log = Logger.getLogger(RequestLogAspect.class);

    @Autowired
    private FileService fileServiceImpl;

    @ApiOperation(value = "/uploadfile",notes="附件上传")
    @LogOperation(code = "2",name = "附件上传",description = "上传单个附件")
    @PostMapping("/uploadfile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = fileServiceImpl.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadfile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize(),file.getOriginalFilename());
    }


    @ApiOperation(value = "/uploadfiles",notes="批量附件上传")
    @LogOperation(code = "2",name = "附件件批量上传",description = "批量上传附件")
    @PostMapping("/uploadfiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "/downloadfile/{fileName}",notes="附件下载")
    //@LogOperation(code = "2",name = "附件下载",description = "下载单个附件")
    //@GetMapping("/downloadFile/{fileName:.+}")
    @GetMapping("/downloadfile/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // Load file as Resource
        Map map =fileServiceImpl.loadFileAsResource(fileName);
        Resource resource = (Resource) map.get("resource");



        // Try to determine file's content type
        String contentType = (String) map.get("contentType");

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        //response.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=UTF-8");
        MediaType aa=  MediaType.parseMediaType(contentType );
        MediaType bb= new MediaType(aa,Charset.forName("UTF-8"));
        // MediaType mediaType = new MediaType. //(contentType, String.valueOf(Charset.forName("utf-8")));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +new String( ((String) map.get("filename")).getBytes("UTF-8"),"ISO-8859-1") + "")
                .body(resource);
    }

    @ApiOperation(value = "/selectfile/{fileName}",notes="附件查看")
    //@LogOperation(code = "2",name = "附件查看",description = "下载单个查看")
    @GetMapping("/selectfile/{fileName}")
    public String downloadfile(@PathVariable String fileName, HttpServletRequest request) {
        return JsonUtil.sucess("成功",fileServiceImpl.selectByPrimaryKey(fileName));
    }
}
