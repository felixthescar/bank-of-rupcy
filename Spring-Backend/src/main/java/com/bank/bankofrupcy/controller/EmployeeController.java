package com.bank.bankofrupcy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankofrupcy.model.Employee;
import com.bank.bankofrupcy.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// Get all employees
	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	// Get a employee by ID
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		ResponseEntity<Employee> employee = employeeService.findEmployeeById(id);
		return employee.hasBody() ? employee : new ResponseEntity<Employee>(new Employee(), HttpStatus.NOT_FOUND);
	}

	// Create a new employee
	@PostMapping("/createEmployee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return employee.getNpc() != null ? employeeService.saveEmployee(employee)
				: new ResponseEntity<Employee>(new Employee(), HttpStatus.FORBIDDEN);
	}

	// Modify an employee
	@PostMapping("/modifyEmployee")
	public ResponseEntity<Employee> modifyEmployee(@RequestBody Employee employee) {
		return employee.getId() != null ? employeeService.saveEmployee(employee)
				: new ResponseEntity<Employee>(new Employee(), HttpStatus.NOT_FOUND);
	}

	// Delete a Employee
	@DeleteMapping(value = "/deleteEmployee/{id}")
	public String deleteProduct(@PathVariable Long id) {
		ResponseEntity<Employee> employee = employeeService.findEmployeeById(id);
		if (employee.hasBody()) {
			employeeService.deleteEmployee(id);
			return "Employee Deleted Successfully with id " + id + " ";
		}
		return "Employee Not Found";
	}

}