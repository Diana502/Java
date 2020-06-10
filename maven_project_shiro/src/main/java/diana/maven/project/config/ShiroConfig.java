package diana.maven.project.config;

import diana.maven.project.jwt.JwtFilter;
import diana.maven.project.shiro.MyRealm;
import diana.maven.project.shiro.ShiroExceptionResolver;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
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

        //添加自定义过滤器，并取名jwt
        LinkedHashMap<String, Filter> filterLinkedHashMap = new LinkedHashMap<>();
        filterLinkedHashMap.put("jwt",new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterLinkedHashMap);

        //配置访问权限，其中authc指定需要认证的uri，anon指定排除认证的uri
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/static/bootstrap/**", "anon");
        filterChainDefinitionMap.put("/role/getRoles", "anon");
        filterChainDefinitionMap.put("/login/kaptcha", "anon");
        filterChainDefinitionMap.put("/login/login", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/**","jwt");


        //配置登录的url和登录成功的url以及验证失败的url
        shiroFilterFactoryBean.setLoginUrl("/static/html/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/static/html/home.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/static/html/403.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

//    public JwtFilter jwtFilter() {
//        return new JwtFilter();
//    }

    @Bean
    SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());

        //关闭shiro自带的session，该项目是整合jwt时需要
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    @Bean
    MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        //不设置，默认明文验证，但由于本项目使用token且token里的密码是密文，所以可以就用默认设置
//        myRealm.setCredentialsMatcher(credentialsMatcher());
        return myRealm;
    }


    //设置加密算法为MD5
//    @Bean
//    HashedCredentialsMatcher credentialsMatcher() {
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
//        credentialsMatcher.setHashIterations(1);  //MD5加密次数  如果是2就相当于MD5(MD5(source))
//        return credentialsMatcher;
//    }

    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
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
     *
     * @return
     */
    @Bean
    public ShiroExceptionResolver shiroExceptionResolver() {
        return new ShiroExceptionResolver();
    }
}
