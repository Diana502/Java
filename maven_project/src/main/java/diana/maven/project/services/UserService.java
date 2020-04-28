package diana.maven.project.services;

import diana.maven.project.models.PlatformUser;

import java.util.List;

/**
 * @author Suna
 * @since 2020/4/28 12:48
 */
public interface UserService {

    /**
     * 获取所有注册用户信息
     * @return
     */
    List<PlatformUser> selectAllPlaformUser();
}
