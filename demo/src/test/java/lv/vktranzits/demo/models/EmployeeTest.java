package lv.vktranzits.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.Employee;

public class EmployeeTest {
    
    Employee e = new Employee("John", "Green", 20000000, "john@gmail.com", "password" ) ;
	Employee e2 = new Employee("1234555", "123456", 555555, "johngmail.com", "password");
	Employee em = new Employee();
	
	@Test
	void testEmployeeCreation() {
		//atļauto datu saglabāšana
		assertEquals("John", e.getName());
		assertEquals("Green", e.getSurname());
		assertEquals(20000000, e.getPhone());
		assertEquals("john@gmail.com", e.getEmail());

        
		//neatļauto datu saglabašana
		
		assertEquals("1234555", e2.getName());
		assertEquals("123456", e2.getSurname());
		assertEquals(null, em.getPassword());




}
}