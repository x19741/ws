package spring.config.filter;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CORSAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CORSAuthenticationFilter.class);

    public CORSAuthenticationFilter() {

        super();
    }


    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        //Always return true if the request's method is OPTIONSif (request instanceof HttpServletRequest) {
        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }



    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse)response;
        HttpServletRequest req= (HttpServletRequest) request;
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "*");
        //res.setStatus(HttpServletResponse.SC_OK);
        res.setStatus(700);
        request.setAttribute("未登录","700");
        res.setCharacterEncoding("UTF-8");
        res.setHeader("Content-Type", "text/html; charset=UTF-8");
        PrintWriter writer = res.getWriter();
        Map<String, Object> map= new HashMap<>();
        map.put("status", 700);
        map.put("message", "未登录");
        writer.write( JSON.toJSONString(map));
        writer.close();
        return false;
    }
}

