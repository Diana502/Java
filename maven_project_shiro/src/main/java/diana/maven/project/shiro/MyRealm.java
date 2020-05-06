package diana.maven.project.shiro;

import diana.maven.project.entity.LoginUserEntity;
import diana.maven.project.models.Function;
import diana.maven.project.models.PlatformRoleUser;
import diana.maven.project.models.PlatformUser;
import diana.maven.project.services.FunctionService;
import diana.maven.project.services.RoleService;
import diana.maven.project.services.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Suna
 * @since 2020/4/29 8:55
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    FunctionService functionService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登录对象的信息
        LoginUserEntity loginUser = (LoginUserEntity) principals.getPrimaryPrincipal();
        //为该用户设置角色
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(loginUser.getRole().getRoleName());
        //获取数据库中该登录角色所拥有的权限
        List<Function> functionList = functionService.getFunctionByRoleId(loginUser.getRole().getId());
        //为该用户设置权限
        for (Function func:functionList) {
            simpleAuthorizationInfo.addStringPermission(func.getFunctionCode());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //用户传递的登录参数
        LoginToken loginToken = (LoginToken) token;
        //验证账号是否存在
        String userName = loginToken.getUsername();
        PlatformUser platformUser = userService.selectPlaformUserByLoginName(userName);
        if(null == platformUser){
            throw new UnknownAccountException("未知用户");
        }
        //验证该用户是否被锁定
        if(Boolean.TRUE.equals(platformUser.getLocked())){
            throw new LockedAccountException("用户被锁定");
        }
        //验证用户是否有该角色
        Integer roleId = loginToken.getRole().getId();
        PlatformRoleUser userRole = roleService.hasRole(platformUser.getId(),roleId);
        if(null == userRole){
            throw new UnknownAccountException("该用户无"+loginToken.getRole().getDisplayRoleName()+"角色");
        }
        //将信息封装成登录用户信息
        LoginUserEntity loginUserEntity = new LoginUserEntity();
        loginUserEntity.setLoginName(platformUser.getLoginName());
        loginUserEntity.setPassword(platformUser.getPassword());
        loginUserEntity.setUserId(platformUser.getId());
        loginUserEntity.setRole(loginToken.getRole());
        //验证密码
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                loginUserEntity,   //数据库用户对象
                platformUser.getPassword(),   //数据库中保存的密码
                getName());                   //获取用户名和密码的类名
        return simpleAuthenticationInfo;
    }
}
