package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Filesend;
import com.example.demo.entity.Student;

public interface StudentService {

    List<Student> getAllStudents();
	
	Student saveStudent(Student student);
	
	Student getStudentById(Long id);
	
	
	
	Student updateStudent(Student student);
	
	List<Student> selectData(Student student);
	
	void deleteStudentById(Long id);
	
	
	List<Filesend> getAllFiles();
	
    Filesend saveFile(Filesend filesend);
	
    Filesend getFileById(Long id);
	
	
	
    Filesend updateFile(Filesend filesend);
	
	List<Filesend> selectData(Filesend ileSend);
	
	void deleteFileById(Long id);
}
