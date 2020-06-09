package diana.maven.project.controller;

import com.google.code.kaptcha.Producer;
import diana.maven.project.common.R;
import diana.maven.project.model.PlatformRole;
import diana.maven.project.service.RoleService;
import diana.maven.project.shiro.LoginToken;
import diana.maven.project.util.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @author Suna
 * @since 2020/4/29 9:19
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    Producer captchaProducer;
    @Autowired
    RoleService roleService;

    private static Log log = LogFactory.getLog(LoginController.class);

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/kaptcha")
    public void getKaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setDateHeader("Expires", 0);
            // 告诉浏览器不要缓存,防止生成同样的验证码图片
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            // 返回信息为jpg
            response.setContentType("image/jpeg");
            // 生成验证码
            String capText = captchaProducer.createText();
            request.getSession().setAttribute("kaptcha", capText);
            log.info("获取验证码|" + capText);
            BufferedImage bufferedImage = captchaProducer.createImage(capText);
            // 将图片写到response中返回
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            log.error("获取验证码失败" + e.getMessage());
        }
    }

    @PostMapping(value = "/login")
    public R login(HttpServletRequest request) {
        String loginName = request.getParameter("loginName");
        String inputKaptcha = request.getParameter("kaptcha");
        String sessionKaptcha = (String) request.getSession().getAttribute("kaptcha");
        if (!inputKaptcha.equals(sessionKaptcha)) {
            return R.error(2001, "验证码错误");
        }

        String password = request.getParameter("password");
        String roleName = request.getParameter("roleName");
        boolean rememberMe = Boolean.valueOf(request.getParameter("rememberMe"));
        PlatformRole role = roleService.getRole(roleName);
        LoginToken loginToken = new LoginToken(loginName, password, role);
        loginToken.setRememberMe(rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(loginToken);
        } catch (UnknownAccountException e) {
            log.error(ExceptionUtils.toString(e));
            return R.error(2002, e.getMessage());
        } catch (LockedAccountException e) {
            log.error(ExceptionUtils.toString(e));
            return R.error(2002, e.getMessage());
        } catch (Exception e) {
            log.error(ExceptionUtils.toString(e));
            return R.error(2002, "未知错误");
        }
        return R.ok("登录成功");
    }
}
