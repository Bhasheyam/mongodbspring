package Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class departmenttest {
	static int i = 0;
	@Before
	public void setup()
	{
		
		i++;
	}
	
	
	@After
	public void finish()
	{
		
		System.out.println("Testcase " + i + " completed.");
		
	}
	
	
	@Test
	public void testones() 
	{
		
		
		
	}

}
