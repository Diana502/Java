package diana.maven.project.service.impl;

import diana.maven.project.mapper.PlatformRoleMapper;
import diana.maven.project.mapper.PlatformRoleUserMapper;
import diana.maven.project.model.PlatformRole;
import diana.maven.project.model.PlatformRoleExample;
import diana.maven.project.model.PlatformRoleUser;
import diana.maven.project.model.PlatformRoleUserExample;
import diana.maven.project.service.RoleService;
import diana.maven.project.util.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Suna
 * @since 2020/4/29 11:25
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    PlatformRoleUserMapper roleUserMapper;
    @Autowired
    PlatformRoleMapper roleMapper;

    private static Log log = LogFactory.getLog(RoleServiceImpl.class);

    @Override
    public PlatformRoleUser hasRole(Integer userId, Integer roleId) {
        PlatformRoleUser roleUser = new PlatformRoleUser();
        try {
            PlatformRoleUserExample platformRoleUserExample = new PlatformRoleUserExample();
            platformRoleUserExample.createCriteria().andUserIdEqualTo(userId).andRoleIdEqualTo(roleId);
            roleUser = roleUserMapper.selectByExample(platformRoleUserExample).get(0);
        } catch (Exception e) {
            log.error(ExceptionUtils.toString(e));
            return null;
        }
        return roleUser;
    }

    @Override
    public List<PlatformRole> getRole() {
        List<PlatformRole> roleList = new ArrayList<>();
        try {
            PlatformRoleExample roleExample = new PlatformRoleExample();
            roleList = roleMapper.selectByExample(roleExample);
        } catch (Exception e) {
            log.error(ExceptionUtils.toString(e));
        }
        return roleList;
    }

    @Override
    public PlatformRole getRole(String roleName) {
        PlatformRole role = new PlatformRole();
        try {
            PlatformRoleExample roleExample = new PlatformRoleExample();
            roleExample.createCriteria().andRoleNameEqualTo(roleName);
            role = roleMapper.selectByExample(roleExample).get(0);
        } catch (Exception e) {
            log.error(ExceptionUtils.toString(e));
            return null;
        }
        return role;
    }
}
