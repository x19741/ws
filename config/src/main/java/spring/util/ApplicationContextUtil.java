package spring.util;

import org.springframework.context.ApplicationContext;

public class ApplicationContextUtil {
    private static ApplicationContextUtil instance;

    private ApplicationContext applicationContext;

    private ApplicationContextUtil() {
    }

    public static ApplicationContextUtil getInstance() {
        if (instance == null) {
            synchronized (ApplicationContextUtil.class) {
                if (instance == null) {
                    instance = new ApplicationContextUtil();
                }
            }
        }
        return instance;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
