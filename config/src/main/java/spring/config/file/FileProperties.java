package spring.config.file;


import org.springframework.boot.context.properties.ConfigurationProperties;
//配置完成，以后若有file前缀开头的参数需要配置，可直接在application.yml配置文件中配置并更新FileProperties.java即可。
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
