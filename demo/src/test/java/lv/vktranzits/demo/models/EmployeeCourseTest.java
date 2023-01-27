package lv.vktranzits.demo.models;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.EmployeeCourse;
import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.models.Course;

public class EmployeeCourseTest {
    

	Employee e1 = new Employee("Janis", "Berzins", 22222222, "janisberzins@inbox.lv", "test123");
	CourseType t1 = new CourseType(true, "Vienu reizi gadā");

	Course c1 = new Course("Programmētājs", "te kaut kada informācija", t1);

	Date date = new Date();

    EmployeeCourse ec = new EmployeeCourse ("Ugunsdrosiba", 10.0f, date, e1, c1) ;
	// EmployeeCourse co2 = new EmployeeCourse (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testEmployeeCourseCreation() {
		//atļauto datu saglabāšana
		assertEquals("Ugunsdrosiba", ec.getTitle());
		assertEquals(10.0f, ec.getTitle());
		assertEquals(date, ec.getDate());
		assertEquals(e1, ec.getEmployee());
		assertEquals(c1, ec.getEmCourse());
		
}
}