package diana.maven.project.controller;

import diana.maven.project.common.R;
import diana.maven.project.model.PlatformRole;
import diana.maven.project.service.RoleService;
import diana.maven.project.util.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Suna
 * @since 2020/4/30 15:27
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    private static final Log log = LogFactory.getLog(RoleController.class);

    @PostMapping(value = "/getRoles", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public R getRoles() {
        List<PlatformRole> roleList = new ArrayList<>();
        try {
            roleList = roleService.getRole();
            return R.ok(roleList);
        } catch (Exception e) {
            log.error(ExceptionUtils.toString(e));
            return R.error(9001, "未知错误");
        }
    }

}
