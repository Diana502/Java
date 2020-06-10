package diana.maven.project.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author Suna
 * @Date 2020/6/9 13:11
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
