package com.bank.bankofrupcy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.bankofrupcy.model.Employee;
import com.bank.bankofrupcy.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	// Find all Employees
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> allEmployees = employeeRepository.findAll();
		return ResponseEntity.ok(allEmployees);
	}

	// Find Employee By Id
	public ResponseEntity<Employee> findEmployeeById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return ResponseEntity.ok(employee.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Modify or Add Employee
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee newEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(newEmployee);
	}

	// Delete Employee
	public ResponseEntity<String> deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return ResponseEntity.ok("Employee Deleted Successfully");
	}

}