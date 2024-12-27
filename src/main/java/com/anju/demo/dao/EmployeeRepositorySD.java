package com.anju.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anju.demo.model.Employee;

public interface EmployeeRepositorySD extends JpaRepository<Employee, Integer>{

}
