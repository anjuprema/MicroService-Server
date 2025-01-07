package com.anju.demo.conroller;

import java.util.List;
import java.util.Optional;

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

import com.anju.demo.dao.EmployeeRepositorySD;
import com.anju.demo.model.Employee;
import com.anju.demo.service.EmployeeService;

/**
 * Microservice Data Provider - 9056
 * */
@RestController
@RequestMapping("")
public class MicroRestControllerServer {
	
	@Autowired
	private EmployeeRepositorySD empRepo;
	
	@Autowired 
	EmployeeService empService;
	
	@GetMapping("/hello")
	public String sayHello() {
		return "hello..";
	}
	
	/**
	 * Using Spring Data
	 * */
	@GetMapping("/isEmployee/{empId}")
	public boolean isEmployee(@PathVariable("empId") Integer empId) {
		if(empId>=1)return true;
		else return false;
	}
	
	@GetMapping("/getEmployee")
	public Optional<Employee> getEmployee(@RequestParam("empId") int empId) {
		Optional<Employee> emp = empRepo.findById(empId);
		return emp;
	}
	/***************************
	 * end; Using Spring Data
	 * */
	
	
	/**
	 * Using Hibernate
	 * */
	@GetMapping("/getEmployeeUsingHibernate")
	public List<Employee> getEmployeeUsingHibernate() {
		return empService.getAllEmployee();
	}
	
	@PostMapping("/saveEmployeeUsingHibernate")
	public Boolean saveEmployeeUsingHibernate(@RequestBody Employee emp) {
		return empService.saveEmployee(emp);
	}
	
	@GetMapping("/getAnEmployeeUsingHibernate")
	public Employee getAnEmployeeUsingHibernate(@RequestParam("empId") Integer empId) {
		return empService.getEmployee(empId);
	}
	
	@PutMapping("/updateEmployeeUsingHibernate")
	public boolean updateEmployeeUsingHibernate(@RequestBody Employee emp) {
		return empService.updateEmployee(emp);
	}
	
	@DeleteMapping("/deleteEmployeeUsingHibernate")
	public Boolean deleteEmployeeUsingHibernate(@RequestParam Integer empId) {
		return empService.deleteEmployee(empId);
	}
	
	/*****************************
	 * end; Using Hibernate
	 * */
}
