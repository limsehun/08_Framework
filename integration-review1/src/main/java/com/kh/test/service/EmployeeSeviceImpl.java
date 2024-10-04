package com.kh.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.test.dto.Employee;
import com.kh.test.mapper.EmployeeMapper;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeSeviceImpl implements EmployeeService{

	@Autowired
	private final EmployeeMapper mapper;

	public List<Employee>selectAll(){
		return mapper.selectAll();
	}
	
}
