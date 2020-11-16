package spring.service.impl;

import spring.config.file.FileException;
import spring.config.file.FileProperties;
import spring.entity.SysAttach;
import spring.entity.SysUser;
import spring.mapper.SysAttachMapper;
import spring.mappers.Mapper;
import spring.service.FileService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import spring.util.IdWorker;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ Service
@Transactional
public class FileServiceImpl extends BaseServiceImpl<SysAttach> implements FileService {


    private final Path fileStorageLocation; // 文件在本地存储的地址

    @Autowired
    public FileServiceImpl(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileException("无法创建将存储上传文件的目录。", ex);
        }
    }
    @Autowired
    SysAttachMapper sysAttachMapper;
    @Override
    public Mapper<SysAttach> getMapper() {
        return sysAttachMapper;
    }

    /**
     * 存储文件到系统
     *
     * @param file 文件
     * @return 文件名
     */
    public String storeFile(MultipartFile file) {
        // Normalize file name

        String fileName =StringUtils.cleanPath(file.getOriginalFilename());
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);  //后缀

        String uuid=  IdWorker.getIdWorkerNext().toString();;
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileException("对不起!文件名包含无效的路径序列。" + fileName);
            }
            SysUser user=(SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
            // Copy file to the target location (Replacing existing file with the same name)
            String path=null;
            if(user!=null){
                path=user.getUsername()+"/"+uuid;
                File filepath=new File(String.valueOf(this.fileStorageLocation.resolve(user.getUsername())));
                if(!filepath.exists()){//如果文件夹不存在
                    filepath.mkdir();//创建文件夹
                }
            }else{
                path=uuid;
            }

            Path targetLocation = this.fileStorageLocation.resolve(path); //文件保护

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);


            SysAttach attach =new SysAttach();
            attach.setId(uuid);
            attach.setAttachName(fileName);
            attach.setAttachFile(uuid);
            attach.setAttachfFlepath(path);
            attach.setUpdateBy(user==null?"":user.getId());
            attach.setUpdateDate(new Date());
            attach.setFileSize((int) file.getSize() );
            attach.setAttachType(file.getContentType());
            attach.setAttach_suffix(suffix);
            sysAttachMapper.insert(attach);
            return uuid;
        } catch (IOException ex) {
            throw new FileException("无法存储文件 " + fileName + "。请再试一次!", ex);
        }catch (Exception ex) {
            throw new FileException("无法存储文件 " + fileName + "。请再试一次!", ex);
        }
    }

    /**
     * 加载文件
     * @param fileName 文件名
     * @return 文件
     */
    public Map loadFileAsResource(String fileName) {
        try {
            SysAttach attach= sysAttachMapper.selectByPrimaryKey(fileName);
            Map<String,Object> map =new HashMap<String,Object>();

            Path filePath = this.fileStorageLocation.resolve(attach.getAttachfFlepath() ).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            map.put("resource",resource);
            map.put("filename",attach.getAttachName());
            map.put("contentType",attach.getAttachType());

            if(resource.exists()) {
                return map;
            } else {
                throw new FileException("文件未找到 " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("文件未找到 " + fileName, ex);
        }catch (Exception ex) {
            throw new FileException("文件未找到 " + fileName, ex);
        }

    }




}
