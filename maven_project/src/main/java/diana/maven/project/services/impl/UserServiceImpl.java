package diana.maven.project.services.impl;

import diana.maven.project.mappers.PlatformUserMapper;
import diana.maven.project.models.PlatformUser;
import diana.maven.project.models.PlatformUserExample;
import diana.maven.project.services.UserService;
import diana.maven.project.utils.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Suna
 * @since 2020/4/28 12:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    PlatformUserMapper userMapper;

    private static Log log = LogFactory.getLog(UserServiceImpl.class);

    @Override
    public List<PlatformUser> selectAllPlaformUser() {
        List<PlatformUser> platformUserList = new ArrayList<>();
        try{
            PlatformUserExample userExample = new PlatformUserExample();
            platformUserList = userMapper.selectByExample(userExample);
        }catch (Exception e){
            log.error(ExceptionUtil.toString(e));
        }
        return platformUserList;
    }
}
