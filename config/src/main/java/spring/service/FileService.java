package spring.service;

import spring.entity.SysAttach;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService extends BaseService<SysAttach> {
    public String storeFile(MultipartFile file) ;
    public Map loadFileAsResource(String fileName) ;

}
