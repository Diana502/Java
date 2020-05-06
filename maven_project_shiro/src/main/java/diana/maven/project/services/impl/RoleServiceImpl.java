package diana.maven.project.services.impl;

import diana.maven.project.mappers.PlatformRoleMapper;
import diana.maven.project.mappers.PlatformRoleUserMapper;
import diana.maven.project.models.PlatformRole;
import diana.maven.project.models.PlatformRoleExample;
import diana.maven.project.models.PlatformRoleUser;
import diana.maven.project.models.PlatformRoleUserExample;
import diana.maven.project.services.RoleService;
import diana.maven.project.utils.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Suna
 * @since 2020/4/29 11:25
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    PlatformRoleUserMapper roleUserMapper;
    @Resource
    PlatformRoleMapper roleMapper;

    private static Log log = LogFactory.getLog(RoleServiceImpl.class);

    @Override
    public PlatformRoleUser hasRole(Integer userId, Integer roleId) {
        PlatformRoleUser roleUser = new PlatformRoleUser();
        try{
            PlatformRoleUserExample platformRoleUserExample = new PlatformRoleUserExample();
            platformRoleUserExample.createCriteria().andUserIdEqualTo(userId).andRoleIdEqualTo(roleId);
            roleUser = roleUserMapper.selectByExample(platformRoleUserExample).get(0);
        }catch (Exception e){
            log.error(ExceptionUtil.toString(e));
            return null;
        }
        return roleUser;
    }

    @Override
    public List<PlatformRole> getRole() {
        List<PlatformRole> roleList = new ArrayList<>();
        try{
            PlatformRoleExample roleExample = new PlatformRoleExample();
            roleList = roleMapper.selectByExample(roleExample);
        }catch (Exception e){
            log.error(ExceptionUtil.toString(e));
        }
        return roleList;
    }

    @Override
    public PlatformRole getRole(String roleName) {
        PlatformRole role = new PlatformRole();
        try{
            PlatformRoleExample roleExample = new PlatformRoleExample();
            roleExample.createCriteria().andRoleNameEqualTo(roleName);
            role = roleMapper.selectByExample(roleExample).get(0);
        }catch (Exception e){
            log.error(ExceptionUtil.toString(e));
            return null;
        }
        return role;
    }
}
