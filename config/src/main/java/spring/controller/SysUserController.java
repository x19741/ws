package spring.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.config.logs.LogOperation;
import spring.entity.SysLog;
import spring.entity.SysUser;
import spring.exception.CustomException;
import spring.service.*;
import spring.service.impl.SysUserServiceImpl;
import spring.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
@Validated
@Api(description = "用户管理",tags = {"config-SysUserController"})
@RequestMapping("/user")
public class SysUserController {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SysUserService sysUserServiceImpl;

    @Autowired
    private SysLogService sysLogServiceImpl;

    @LogOperation(code = "2",name = "查询用户",description = "查询用户")
    @ApiOperation(value = "查询用户")
    @PostMapping("/get")
    @ResponseBody
    public ReType select(@ApiParam(value = "用户") @RequestBody SysUser item
            ,@ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize  ) throws Exception {
        return sysUserServiceImpl.show(item,pageNumber,pageSize);
    }

    @LogOperation(code = "2",name = "查询用户表单",description = "查询用户表单")
    @ApiOperation(value = "查询用户表单")
    @PostMapping("/getform")
    @ResponseBody
    public ReType selectForm(@ApiParam(value = "用户") @RequestBody SysUser item
            ,@ApiParam(value = "页码",required = true) @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber
            , @ApiParam(value = "每页大小",required = true) @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize  ) throws Exception {
        return sysUserServiceImpl.showForm(item,pageNumber,pageSize);
    }

    @ApiOperation(value = "查询单个用户")
    @PostMapping("/getById")
    @ResponseBody
    public String getById(@ApiParam(value = "id") @RequestParam String id){
        return JsonUtil.sucess("成功",sysUserServiceImpl.selectByPrimaryKey(id));

    }
    @ApiOperation(value = "添加用户")
    @LogOperation(code = "2",name = "添加用户",description = "添加用户")
    @PostMapping("/add")
    @ResponseBody
    public String add(@ApiParam(value = "用户")  @RequestBody SysUser item) throws Exception {
        try{
            String uuid =IdWorker.getIdWorkerNext().toString();
            item.setId(uuid);
            item.setPassword( Md5Util.EncoderByMd5(item.getPassword()));
            if(sysUserServiceImpl.insert(item)>0)
                return  JsonUtil.sucess("成功",sysUserServiceImpl.selectByPrimaryKey(uuid));
            return  JsonUtil.error("失败");
        }catch (CustomException e){
                return  JsonUtil.error(e.getMessage());
            }
    }


    @ApiOperation(value = "修改用户")
    @LogOperation(code = "2",name = "修改用户",description = "修改用户")
    @PostMapping("/update")
    @ResponseBody
    public String update(@ApiParam(value = "用户")  @RequestBody SysUser item) throws Exception {
        try{
            SysUser user= sysUserServiceImpl.selectByPrimaryKey(item.getId());
            if(!user.getPassword().equals(item.getPassword())){
                item.setPassword( Md5Util.EncoderByMd5(item.getPassword()));
            }
            if(sysUserServiceImpl.updateByPrimaryKeySelective(item)<=0){
                throw  new CustomException("修改失败");
            }
            return  JsonUtil.sucess("成功",sysUserServiceImpl.selectByPrimaryKey(item.getId()));
        }catch (CustomException e){
            return  JsonUtil.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除用户")
    @LogOperation(code = "2",name = "删除用户",description = "删除用户")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@ApiParam(value = "用户")  @RequestParam String ids ) throws Exception {
        return JsonUtil.sucess("删除了"+sysUserServiceImpl.deleteByIds(ids)+"个数据。",null);
    }


//-- -----------------微信登录

    @ApiOperation(value = "首次授权登录方法")
    //@LogOperation(code = "1",name = "登录日志",description = "登录")
    @PostMapping("/authlogin")
    @ResponseBody
    public String authlogin(@NotNull(message = "账号不能为空")  @RequestParam String username,
                            @NotNull(message = "密码不能为空")  @RequestParam String password,
                            @RequestParam("code") String code,
                            HttpServletRequest request) throws Exception {
        Subject subject = SecurityUtils.getSubject();

        try {
            //将用户请求参数封装后，直接提交给Shiro处理
            UsernamePasswordToken token = new UsernamePasswordToken(username, Md5Util.EncoderByMd5(password));
            subject.login(token);
            //Shiro认证通过后会将user信息放到subject内，生成token并返回
            SysUser user=(SysUser)subject.getSession().getAttribute("user");
            if(user.getDelFlag()==null||user.getDelFlag()!=0){

                throw new CustomException("该用户被禁用");
            }
            user.setToken(subject.getSession().getId().toString());
            user.setPassword(null);
            SysLog requestLog=new SysLog();
            //日志记录学生日常操作
            requestLog.setCodeName("1");
            requestLog.setLogName("首次授权登录日志");
            requestLog.setContent("登录");
            requestLog.setUserId(user==null?"":user.getId());
            requestLog.setUserName(user==null?"":user.getRealName());
            requestLog.setIp(CusAccessObjectUtil.getIpAddress(request));
            requestLog.setCreateTime(new Date());
            Long startTime=System.currentTimeMillis();
            requestLog.setClassName("SysUserController");
            requestLog.setMethodName("authlogin");
            sysLogServiceImpl.insertLog(requestLog);

            // 配置请求参数
            /*Map<String, String> param = new HashMap<>();
            param.put("appid", UserConstantInterface.WX_LOGIN_APPID);
            param.put("secret", UserConstantInterface.WX_LOGIN_SECRET);
            param.put("js_code", code);
            param.put("grant_type", UserConstantInterface.WX_LOGIN_GRANT_TYPE);
            // 发送请求
            String wxResult = HttpClientUtil.doGet(UserConstantInterface.WX_LOGIN_URL, param);
            JSONObject jsonObject = JSONObject.parseObject(wxResult);
            // 获取参数返回的
            String session_key = jsonObject.get("session_key").toString();
            String open_id = jsonObject.get("openid").toString();

            //System.out.println("jsonObject: "+jsonObject);
            System.out.println("--------------session_key: "+session_key);*/
            System.out.println("--------------open_id: "+code);

            SysUser nUser = new SysUser();
            nUser.setId(user.getId());
            nUser.setWxBindOne(code);
            //sysUserServiceImpl.updateOpenidNull(code);                  //消除上个账号openid
            sysUserServiceImpl.updateByPrimaryKeySelective(nUser);      //绑定openid

            return JsonUtil.sucess("授权绑定账号成功，登录成功!",user);
        } catch (CustomException e){
            return JsonUtil.error("用户已禁用");
        }catch (AuthenticationException e) {
            // 如果校验失败，shiro会抛出异常，返回客户端失败
            // 日志记录
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            //throw new Exception("密码错误");
            return JsonUtil.error("账户名或密码错误");
        } catch (Exception e) {
            // 日志记录
            throw new Exception("登录失败");
            //return JSON.toJSONString(JsonUtil.error("登录失败"));
        }
    }

    @ApiOperation(value = "授权退出登录")
    @PostMapping("/authlogout")
    @ResponseBody
    public String authlogout() throws Exception {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            SysUser user=(SysUser)currentUser.getSession().getAttribute("user");
            sysUserServiceImpl.updateOpenidNull(user.getWxBindOne());        //消除账号openid
            currentUser.logout();
            return  JsonUtil.sucess("退出登录成功",null);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            // 日志记录
            throw new Exception("退出登录失败");
        }
    }

    @ApiOperation(value = "授权登录")
    @PostMapping("/wxLogin")
    public JsonUtil wxLogin(@RequestParam("code") String code,HttpServletRequest request) throws Exception {
        // 配置请求参数
        Map<String, String> param = new HashMap<>();
        param.put("appid", UserConstantInterface.WX_LOGIN_APPID);
        param.put("secret", UserConstantInterface.WX_LOGIN_SECRET);
        param.put("js_code", code);
        param.put("grant_type", UserConstantInterface.WX_LOGIN_GRANT_TYPE);
        // 发送请求
        String wxResult = HttpClientUtil.doGet(UserConstantInterface.WX_LOGIN_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        /*Map<String, String> result = new HashMap<>();
        result.put("session_key", session_key);
        result.put("open_id", open_id);*/
        //System.out.println("jsonObject: "+jsonObject);
        System.out.println("session_key: "+session_key);
        System.out.println("open_id: "+open_id);
        try {
            SysUser suser = new SysUser();
            suser.setWxBindOne(open_id);
            SysUser users =sysUserServiceImpl.selectOneByCon(suser);
            if (users!=null){
                //已授权登录过

                Subject subject = SecurityUtils.getSubject();
                //将用户请求参数封装后，直接提交给Shiro处理
                String username=users.getUsername();
                String password=users.getPassword();
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);
                //Shiro认证通过后会将user信息放到subject内，生成token并返回
                SysUser user=(SysUser)subject.getSession().getAttribute("user");
                if(user.getDelFlag()==null||user.getDelFlag()!=0){

                    throw new CustomException("该用户被禁用");
                }
                user.setToken(subject.getSession().getId().toString());
                user.setPassword(null);
                SysLog requestLog=new SysLog();
                //日志记录学生日常操作
                requestLog.setCodeName("1");
                requestLog.setLogName("授权登录日志");
                requestLog.setContent("授权登录");
                requestLog.setUserId(user==null?"":user.getId());
                requestLog.setUserName(user==null?"":user.getRealName());
                requestLog.setIp(CusAccessObjectUtil.getIpAddress(request));
                requestLog.setCreateTime(new Date());
                Long startTime=System.currentTimeMillis();
                requestLog.setClassName("SysUserController");
                requestLog.setMethodName("callBack");
                sysLogServiceImpl.insertLog(requestLog);

                return new JsonUtil("1", "已授权绑定账号，自动登录!",user);
                //return JsonUtil.sucess("已授权绑定账号，自动登录!",1);
            }else{
                //未授权第一次登录
                return new JsonUtil("0", "未授权绑定账号，请登录!",open_id);
                //return new JsonUtil.sucess("未授权绑定账号，请登录!",0);
            }
        }catch (CustomException e){
            return new JsonUtil("500","用户已禁用!",null,null,0);
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("出现了意外的错误。");
        }
    }







}
