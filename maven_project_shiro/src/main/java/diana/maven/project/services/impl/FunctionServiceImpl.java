package diana.maven.project.services.impl;

import diana.maven.project.mappers.FunctionMapper;
import diana.maven.project.models.Function;
import diana.maven.project.models.PermissionService;
import diana.maven.project.models.PlatformRoleUser;
import diana.maven.project.models.PlatformRoleUserExample;
import diana.maven.project.services.FunctionService;
import diana.maven.project.utils.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Suna
 * @since 2020/4/29 16:06
 */
@Service
public class FunctionServiceImpl implements FunctionService {

    @Resource
    FunctionMapper functionMapper;

    private static Log log = LogFactory.getLog(FunctionServiceImpl.class);

    @Override
    public List<Function> getFunctionByRoleId(Integer roleId) {
        List<Function> functionList = new ArrayList<>();
        try{
            PermissionService permission = new PermissionService();
            permission.setRoleId(roleId);
            functionList = functionMapper.getFunctionByRoleId(permission);
        }catch (Exception e){
            log.error(ExceptionUtil.toString(e));
        }
        return functionList;
    }
}
