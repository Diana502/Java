package diana.maven.project.service;

import diana.maven.project.model.Function;

import java.util.List;

/**
 * @author Suna
 * @since 2020/4/29 16:06
 */
public interface FunctionService {

    /**
     * 获取某角色所拥有的所有权限
     *
     * @param roleId
     * @return
     */
    List<Function> getFunctionByRoleId(Integer roleId);
}
