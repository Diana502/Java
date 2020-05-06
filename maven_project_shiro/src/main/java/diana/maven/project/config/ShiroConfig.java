package diana.maven.project.config;

import diana.maven.project.shiro.MyRealm;
import diana.maven.project.shiro.ShiroExceptionResolver;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Suna
 * @since 2020/4/29 8:42
 */
@Configuration
public class ShiroConfig {

    @Bean
    ShiroFilterFactoryBean shiroFilter() {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        //配置访问权限，其中authc指定需要认证的uri，anon指定排除认证的uri
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/static/bootstrap/**", "anon");
        filterChainDefinitionMap.put("/role/getRoles", "anon");
        filterChainDefinitionMap.put("/login/kaptcha", "anon");
        filterChainDefinitionMap.put("/login/login", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");

        //配置登录的url和登录成功的url以及验证失败的url
        shiroFilterFactoryBean.setLoginUrl("/static/html/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/static/html/index.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/static/html/403.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean
    MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher());
        return myRealm;
    }

    @Bean  //设置加密算法为MD5
    HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        credentialsMatcher.setHashIterations(1);  //MD5加密次数  如果是2就相当于MD5(MD5(source))
        return credentialsMatcher;
    }

    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 自定义shiro权限异常过滤器
     * @return
     */
    @Bean
    public ShiroExceptionResolver shiroExceptionResolver(){
        return new ShiroExceptionResolver();
    }
}
