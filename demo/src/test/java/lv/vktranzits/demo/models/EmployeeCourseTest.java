package lv.vktranzits.demo.models;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.EmployeeCourse;

public class EmployeeCourseTest {
    

    EmployeeCourse ec = new EmployeeCourse ("Ugunsdrosiba", 10.0f, Date date, Employee employee, Course emCourse) ;
	// EmployeeCourse co2 = new EmployeeCourse (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testEmployeeCourseCreation() {
		//atļauto datu saglabāšana
		assertEquals("Ugunsdrosiba", ec.getTitle());
		assertEquals(10.0f, ec.getTitle());
		assertEquals(date, ec.getDate());
		assertEquals(Employee, ec.getEmployee());
		assertEquals(emCourse, ec.getEmCourse());
		
}
}