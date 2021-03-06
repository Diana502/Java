package diana.maven.project.services;

import diana.maven.project.models.PlatformUser;


/**
 * @author Suna
 * @since 2020/4/28 12:48
 */
public interface UserService {

    /**
     * 根据用户名判断用户是否是注册用户
     * @param loginName
     * @return
     */
    PlatformUser selectPlaformUserByLoginName(String loginName);

}
