package spring.controller;

import org.hibernate.validator.constraints.Length;
import spring.config.logs.LogOperation;
import spring.entity.SysLog;
import spring.entity.SysUser;
import spring.exception.CustomException;
import spring.service.LoginService;
import spring.service.SysLogService;
import spring.service.SysUserService;
import spring.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@Validated
@Api(description = "用户登录接口",tags = {"config-LoginController"})
public class LoginController {
    private Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    private SysLogService sysLogServiceImpl;

    @Autowired
    SysUserService sysUserServiceImpl;


    @ApiOperation(value = "首页登录方法")
    //@LogOperation(code = "1",name = "登录日志",description = "登录")
    @PostMapping("/login")
    @ResponseBody
    public String login(@NotNull(message = "账号不能为空")  @RequestParam String username,
                        @NotNull(message = "密码不能为空")  @RequestParam String password
    ,HttpServletRequest request) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        try {
            //将用户请求参数封装后，直接提交给Shiro处理
            UsernamePasswordToken token = new UsernamePasswordToken(username, Md5Util.EncoderByMd5(password));
            subject.login(token);
            //Shiro认证通过后会将user信息放到subject内，生成token并返回
            SysUser user=(SysUser)subject.getSession().getAttribute("user");
            if(user.getDelFlag()==null||user.getDelFlag()!=0){
                throw new CustomException("该用户已被禁用");
            }
            user.setToken(subject.getSession().getId().toString());
            user.setPassword(null);
            SysLog requestLog=new SysLog();
            //日志记录学生日常操作
            requestLog.setCodeName("1");
            requestLog.setLogName("登录日志");
            requestLog.setContent("登录");
            requestLog.setUserId(user==null?"":user.getId());
            requestLog.setUserName(user==null?"":user.getRealName());
            requestLog.setIp(CusAccessObjectUtil.getIpAddress(request));
            requestLog.setCreateTime(new Date());
            Long startTime=System.currentTimeMillis();
            requestLog.setClassName("LoginController");
            requestLog.setMethodName("login");
            sysLogServiceImpl.insertLog(requestLog);
            return JsonUtil.sucess("登录成功",user);
        }catch (CustomException e){
            return JsonUtil.error(e.getMessage());
        }catch (AuthenticationException e) {
            // 如果校验失败，shiro会抛出异常，返回客户端失败
            // 日志记录
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            //throw new Exception("密码错误");
            e.printStackTrace();
            return JsonUtil.error("账户名或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            // 日志记录
            throw new Exception("登录失败");
            //return JSON.toJSONString(JsonUtil.error("登录失败"));
        }
    }

    // 退出登录
    @ApiOperation(value = "退出登录")
    @LogOperation(code = "1",name = "退出登录日志",description = "退出登录")
    @PostMapping("/logout")
    @ResponseBody
    public String logout() throws Exception {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
            return  JsonUtil.sucess("退出登录成功",null);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            // 日志记录
            throw new Exception("退出登录失败");
        }
    }



    //修改密码
    @ApiOperation(value = "用户修改密码账号",notes="用户修改密码账号日志")
    @LogOperation(code = "2",name = "用户修改账密码号日志",description = "用户修改账号密码信息")
    @PostMapping("/updateuserpassword")
    @ResponseBody
    public String updateUserPassword(@NotNull(message = "密码不能为空") @Length(max = 16,min = 6,message = "请输入6-16长的有效密码")@RequestParam String password
            ,@NotNull(message = "重复密码不能为空")@Length(max = 16,min = 6,message = "请输入6-16长的有效重复密码") @RequestParam String verifyPassword
            ,@RequestParam String oldPassword) throws Exception {
        try {
            if(!password.equals(verifyPassword))
                throw new  CustomException("两次密码不一致");
            //获取当前登录用户
            SysUser user=(SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
            if(user==null)
                throw new CustomException("当前没有登录用户");
            user=sysUserServiceImpl.selectByPrimaryKey(user.getId());
            if(!user.getPassword().equals(Md5Util.EncoderByMd5(oldPassword)))
                throw new CustomException("原密码不正确！");
            user.setPassword(Md5Util.EncoderByMd5(password));
            if(sysUserServiceImpl.updateByPrimaryKeySelective(user)>0){
                return  JsonUtil.sucess("成功",null);
            }
            throw  new CustomException("修改密码失败。");
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }




}