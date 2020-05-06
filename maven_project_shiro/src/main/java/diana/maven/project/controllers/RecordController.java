package diana.maven.project.controllers;

import diana.maven.project.common.R;
import diana.maven.project.utils.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Suna
 * @since 2020/5/6 13:10
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    private static Log log = LogFactory.getLog(RecordController.class);

    @PostMapping(value = "/reportRecord",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @RequiresPermissions("2001")
    public R reportRecord(){
        try{
            log.error("record reportRecord：上报失信记录成功");
            return R.ok("上报失信记录成功");
        }catch (Exception e){
            log.error("record reportRecord：上报失信记录失败\n"+ ExceptionUtil.toString(e));
            return R.error(9001,"未知错误");
        }
    }

    @PostMapping(value = "/auditRecord",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @RequiresPermissions("2002")
    public R auditRecord(){
        try{
            log.error("record auditRecord：审核失信记录成功");
            return R.ok("审核失信记录成功");
        }catch (Exception e){
            log.error("record auditRecord：审核失信记录失败\n"+ ExceptionUtil.toString(e));
            return R.error(9001,"未知错误");
        }
    }

    @PostMapping(value = "/selectRecord",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @RequiresPermissions("2003")
    public R selectRecord(){
        try{
            log.error("record selectRecord：查询失信记录成功");
            return R.ok("查询失信记录成功");
        }catch (Exception e){
            log.error("record selectRecord：查询失信记录失败\n"+ ExceptionUtil.toString(e));
            return R.error(9001,"未知错误");
        }
    }

    @PostMapping(value = "/appealRecord",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @RequiresPermissions("2004")
    public R appealRecord(){
        try{
            log.error("record appealRecord：申诉失信记录成功");
            return R.ok("申诉失信记录成功");
        }catch (Exception e){
            log.error("record appealRecord：申诉失信记录失败\n"+ ExceptionUtil.toString(e));
            return R.error(9001,"未知错误");
        }
    }

    @PostMapping(value = "/repealRecord",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @RequiresPermissions("2005")
    public R repealRecord(){
        try{
            log.error("record repealRecord：撤销失信记录成功");
            return R.ok("撤销失信记录成功");
        }catch (Exception e){
            log.error("record repealRecord：撤销失信记录失败\n"+ ExceptionUtil.toString(e));
            return R.error(9001,"未知错误");
        }
    }
}
