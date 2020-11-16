package spring.config.shiro;


import spring.entity.SysUser;
import spring.exception.CustomException;
import spring.service.SysRoleService;
import spring.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashSet;
import java.util.Set;

/**
 * 自定义realm
 * @author shengwu ni
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private LoginService userService;

    @Autowired
    SysRoleService sysRoleServiceImpl;


    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户名
        String  username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 给该用户设置角色，角色信息存在 t_role 表中取
        Set<String> s =new HashSet<String>();
        SysUser user = userService.selectByUsername(username);
        s.add(user.getPositionId());
       /* List<SysRole> roleList=  sysRoleServiceImpl.selectByUsername(username);
        for (SysRole roles: roleList ) {
            s.add(roles.getRoleCode());
        }*/
        Set<String> s2=new HashSet<String>();
        //s2.add("测试权限1");
        //s2.add("测试权限2");

        authorizationInfo.setRoles(s);//这里也忽悠一下系统
        // 给该用户设置权限，权限信息存在 t_permission 表中取
        authorizationInfo.setStringPermissions(s2);//这里也忽悠一下系统
        return authorizationInfo;
    }


    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws CustomException {
        // 根据 Token 获取用户名，如果您不知道该 Token 怎么来的，先可以不管，下文会解释
        String username = (String) authenticationToken.getPrincipal();
        // 根据用户名从数据库中查询该用户
        SysUser user = userService.selectByUsername(username);

        //if(user != null && user.getDelFlag()!=null&&user.getDelFlag()==0){
        if(user != null){
            // 把当前用户存到 Session 中
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            // 传入用户名和密码进行身份认证，并返回认证信息
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "myRealm");
            return authcInfo;
        } else {
            return null;
        }
    }
}

