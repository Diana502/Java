package diana.maven.project.services;

import diana.maven.project.models.Function;
import diana.maven.project.models.PermissionService;

import java.util.List;

/**
 * @author Suna
 * @since 2020/4/29 16:06
 */
public interface FunctionService {

    /**
     * 获取某角色所拥有的所有权限
     * @param roleId
     * @return
     */
    List<Function> getFunctionByRoleId(Integer roleId);
}
