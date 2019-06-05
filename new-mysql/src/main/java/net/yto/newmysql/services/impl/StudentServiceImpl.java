package net.yto.newmysql.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.yto.newmysql.mappers.StudentEntityMapper;
import net.yto.newmysql.models.StudentEntity;
import net.yto.newmysql.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentEntityMapper studentEntityMapper;
	
	@Override
	public List<StudentEntity> getAllStudent() {
		List<StudentEntity> studentList = studentEntityMapper.getAllStudent();
		return studentList;
	}

}
