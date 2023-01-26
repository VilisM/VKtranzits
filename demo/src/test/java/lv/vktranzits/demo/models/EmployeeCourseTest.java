package lv.vktranzits.demo.models;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.EmployeeCourse;

public class EmployeeCourseTest {
	
	Date date = new Date();
	Employee employee = new Employee();
	Course course = new Course();
    

    EmployeeCourse ec = new EmployeeCourse ("Ugunsdrosiba", 10.0f, date, employee, course) ;
    EmployeeCourse ecClean = new EmployeeCourse();
	// EmployeeCourse co2 = new EmployeeCourse (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testEmployeeCourseCreation() {
		//atļauto datu saglabāšana
		assertEquals("Ugunsdrosiba", ec.getTitle());
		assertEquals(10.0f, ec.getValuePr());
		assertEquals(date, ec.getDate());
		assertEquals(employee, ec.getEmployee());
		assertEquals(course, ec.getEmCourse());
		assertNotNull(ec.getIdEmCo());
		assertEquals("EmployeeCourse(idEmCo=0, title=null, valuePr=0.0, date=null, employee=null, emCourse=null)", ecClean.toString());
	}
	
}