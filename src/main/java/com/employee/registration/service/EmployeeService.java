package com.employee.registration.service;

import java.util.List;

import com.employee.registration.domain.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
	
	Employee fetchEmployeeById(int id);
	
	List<Employee> fetchAllEmployees();
	
	Employee updateEmployee(Employee employee);
	
	void deleteEmployee(Employee employee);

}
