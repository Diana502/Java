package diana.maven.project.service.impl;

import diana.maven.project.mapper.FunctionMapper;
import diana.maven.project.model.Function;
import diana.maven.project.model.PermissionService;
import diana.maven.project.service.FunctionService;
import diana.maven.project.util.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Suna
 * @since 2020/4/29 16:06
 */
@Service
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    FunctionMapper functionMapper;

    private static Log log = LogFactory.getLog(FunctionServiceImpl.class);

    @Override
    public List<Function> getFunctionByRoleId(Integer roleId) {
        List<Function> functionList = new ArrayList<>();
        try {
            PermissionService permission = new PermissionService();
            permission.setRoleId(roleId);
            functionList = functionMapper.getFunctionByRoleId(permission);
        } catch (Exception e) {
            log.error(ExceptionUtils.toString(e));
        }
        return functionList;
    }
}
