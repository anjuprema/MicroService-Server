package com.anju.demo.service;

import java.util.List;

import com.anju.demo.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployee();
	Employee getEmployee(Integer empId);
	boolean saveEmployee(Employee emp);
	boolean deleteEmployee(Integer empId);
	boolean updateEmployee(Employee emp);
}
