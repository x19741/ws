package spring;


import spring.config.file.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import spring.util.ApplicationContextUtil;
import tk.mybatis.spring.annotation.MapperScan;



@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,org.activiti.spring.boot.SecurityAutoConfiguration.class})

/*@Configuration*/
@ComponentScan(value="spring.*")
@MapperScan("spring.mapper")
@EnableConfigurationProperties({FileProperties.class}) //文件路径注解
public class Application extends SpringBootServletInitializer   {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        ApplicationContextUtil.getInstance().setApplicationContext(applicationContext);
        System.out.println("Server start succ");
    }



}
