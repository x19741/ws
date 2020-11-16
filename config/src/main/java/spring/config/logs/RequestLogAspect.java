package spring.config.logs;



import spring.entity.SysLog;
import spring.entity.SysUser;
import spring.service.SysLogService;
import spring.util.CusAccessObjectUtil;


import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Aspect()
@Component
public class RequestLogAspect {
    private SysLog requestLog=new SysLog();

    private long startTime;

    @Autowired
    private SysLogService sysLogServiceImpl;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(logOperation)")
    public void pointcutConfig(LogOperation logOperation){ }

    private   Logger log = Logger.getLogger(RequestLogAspect.class);

    @Before(value = "pointcutConfig(logOperation)", argNames = "joinpoint,logOperation")
    public void before(JoinPoint joinpoint,LogOperation logOperation) {


        startTime=System.currentTimeMillis();
    }

    //如有需要,可做操作
    @After(value="pointcutConfig(logOperation)")
    public void after(JoinPoint joinpoint,LogOperation logOperation) {

        Signature signature =joinpoint.getSignature();
        String className=signature.getDeclaringType().getSimpleName();
        String name =signature.getName();
        log.info(className+"."+name+"方法执行时间："+(System.currentTimeMillis()-startTime)+"");

        //获取用户信息
        //String userName = (SysUser) SecurityUtils.getSubject().getPrincipal(); //获取用户名
        SysUser user=(SysUser)SecurityUtils.getSubject().getSession().getAttribute("user");
        if(user==null)
            return;
        //日志记录学生日常操作
        requestLog.setCodeName(logOperation.code());
        requestLog.setLogName(logOperation.name());
        requestLog.setContent(logOperation.description());
        requestLog.setUserId(user==null?"":user.getId());
        requestLog.setUserName(user==null?"":user.getRealName());
        requestLog.setIp(CusAccessObjectUtil.getIpAddress(request));
        requestLog.setCreateTime(new Date());
        requestLog.setClassName(className);
        requestLog.setMethodName(name);
        //sysLogServiceImpl.insertLog(requestLog);
    }

    //如有需要,可做操作
   /* @AfterReturning(value="pointcutConfig(logOperation)",returning="returnValue")
    public void afterReturn(JoinPoint joinpoint,String returnValue,LogOperation logOperation) {
        LOG.info("后置返回通知---调用获得返回值后执行" + joinpoint);
    }*/

    //如有需要,可做操作
    @AfterThrowing(value = "pointcutConfig(logOperation)", argNames = "joinpoint,logOperation")
    public void afterThrows(JoinPoint joinpoint,LogOperation logOperation) {
        log.error("日志AfterThrowing出现错误");
    }





}
