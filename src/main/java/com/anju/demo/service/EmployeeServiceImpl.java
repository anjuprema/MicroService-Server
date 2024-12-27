package com.anju.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anju.demo.dao.EmployeeHibernateDao;
import com.anju.demo.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeHibernateDao empDao;
	
	@Override
	@Transactional
	public List<Employee> getAllEmployee() {
		return empDao.getAllEmployee();
	}

	@Override
	@Transactional
	public Employee getEmployee(Integer empId) {
		return empDao.getEmployee(empId);
	}

	@Override
	@Transactional
	public boolean saveEmployee(Employee emp) {
		return empDao.saveEmployee(emp);
	}

	@Override	
	@Transactional
	public boolean deleteEmployee(Integer empId) {
		return empDao.deleteEmployee(empId);
	}

	@Override
	@Transactional
	public boolean updateEmployee(Employee emp) {		
		return empDao.updateEmployee(emp);
	}

}
