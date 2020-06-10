package diana.maven.project.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * @Author Suna
 * @Date 2020/6/9 13:05
 */
public class JwtManager {

    private static Log log = LogFactory.getLog(JwtManager.class);

    private static final long EXPIRE_TIME = 5 * 60 * 1000;//过期时间5min

    /**
     * 校验token是否正确
     *
     * @param token    秘钥
     * @param username 用户名
     * @param secret   用户密码
     * @return
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            //根据密码生成JWT校验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim("username", username).build();
            //校验token
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            log.info("The jwtToken is valid : " + decodedJWT);
            return true;
        } catch (Exception e) {
            log.info("The jwtToken is invalid : " + e.getMessage());
            return false;
        }
    }

    /**
     * 获取用户名
     *
     * @param token 秘钥
     * @return
     */
    public static String getUsername(String token) {
        try {
            if (token == null || "".equals(token)) {
                log.info("The token is null");
                return null;
            }
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            log.error("Error decoding this token :"+token);
            return null;
        }
    }

    public static String getRoleName(String token){
        try {
            if (token == null || "".equals(token)) {
                log.info("The token is null");
                return null;
            }
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("roleName").asString();
        } catch (JWTDecodeException e) {
            log.error("Error decoding this token :"+token);
            return null;
        }
    }

    public static boolean getRememberMe(String token){
        try {
            if (token == null || "".equals(token)) {
                log.info("The token is null");
                return false;
            }
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("rememberMe").asBoolean();
        } catch (JWTDecodeException e) {
            log.error("Error decoding this token :"+token);
            return false;
        }
    }

    /**
     * 生成签名，5min后过期
     *
     * @param username
     * @param secret
     * @return
     */
    public static String sign(String username, String secret,String roleName,boolean rememberMe) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            //附带username信息
            String token = JWT.create()
                    .withClaim("username",username)
                    .withClaim("roleName",roleName)
                    .withClaim("rememberMe",rememberMe)
                    .withExpiresAt(date)
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            return null;
        }
    }

}
