package diana.maven.project.shiro;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Suna
 * @since 2020/5/6 15:08
 */
public class ShiroExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        if(ex instanceof UnauthorizedException) {
            mv.addObject("code","2001");
            mv.addObject("message","您没有此权限");
        }else {
            mv.addObject("code","9001");
            mv.addObject("message","未知错误");
        }
        return mv;
    }
}
