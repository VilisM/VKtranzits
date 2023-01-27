package lv.vktranzits.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.Course;
public class CourseTest {

	CourseType t1 = new CourseType(true, "Vienu reizi gadā");

    Course c = new Course ("Vaditajs", "Labs kurss", t1) ;
	// Course c2 = new Course (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testEmployeeCreation() {
		//atļauto datu saglabāšana
		assertEquals("Vaditajs", c.getTitle());
		assertEquals("Labs kurss", c.getDescription());
		assertEquals(true, c.getCoType().isObligatory());
		assertEquals("Vienu reizi gadā", c.getCoType().getDescription());
		
	
}
}