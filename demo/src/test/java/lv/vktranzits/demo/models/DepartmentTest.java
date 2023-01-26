package lv.vktranzits.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.Department;
public class DepartmentTest {


    Department d = new Department ("Kraveji","Janis", "Berzins" ) ;
	// Department d2 = new Department (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testDepartmentCreation() {
		//atļauto datu saglabāšana
		assertEquals("Kraveji", d.getTitle());
		assertEquals("Janis", d.getVname());
		assertEquals("Berzins", d.getVsurname());
		
	
}
}