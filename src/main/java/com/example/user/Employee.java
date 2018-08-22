package com.example.user;

import org.springframework.data.annotation.Id;

public class Employee 

{
    @Id
    private String id;
	private String employeename;
    private String deptname;

    public String getId()
    {
		return id;
	}
    
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getEmployeename() 
	{
		return employeename;
	}
	
	public void setEmployeename(String employeename)
	{
		this.employeename = employeename;
	}
	
	public String getDeptname() 
	{
		return deptname;
	}
	
	public void setDeptname(String deptname)
	{
		this.deptname = deptname;
	}

}