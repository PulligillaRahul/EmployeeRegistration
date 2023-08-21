package com.employee.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.registration.domain.Employee;
import com.employee.registration.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee fetchEmployeeById(int id) {
		return employeeRepository.getById(id);
	}

	@Override
	public List<Employee> fetchAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Employee updatedEmployee = employeeRepository.getById(employee.getEmployeeId());
		updatedEmployee.setEmployeeName(employee.getEmployeeName());
		updatedEmployee.setDepartment(employee.getDepartment());
		updatedEmployee.setCountry(employee.getCountry());
		
		return employeeRepository.save(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
		
	}

}
