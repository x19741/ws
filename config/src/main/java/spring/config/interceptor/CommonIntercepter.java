package spring.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//用于解决前后端分离跨域问题
@Component
public class CommonIntercepter implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //在Response Header中增加 "Access-Control-Allow-Origin: *"
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        //response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild");
        //允许跨域,不能放在postHandle内

        //response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        /*if (request.getMethod().equals("OPTIONS")) {
            response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
        }*/

        return true;
    }
}
