package net.yto.newmysql.contrllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.yto.newmysql.models.StudentEntity;
import net.yto.newmysql.services.StudentService;

@RestController
public class StudentController {
	
	private static final Log LOG = LogFactory.getLog(StudentController.class);
	
	@Autowired
	StudentService studentService;
	
	/** 
	 * 无条件获取所有学生信息
	 * @return
	 */
	@RequestMapping("getAllStudent")
	List<StudentEntity> getAllStudent(){
		
		List<StudentEntity> stuentList = null;
		
		try {
			stuentList = studentService.getAllStudent();
		} catch (Exception e) {
			LOG.error(e.getMessage()+"获取所有学生信息失败！");
		}
		
		return stuentList;
	}
	
}
