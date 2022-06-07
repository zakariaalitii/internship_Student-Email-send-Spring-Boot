package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repository.StudentRepository;

@SpringBootApplication
public class StudentEmailsendApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StudentEmailsendApplication.class, args);
	}
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
