package diana.maven.project.jwt;

import diana.maven.project.util.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author Suna
 * @Date 2020/6/9 13:02
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private static Log log = LogFactory.getLog(JwtFilter.class);

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (!isLoginAttempt(request, response)) {//不是登录请求才会带token
            try {
                //执行登录方法，验证token
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                //token出错
                log.error(ExceptionUtils.toString(e));
                throw new AuthorizationException("权限不足", e);
            }
        }
        //游客请求
        return true;
    }

    /**
     * 通过检测headers里是否包含token，来判断用户请求是否是登录请求
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if(antPathMatcher.match("/login/login", httpServletRequest.getRequestURI())){
            return true;
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String islogin = httpRequest.getHeader(JwtConsts.LOGIN_SIGN);
        if(islogin != null){//有token，不是登录请求
            return false;
        }
        return true;//无token，可能是登录请求
    }

    /**
     * 执行登录方法
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader(JwtConsts.LOGIN_SIGN);
        JwtToken jwtToken = new JwtToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }
}
