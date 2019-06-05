package net.yto.newmysql.services;

import java.util.List;

import net.yto.newmysql.models.StudentEntity;


public interface StudentService {
	
	/**
	 * 获取所有学生的信息
	 * @return
	 */
	List<StudentEntity> getAllStudent();

}
