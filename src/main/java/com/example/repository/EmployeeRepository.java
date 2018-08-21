package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.user.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String>{
	
	public List<Employee> findByEmployeename(String employeename);


}
