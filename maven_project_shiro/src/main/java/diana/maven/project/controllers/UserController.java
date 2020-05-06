package diana.maven.project.controllers;

import diana.maven.project.common.R;
import diana.maven.project.utils.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Suna
 * @since 2020/4/28 12:58
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Log log = LogFactory.getLog(UserController.class);

    @PostMapping(value = "/addUser",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @RequiresPermissions("1001")
    public R addUser(){
        try{
            log.error("user addUser：添加用户成功");
            return R.ok("添加用户成功");
        }catch (Exception e){
            log.error("user addUser：添加用户失败\n"+ ExceptionUtil.toString(e));
            return R.error(9001,"未知错误");
        }
    }

    @PostMapping(value = "/updateUser",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @RequiresPermissions ("1002")
    public R updateUser(){
        try{
            log.error("user updateUser：修改用户成功");
            return R.ok("修改用户成功");
        }catch (Exception e){
            log.error("user updateUser：修改用户失败\n"+ ExceptionUtil.toString(e));
            return R.error(9001,"未知错误");
        }
    }

    @PostMapping(value = "/lockedUser",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @RequiresPermissions("1003")
    public R lockedUser(){
        try{
            log.error("user lockedUser：锁定用户成功");
            return R.ok("锁定用户成功");
        }catch (Exception e){
            log.error("user lockedUser：锁定用户失败\n"+ ExceptionUtil.toString(e));
            return R.error(9001,"未知错误");
        }
    }

    @PostMapping(value = "/resetUser",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @RequiresPermissions("1004")
    public R resetUser(){
        try{
            log.error("user resetUser：启用用户成功");
            return R.ok("启用用户成功");
        }catch (Exception e){
            log.error("user resetUser：启用用户失败\n"+ ExceptionUtil.toString(e));
            return R.error(9001,"未知错误");
        }
    }

    @PostMapping(value = "/selectUser",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public R selectUser(){
        try{
            log.error("user selectUser：查询用户成功");
            return R.ok("查询用户成功");
        }catch (Exception e){
            log.error("user selectUser：查询用户失败\n"+ ExceptionUtil.toString(e));
            return R.error(9001,"未知错误");
        }
    }

}
