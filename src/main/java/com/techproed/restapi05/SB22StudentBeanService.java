package com.techproed.restapi05;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SB22StudentBeanService {

	private static SB21StudentBeanRepository studentRepository;
	
	@Autowired
	public SB22StudentBeanService(SB21StudentBeanRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
//	public List<SB20StudentBean> getStudents(){
//		return studentRepository.findAll();
//	}
	
	//For GET Requests for all data
	public static List<SB20StudentBean> getStudents(){
		return studentRepository.findAll();
	}
	
	//For GET Request by using id
	public static Optional<SB20StudentBean> getStudentById(Long studentId) {
		return studentRepository.findById(studentId);
	}
	
	//For POST Requests
	public static void addNewStudent(SB20StudentBean studentBean) {
		
		Optional<SB20StudentBean> studentEmailOptional =  studentRepository.findSB20StudentBeanByEmail(studentBean.getEmail());
		Optional<SB20StudentBean> studentIdOptional =  studentRepository.findSB20StudentBeanById(studentBean.getId());
		
		if(studentEmailOptional.isPresent()) {
			throw new IllegalStateException("Email is taken");
		}
		if(studentIdOptional.isPresent()) {
			throw new IllegalStateException("Id exists, id should be unique");
		}
		
		studentRepository.save(studentBean);
		
	}
	
	//For DELETE Requests
	public static String deleteStudentById(Long studentId) {
		
		//1.Way to check if the record exist by using id
		boolean isExist = studentRepository.existsById(studentId);
		if(!isExist) {
			throw new IllegalStateException(studentId + " does not exist. So it cannot be deleted...");
		}
		
		studentRepository.deleteById(studentId);
		
		return "Student whose id is " + studentId + " is deleted successfully!...";
	}
	
	//For Put Requests
	@Transactional
	public static SB20StudentBean updateStudent(Long studentId, @RequestBody SB20StudentBean newStudent) {
		
		//2.Way to check if the record exist by using id
		SB20StudentBean existingStudent = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException(studentId + " does not exist..."));
		
		//Update "name"
		String existingName = existingStudent.getName();
		if(newStudent.getName()==null) {
			existingStudent.setName(null);
		}else if(existingStudent.getName()==null) {
			existingStudent.setName(newStudent.getName());
		}else if(!existingName.equals(newStudent.getName())) {
			existingStudent.setName(newStudent.getName());
		}
		
		//Update "email"
		String existingEmail = existingStudent.getEmail();
		if(newStudent.getEmail()==null) {
			existingStudent.setEmail(null);
		}else if(existingStudent.getEmail()==null) {
			existingStudent.setEmail(newStudent.getEmail());
		}else if(!existingEmail.equals(newStudent.getEmail())) {
			existingStudent.setEmail(newStudent.getEmail());
		}else {
			throw new IllegalStateException("Email exists, use another email");
		}
		
		//Update date of birth
		LocalDate existingDob = existingStudent.getDob();
		if(newStudent.getDob()==null) {
			existingStudent.setDob(null);
		}else if(existingStudent.getDob()==null) {
			existingStudent.setDob(newStudent.getDob());
		}else if(!existingDob.equals(newStudent.getDob())) {
			existingStudent.setDob(newStudent.getDob());
		}
		
		
		return studentRepository.save(existingStudent);
		
		//Note: eski kodlar 
//		//Update name
//		if(newStudent.getName()!=null) {
//			String newName = newStudent.getName();
//			if(!newName.equals(existingStudent.getName())) {
//				existingStudent.setName(newName);
//			}
//		}
//		//Update email
//		if(newStudent.getEmail()!=null) {
//			String newEmail = newStudent.getEmail();
//			if(!newEmail.equals(existingStudent.getEmail())) {
//				existingStudent.setEmail(newEmail);
//			}
//		}
//		//Update date of birth
//		if(newStudent.getDob()!=null) {
//			LocalDate newDob = newStudent.getDob();
//			if(!newDob.equals(existingStudent.getDob())) {
//				existingStudent.setDob(newDob);
//			}
//		}
//		return studentRepository.save(existingStudent);
	}
	
	//For PATCH Request
	public static SB20StudentBean updateStudentPartially(Long studentId, @RequestBody SB20StudentBean newStudent) {
		
		SB20StudentBean existingStudent = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException(studentId + " does not exist..."));
		
		if(newStudent.getName()!=null) {
			existingStudent.setName(newStudent.getName());
		}
		
		Optional<SB20StudentBean> studentEmailOptional = studentRepository.findSB20StudentBeanByEmail(newStudent.getEmail());
		if(studentEmailOptional.isPresent()) {
			throw new IllegalStateException("Email is taken, try with another email...");
		}
		if(newStudent.getEmail()!=null) {
			existingStudent.setEmail(newStudent.getEmail());
		}
		
		if(newStudent.getDob()!=null) {
			existingStudent.setDob(newStudent.getDob());
		}
		
		return studentRepository.save(existingStudent);
	}
	
}
