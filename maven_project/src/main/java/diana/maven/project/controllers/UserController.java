package diana.maven.project.controllers;

import diana.maven.project.common.R;
import diana.maven.project.models.PlatformUser;
import diana.maven.project.services.UserService;
import diana.maven.project.services.impl.UserServiceImpl;
import diana.maven.project.utils.ExceptionUtil;
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
 * @since 2020/4/28 12:58
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    private static Log log = LogFactory.getLog(UserController.class);

    @PostMapping(value = "/getAllUser",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public R getAllUser(){
        List<PlatformUser> userList = new ArrayList<>();
        try{
            userList = userService.selectAllPlaformUser();
            log.error("user getAllUser：获取所有注册用户信息成功");
            return R.ok(userList);
        }catch (Exception e){
            log.error("user getAllUser：获取所有注册用户信息失败\n"+ ExceptionUtil.toString(e));
            return R.error(9001,"未知错误");
        }
    }
}
