package com.example.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;
import com.example.user.Department;
import com.example.user.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@RestController
@RequestMapping( "/employee" )
public class EmployeeController 
{
	
	

    @Autowired
    EmployeeRepository employeerepository;
    
    
    @Autowired
    DepartmentRepository deptartment;

    
    
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<String> create( @RequestBody Employee emp ) {
    	
    	//If department is not there
    	if(deptartment.findByDeptname( emp.getDeptname()) == null )
    	{
    		Department dept = new Department();
    		dept.setDeptname( emp.getDeptname() );
    		deptartment.save( dept );
    	}
    	
    	employeerepository.save( emp );
        return new ResponseEntity<String>("Pushed in DB", HttpStatus.CREATED);
    }
  
    
    
    
    
    
    
    @RequestMapping( value = "/test" ) 
    public ResponseEntity<String> read1()
    
    {
    	HttpHeaders response = new HttpHeaders();
    	response.setContentLength(10);
        return new ResponseEntity<String>("Hello World", response, HttpStatus.CREATED);
    }
    
    
    
    
    
    @RequestMapping ( method = RequestMethod.GET ) 
    public ResponseEntity<List<Employee>> readall()
    {

    	 return new ResponseEntity<List<Employee>>(employeerepository.findAll(), HttpStatus.OK); 
    }
    
    
    
    
    

    @RequestMapping( value = "/{id}" ) 
    public ResponseEntity<Employee> read( @PathVariable String id ) {
        
        return new ResponseEntity<Employee>(employeerepository.findOne( id ), HttpStatus.OK);
    }
    
    
    
    
    
    @RequestMapping(value = "name/{employeename}") 
    public ResponseEntity<List<Employee>> readnames(@PathVariable String employeename) {
   
        return new ResponseEntity<List<Employee>>(employeerepository.findByEmployeename(employeename), HttpStatus.OK); 
    }
    
    
    
    
    @RequestMapping(value = "/readfile",method = RequestMethod.POST)
    public void readfromfile() throws IOException
    {
    	ClassLoader classLoader = getClass().getClassLoader();
    	InputStream inputStream = classLoader.getResourceAsStream("Employee.txt");
    	String data = readFromInputStream(inputStream);
    	ObjectMapper mapper = new ObjectMapper();
    	List<Employee> employeelist = mapper.readValue(data, new TypeReference<List<Employee>>(){});
    	employeerepository.save(employeelist);
    	
    	
    }
    
    
    
    
   
    @RequestMapping(value ="/storetofile")
    public void storetofile() throws IOException
    {
    	FileOutputStream fos = new FileOutputStream("Employeefromdb.txt");
    	ObjectOutputStream oos = new ObjectOutputStream(fos);
    	String employeelist =  new Gson().toJson(employeerepository.findAll());
    		 oos.writeObject(employeelist);
    	
       
    	oos.close();
    }
    
    
    
    
    
    
   //stream to string 
private String readFromInputStream(InputStream inputStream)throws IOException 
    
    {
    	
    	
    		    StringBuilder resultStringBuilder = new StringBuilder();
    		    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream)))
    		    {
    		        String line;
    		        while ((line = br.readLine()) != null)
    		        {
    		            resultStringBuilder.append(line).append("\n");
    		        }
    		    }
    		  return resultStringBuilder.toString();
    		}

}
