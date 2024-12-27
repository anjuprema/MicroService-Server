package com.anju.demo.dao;

import java.util.List;

import com.anju.demo.model.Employee;

public interface EmployeeHibernateDao {
	List<Employee> getAllEmployee();
	Employee getEmployee(Integer empId);
	boolean saveEmployee(Employee emp);
	boolean deleteEmployee(Integer empId);
	boolean updateEmployee(Employee emp);
}
