package diana.maven.project.service;

import diana.maven.project.model.PlatformRole;
import diana.maven.project.model.PlatformRoleUser;

import java.util.List;


/**
 * @author Suna
 * @since 2020/4/29 11:24
 */
public interface RoleService {

    /**
     * 该用户是否拥有该角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    PlatformRoleUser hasRole(Integer userId, Integer roleId);

    /**
     * 获取所有角色信息
     *
     * @return
     */
    List<PlatformRole> getRole();

    /**
     * 根据角色英文名称获取角色信息
     *
     * @return
     */
    PlatformRole getRole(String roleName);
}
