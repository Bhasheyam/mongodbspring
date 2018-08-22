package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.user.Department;

public interface DepartmentRepository extends MongoRepository< Department, String >{

	public Department findByDeptname( String deptname );
}
