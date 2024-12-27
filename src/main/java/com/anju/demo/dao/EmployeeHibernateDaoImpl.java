package com.anju.demo.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.anju.demo.model.Employee;

@Repository
public class EmployeeHibernateDaoImpl implements EmployeeHibernateDao {
	
	@Autowired
	EntityManager entityManager;
	
	private Session currentSession;
	
	@PostConstruct
	public void getCurrentSession() {
		currentSession = entityManager.unwrap(Session.class);
	}
	
	@Override
	public List<Employee> getAllEmployee() {	
		Query query = currentSession.createQuery(" from Employee", Employee.class);
		List<Employee> emp = query.getResultList();
		return emp;
	}

	@Override
	public Employee getEmployee(Integer empId) {
		Query query = currentSession.createQuery(" from Employee where empId="+empId, Employee.class);
		Employee emp = (Employee) query.getSingleResult();
		return emp;
	}

	@Override
	public boolean saveEmployee(Employee emp) {
		currentSession.save(emp);
		return true;
	}

	@Override
	public boolean deleteEmployee(Integer empId) {
		Employee emp = currentSession.get(Employee.class, empId);
		currentSession.delete(emp);
		return true;
	}

	@Override
	public boolean updateEmployee(Employee emp) {
		currentSession.update(emp);
		return true;
	}

}
