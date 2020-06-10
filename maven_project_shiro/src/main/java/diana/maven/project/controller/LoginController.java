package diana.maven.project.controller;

import com.google.code.kaptcha.Producer;
import diana.maven.project.common.R;
import diana.maven.project.jwt.JwtManager;
import diana.maven.project.model.PlatformRole;
import diana.maven.project.model.PlatformRoleUser;
import diana.maven.project.model.PlatformUser;
import diana.maven.project.service.RoleService;
import diana.maven.project.service.UserService;
import diana.maven.project.util.Md5Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    @Autowired
    UserService userService;

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
        String inputKaptcha = request.getParameter("kaptcha");
        String sessionKaptcha = (String) request.getSession().getAttribute("kaptcha");
        if (!inputKaptcha.equals(sessionKaptcha)) {
            return R.error(2001, "验证码错误");
        }
        //验证账号是否存在
        String loginName = request.getParameter("loginName");
        PlatformUser platformUser = userService.selectPlaformUserByLoginName(loginName);
        if (null == platformUser) {
            return R.error(2002, "未知用户");
        }
        //验证该用户是否被锁定
        if (Boolean.TRUE.equals(platformUser.getLocked())) {
            return R.error(2003, "账号被锁定");
        }
        //验证用户是否有该角色
        String roleName = request.getParameter("roleName");
        PlatformRole role = roleService.getRole(roleName);
        Integer roleId = role.getId();
        PlatformRoleUser userRole = roleService.hasRole(platformUser.getId(), roleId);
        if (null == userRole) {
            return R.error(2004, "该用户无" + role.getDisplayRoleName() + "角色");
        }
        String password = request.getParameter("password");
        String pwdMd5 = Md5Utils.encrypt32(password);
        if(!platformUser.getPassword().equals(pwdMd5)){
            return R.error(2005, "用户密码错误");
        }
        boolean rememberMe = Boolean.valueOf(request.getParameter("rememberMe"));
        String token = JwtManager.sign(loginName, pwdMd5, roleName,rememberMe);
        log.info(token);
        return R.ok(token);
    }
}
