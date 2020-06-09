package diana.maven.project.service.impl;

import diana.maven.project.mapper.PlatformUserMapper;
import diana.maven.project.model.PlatformUser;
import diana.maven.project.model.PlatformUserExample;
import diana.maven.project.service.UserService;
import diana.maven.project.util.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Suna
 * @since 2020/4/28 12:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PlatformUserMapper userMapper;

    private static Log log = LogFactory.getLog(UserServiceImpl.class);

    @Override
    public PlatformUser selectPlaformUserByLoginName(String loginName) {
        PlatformUser platformUser = new PlatformUser();
        try {
            PlatformUserExample platformUserExample = new PlatformUserExample();
            platformUserExample.createCriteria().andLoginNameEqualTo(loginName);
            platformUser = userMapper.selectByExample(platformUserExample).get(0);
        } catch (Exception e) {
            log.error(ExceptionUtils.toString(e));
            return null;
        }
        return platformUser;
    }

}
