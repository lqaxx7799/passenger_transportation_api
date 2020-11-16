package web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import web.model.Employee;
import web.repos.EmployeeRepository;
import web.exception.EmployeeNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employee")
	public List<Employee> getAll() { 
		return employeeRepository.findAllAvailable();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getById(@PathVariable int id) {
		return employeeRepository.findById(id).filter(employee -> employee.getIsWorking())
				.orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
	@PostMapping("/employee")
	public Employee addNew(@RequestBody Employee employee) {
		// TODO: implement validation
		employee.setStartedTime(new Date());
		employee.setIsWorking(true);
		return employeeRepository.save(employee);
	}
	
	@PutMapping("/employee/{id}")
	public Employee update(@RequestBody Employee newEmployee, @PathVariable int id) {
		// if found, perform update, else throw error
		return employeeRepository.findById(id).map(employee -> {
			// skip id, createdTime and isDeleted
			employee.setEmployeeName(newEmployee.getEmployeeName());
			employee.setIdentificationNumber(newEmployee.getIdentificationNumber());
			employee.setLicenseCode(newEmployee.getLicenseCode());
			employee.setLicenseType(newEmployee.getLicenseType());
			employee.setAddress(newEmployee.getAddress());
			employee.setDateOfBirth(newEmployee.getDateOfBirth());
			employee.setSeniority(newEmployee.getSeniority());
			employee.setGender(newEmployee.getGender());
			employee.setStartedTime(new Date());
			
			return employeeRepository.save(employee);
		}).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@DeleteMapping("/employee/{id}")
	public void delete(@PathVariable int id) {
		// if found, perform update, else throw error
		employeeRepository.findById(id).map(employee -> {
			employee.setIsWorking(false);
			return employeeRepository.save(employee);
		}).orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
}
