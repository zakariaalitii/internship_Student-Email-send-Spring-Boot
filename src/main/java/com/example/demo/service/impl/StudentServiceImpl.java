package com.example.demo.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Filesend;
import com.example.demo.entity.Student;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;


@Service

public class StudentServiceImpl implements StudentService{

	private StudentController studentController;
	
     private StudentRepository studentRepository;
     private FileRepository fileRepository;
     
     private Student student;
     private Filesend filesend;
	
	public StudentServiceImpl(StudentRepository studentRepository,FileRepository fileRepository) {
		super();
		this.studentRepository = studentRepository;
		this.fileRepository = fileRepository;
	}
	
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);	
	}

	@Override
	public List<Student> selectData(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Filesend> getAllFiles() {
		return fileRepository.findAll();
	}

	@Override
	public Filesend saveFile(Filesend filesend) {
		return fileRepository.save(filesend);
	}

	@Override
	public Filesend getFileById(Long id) {
		return fileRepository.findById(id).get();
	}

	@Override
	public Filesend updateFile(Filesend filesend) {
		return fileRepository.save(filesend);
	}

	@Override
	public List<Filesend> selectData(Filesend ileSend) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFileById(Long id) {
		studentRepository.deleteById(id);	
	}
	

}
