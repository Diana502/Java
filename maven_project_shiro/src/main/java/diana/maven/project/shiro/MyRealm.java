package diana.maven.project.shiro;

import diana.maven.project.entity.LoginUserEntity;
import diana.maven.project.jwt.JwtManager;
import diana.maven.project.jwt.JwtToken;
import diana.maven.project.model.Function;
import diana.maven.project.model.PlatformRole;
import diana.maven.project.model.PlatformRoleUser;
import diana.maven.project.model.PlatformUser;
import diana.maven.project.service.FunctionService;
import diana.maven.project.service.RoleService;
import diana.maven.project.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
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

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登录对象的信息
        String token = principals.toString();
        String roleName = JwtManager.getRoleName(token);
        //为该用户设置角色
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(roleName);
        //获取数据库中该登录角色所拥有的权限
        PlatformRole role = roleService.getRole(roleName);
        Integer roleId = role.getId();
        List<Function> functionList = functionService.getFunctionByRoleId(roleId);
        //为该用户设置权限
        for (Function func : functionList) {
            simpleAuthorizationInfo.addStringPermission(func.getFunctionCode());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //用户传递的登录参数
        String loginToken = (String) token.getPrincipal();
        //验证账号是否存在
        String userName = JwtManager.getUsername(loginToken);
        PlatformUser platformUser = userService.selectPlaformUserByLoginName(userName);
        if (null == platformUser) {
            throw new UnknownAccountException("未知用户");
        }
        //验证该用户是否被锁定
        if (Boolean.TRUE.equals(platformUser.getLocked())) {
            throw new LockedAccountException("用户被锁定");
        }
        //验证密码
        if(!JwtManager.verify(loginToken,userName,platformUser.getPassword())){
            throw new UnknownAccountException("token认证失败");
        }
        //验证用户是否有该角色
        String roleName = JwtManager.getRoleName(loginToken);
        PlatformRole role = roleService.getRole(roleName);
        Integer roleId = role.getId();
        PlatformRoleUser userRole = roleService.hasRole(platformUser.getId(), roleId);
        if (null == userRole) {
            throw new UnknownAccountException("该用户无" + role.getDisplayRoleName() + "角色");
        }
        //将信息封装成登录用户信息
        LoginUserEntity loginUserEntity = new LoginUserEntity();
        loginUserEntity.setLoginName(platformUser.getLoginName());
        loginUserEntity.setPassword(platformUser.getPassword());
        loginUserEntity.setUserId(platformUser.getId());
        loginUserEntity.setRole(role);
        //验证密码
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                loginToken,   //数据库用户对象
                loginToken,   //数据库中保存的密码
                getName()); //获取用户名和密码的类名
        return simpleAuthenticationInfo;
    }
}
