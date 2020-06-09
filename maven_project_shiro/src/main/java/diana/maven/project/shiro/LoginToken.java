package diana.maven.project.shiro;

import diana.maven.project.model.PlatformRole;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author Suna
 * @since 2020/4/29 14:54
 */
public class LoginToken extends UsernamePasswordToken {

    private PlatformRole role;//角色对象

    public LoginToken(String userName, String password, PlatformRole role) {
        super(userName, password);
        this.role = role;
    }

    public PlatformRole getRole() {
        return role;
    }

    public void setRole(PlatformRole role) {
        this.role = role;
    }
}
