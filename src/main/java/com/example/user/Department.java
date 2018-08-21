package com.example.user;

import org.springframework.data.annotation.Id;

public class Department {

	@Id
    private String id;
   
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	private String deptname;

   

}
