package spring.config.shiro;

import spring.config.filter.CORSAuthenticationFilter;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.mgt.SecurityManager;


import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;



@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new MyRealm();
    }

    @Bean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        //System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager sm = new DefaultWebSecurityManager();
        sm.setCacheManager(cacheManager());
        //注入记住我管理器
        sm.setRememberMeManager(rememberMeManager());
        //注入自定义sessionManager
        sm.setSessionManager(sessionManager());
        sm.setRealm(realm());

        return sm;
    }

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        return new CustomSessionManager();
    }

    public CORSAuthenticationFilter corsAuthenticationFilter(){
        return new CORSAuthenticationFilter();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //SecurityUtils.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置不会被拦截的链接，顺序判断
        filterChainDefinitionMap.put("/", "anon");


        filterChainDefinitionMap.put("/static/js/**", "anon");
        filterChainDefinitionMap.put("/static/css/**", "anon");
        filterChainDefinitionMap.put("/static/fonts/**", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/corp/call_back/receive", "anon");
        //swagger页面
        //swagger-resources/configuration/ui 700
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");


        //文件上传
        filterChainDefinitionMap.put("/uploadfile/**", "anon");
        filterChainDefinitionMap.put("/uploadfiles/**", "anon");
        //文件下载
        filterChainDefinitionMap.put("/downloadfile/**", "anon");
        filterChainDefinitionMap.put("/selectfile/**", "anon");

        //微信授权回调页面域名
        filterChainDefinitionMap.put("/MP_verify_YAq14Ih3g7lehAFF.txt", "anon");

        filterChainDefinitionMap.put("/cbLogin", "anon");
        filterChainDefinitionMap.put("/callBack", "anon");
        filterChainDefinitionMap.put("/wxLogin", "anon");
        filterChainDefinitionMap.put("/authlogin", "anon");

        //首页接口
        filterChainDefinitionMap.put("/notice/getonenotice", "anon");
        filterChainDefinitionMap.put("/homepage/**", "anon");
        filterChainDefinitionMap.put("/activity/getoneactivity", "anon");
        filterChainDefinitionMap.put("/btqres/getonebtqres", "anon");

        //微信授权相关接口
        filterChainDefinitionMap.put("/user/wxLogin", "anon");
        filterChainDefinitionMap.put("/user/authlogin", "anon");
        filterChainDefinitionMap.put("/user/authlogout", "anon");

        //测试websocket
        filterChainDefinitionMap.put("/style.css", "anon");
        filterChainDefinitionMap.put("/js/*", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/index.html", "anon");
        filterChainDefinitionMap.put("/groupcall", "anon");
        filterChainDefinitionMap.put("/groupcall/**", "anon");
        filterChainDefinitionMap.put("/websocket/**", "anon");
        filterChainDefinitionMap.put("/test.html", "anon");
        filterChainDefinitionMap.put("/jquery-3.4.1.js", "anon");

        //activiti
        filterChainDefinitionMap.put("/service/**", "anon");
        filterChainDefinitionMap.put("/models/**", "anon");
        filterChainDefinitionMap.put("/editor", "anon");
        filterChainDefinitionMap.put("/modeler.html", "anon");
        filterChainDefinitionMap.put("/editor-app/**", "anon");
        filterChainDefinitionMap.put("/diagram-viewer/**", "anon");

        //为了项目运行方便一点先关了shiro拦截
        //filterChainDefinitionMap.put("/*/**", "anon");

        //退出登录
        filterChainDefinitionMap.put("/logout", "anon");
        //authc:所有url必须通过认证才能访问，anon:所有url都可以匿名访问
        filterChainDefinitionMap.put("/**", "corsAuthenticationFilter");

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //自定义过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("corsAuthenticationFilter", corsAuthenticationFilter());
        shiroFilter.setFilters(filterMap);
        return shiroFilter;
    }

    /**
     * Shiro生命周期处理器 * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能 * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

