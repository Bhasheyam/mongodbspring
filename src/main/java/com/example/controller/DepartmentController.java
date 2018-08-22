package com.example.controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.repository.DepartmentRepository;
import com.example.user.Department;
import com.example.user.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@RestController
@RequestMapping( "/dept" )
public class DepartmentController 

{
	
	@Autowired
	DepartmentRepository deptartment;
	
	
	@RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<String> create( @RequestBody Department emp )
	{
		if( deptartment.findByDeptname( emp.getDeptname() ) == null )
    	{
			deptartment.save( emp );	
    	}
		return new ResponseEntity<String>("Pushed in DB", HttpStatus.CREATED);
    }

    
    @RequestMapping(  value = "/test" ) 
    public String read1()
    {
        return "Working1";
    }
    
    

    @RequestMapping( value = "/{deptname}" ) 
    public ResponseEntity<Department> read( @PathVariable String deptname )
    {
        return new ResponseEntity<Department>(deptartment.findByDeptname( deptname ), HttpStatus.OK);
    }
    
    
    @RequestMapping( method = RequestMethod.GET ) 
    public  ResponseEntity<List<Department>> readall()
    {
    	
 
    	return new ResponseEntity<List<Department>>(deptartment.findAll(), HttpStatus.OK);
    }
    
    
    
    @RequestMapping( value ="/storetofile" )
    public void storetofile() throws IOException
    {
    	FileOutputStream fos = new FileOutputStream( "DepartmentfromDB.txt" );
    	ObjectOutputStream oos = new ObjectOutputStream( fos );
    	String employeelist =  new Gson().toJson( deptartment.findAll() );
        oos.writeObject( employeelist );
    	oos.close();
    }
    
    
    @RequestMapping( value = "/readfile",method = RequestMethod.POST )
    public void readfromfile() throws IOException
    {
    	ClassLoader classLoader = getClass().getClassLoader();
    	InputStream inputStream = classLoader.getResourceAsStream( "Department.txt" );
    	String data = readFromInputStream( inputStream );
    	ObjectMapper mapper = new ObjectMapper();
    	List< Department > departmentlist = mapper.readValue( data, new TypeReference<List<Department>>(){} );
    	deptartment.save( departmentlist );
    }

    
   
    
    
    
    
    private String readFromInputStream( InputStream inputStream )throws IOException 
    
    {
    	
    	
    		    StringBuilder resultStringBuilder = new StringBuilder();
    		    try ( BufferedReader br = new BufferedReader( new InputStreamReader(inputStream) ) )
    		    {
    		        String line;
    		        while (( line = br.readLine()) != null )
    		        {
    		            resultStringBuilder.append( line ).append( "\n" );
    		        }
    		    }
    		  return resultStringBuilder.toString();
    		}
	

}
