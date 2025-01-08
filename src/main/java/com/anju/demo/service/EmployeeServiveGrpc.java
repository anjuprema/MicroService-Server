package com.anju.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anju.demo.model.Employee;
import com.anju.demo.stub.Employee.AllEmployeeRequest;
import com.anju.demo.stub.Employee.AllEmployeeResponse;
import com.anju.demo.stub.Employee.EmployeeRequest;
import com.anju.demo.stub.Employee.EmployeeResponse;
import com.anju.demo.stub.Employee.IsAnEmployeeResponse;
import com.anju.demo.stub.EmployeeServiceGrpc.EmployeeServiceImplBase;

import io.grpc.stub.StreamObserver;

@Service
public class EmployeeServiveGrpc extends EmployeeServiceImplBase{
	
	@Autowired
	private EmployeeService empService;
	
	@Override
	public void getAnEmployee(EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
		int empId = request.getEmpId();
		 if(empId > 0) {
			 Employee emp = empService.getEmployee(empId);
			 System.out.println(emp);
			 EmployeeResponse.Builder response = EmployeeResponse.newBuilder();
			 response.setEmpId(emp.getEmpId());
			 response.setEmpName(emp.getEmpName());
			 responseObserver.onNext(response.build());
			 responseObserver.onCompleted();
		 }		 
	}

	@Override
	public void getAllEmployees(AllEmployeeRequest request, StreamObserver<AllEmployeeResponse> responseObserver) {
		List<Employee> employees = empService.getAllEmployee();
		List<EmployeeResponse> empList = new ArrayList<EmployeeResponse>();
		for(Employee emp : employees) {
			empList.add(EmployeeResponse.newBuilder().setEmpId(emp.getEmpId()).setEmpName(emp.getEmpName()).build());
		}
		
		AllEmployeeResponse.Builder response = AllEmployeeResponse.newBuilder();
		response.addAllEmployee(empList);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void isAnEmployeeAsync(EmployeeRequest request, StreamObserver<IsAnEmployeeResponse> responseObserver) {
		Employee emp = empService.getEmployee(request.getEmpId());
		IsAnEmployeeResponse.Builder response = IsAnEmployeeResponse.newBuilder();		
		if(emp.getEmpId() > 0) {
			response.setIsAnEmployee(true);
		}else {
			response.setIsAnEmployee(false);
		}
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

}
