package lv.vktranzits.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.Employee;

public class EmployeeTest {
    
    Employee e = new Employee("John", "Green", 20000000, "john@gmail.com", "password" ) ;
	//Employee e2 = new Employee(12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testEmployeeCreation() {
		//atļauto datu saglabāšana
		assertEquals("John", e.getName());
		assertEquals("Green", e.getSurname());
		assertEquals(20000000, e.getPhone());
		assertEquals("john@gmail.com", e.getEmail());
		assertEquals("password", e.getPassword());

        
		//neatļauto datu saglabašana
		
		//assertEquals("", e2.getName());
		//assertEquals("", e2.getSurname());
		




}
}