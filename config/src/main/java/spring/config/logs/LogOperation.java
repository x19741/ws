package spring.config.logs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogOperation {

    /**
     * 栏目dode
     * @return
     */
    String code() default "";

    /**
     * 日志名称
     * @return
     */
    String name() default "";
    /**
     * 类名
     * @return
     */
    String calss() default "";
    /**
     * 方法名称
     */
    String method() default "";
    /**
     * 描述
     * @return
     */
    String description() default "";


}
