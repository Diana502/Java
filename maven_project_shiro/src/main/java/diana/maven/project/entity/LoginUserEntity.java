package diana.maven.project.entity;

import diana.maven.project.model.PlatformRole;

/**
 * @author Suna
 * @since 2020/4/29 15:39
 */
public class LoginUserEntity {

    private Integer userId;//用户编号
    private String loginName;//登录名
    private String password;//密码
    private String kaptcha;//验证码
    private PlatformRole role;//登录角色

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }

    public PlatformRole getRole() {
        return role;
    }

    public void setRole(PlatformRole role) {
        this.role = role;
    }
}
