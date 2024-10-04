package com.kh.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.test.dto.Employee;
import com.kh.test.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@ResponseBody
	@GetMapping("selectAll")
	public List<Employee>selectAll(){
		return service.selectAll();
	}
	
}
