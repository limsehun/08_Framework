package edu.kh.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.test.dto.Student;
@Controller
public class StudentController {

	@GetMapping("select")
	public String selectStudent(
			Model model, @ModelAttribute Student student) {
		model.addAttribute("stdName", student.getStdName());
		model.addAttribute("stdAge", student.getStdAge());
		model.addAttribute("stdAddress", student.getStdAddress());
		return "student/select";
	}
	
	@PostMapping("select")
	public String selectStudent(
			@RequestParam("userNo") int userNo,
			Model model, @ModelAttribute Student student) {
		model.addAttribute("stdName", student.getStdName());
		model.addAttribute("stdAge", student.getStdAge());
		model.addAttribute("stdAddress", student.getStdAddress());
		return "student/select";
	}
	
}