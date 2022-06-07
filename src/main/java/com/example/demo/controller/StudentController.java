package com.example.demo.controller;



import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Student;
import com.example.demo.export.Exportpdf;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Controller

public class StudentController {
	
	private StudentService studentService;
	
	StudentRepository studentRepository;

	@Autowired
    private EmailService emailService;
	
    @Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	JavaMailSender  mailSendObj;
	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	// handler method to handle list students and return mode and view
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		
		// create student object to hold student form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}

	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		//Student student = null;
		//List<Student> getAllStudents();
		//System.out.println("hahahaahah"+student.getEmail());

		
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) throws MessagingException {
		
		// get student from database by id
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setPdf(student.getPdf());
		existingStudent.setPdfl(student.getPdfl());
		System.out.println("hahahaahah"+student.getEmail());
		/**/
		sendmail(student);
		// save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/students";		
	}
	
	public void sendmail(Student student) throws MessagingException
    {System.out.println("hahahaahah1"+student.getEmail());
    	String toemail = student.getEmail() ;
    	System.out.println("hahahaahah2"+student.getEmail());
    	String topdf = student.getPdf(); 
    	System.out.println("hahahaahahgetPdf"+student.getPdf());
    	String topdfl = student.getPdfl();
    	System.out.println("hahahaahahgetPdflllllllllllllll"+student.getPdf());
    	 MimeMessage mimeMessage=javaMailSender.createMimeMessage();
    	
    	 MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
    	 mimeMessageHelper.setTo(toemail);
    	 mimeMessageHelper.setSubject("Application for internship End-of-studies project");
    	 mimeMessageHelper.setText("ffff");
    	  FileSystemResource fileSystemResource = new FileSystemResource(new File("C:\\Users\\Zakar\\Downloads\\"+topdf));
          FileSystemResource fileSystemResourcel = new FileSystemResource(new File("C:\\Users\\Zakar\\Downloads\\"+topdfl));
          mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),
                  fileSystemResource);
          mimeMessageHelper.addAttachment(fileSystemResourcel.getFilename(),
          		fileSystemResourcel);
    	 javaMailSender.send(mimeMessage);
    }
	
	// handler method to handle delete student request
	
		@GetMapping("/students/{id}")
		public String deleteStudent(@PathVariable Long id) {
			studentService.deleteStudentById(id);
			return "redirect:/students";
		}
		
		
		@GetMapping(value="/Exportpdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
		private void sendmail(@PathVariable("id")Long id) {
			Student student = studentRepository.findById(id).get();
			final String emailToRecipent = student.getEmail();
			final String emailSubject = "Regarding Report";
			final String emailMessage1 = "hhhhhhh";
			mailSendObj.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException {
					MimeMessageHelper mimeMessageHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					mimeMessageHelperObj.setTo(emailToRecipent);
					mimeMessageHelperObj.setText(emailMessage1);
					mimeMessageHelperObj.setSubject(emailSubject);
					
					//DataSource attachment = new ByteArrayDataSource(bis, "application/pdf");
					
					//mimeMessageHelperObj.addAttachment(student.getFirstName()+".pdf", attachment);
				}
			});
		}
	
}
