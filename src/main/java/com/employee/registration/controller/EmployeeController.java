package com.employee.registration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.registration.domain.Employee;
import com.employee.registration.service.EmployeeService;

@RestController
@RequestMapping("/employee/registrations")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeServiceImpl;
	
	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees(){
		return employeeServiceImpl.fetchAllEmployees();
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public Employee getEmployeeById(@PathVariable int id){
		return employeeServiceImpl.fetchEmployeeById(id);
	}
	
	@PostMapping("/saveEmployee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeServiceImpl.saveEmployee(employee);
	}
	
	@PutMapping("/updateEmployee")
	public Employee updateEmployee(@RequestBody Employee employee){
		return employeeServiceImpl.updateEmployee(employee);
	}
	
	@DeleteMapping("/deleteEmployee")
	public void deleteEmployee(@RequestBody Employee employee) {
		employeeServiceImpl.deleteEmployee(employee);
	}

}
