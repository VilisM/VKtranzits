package lv.vktranzits.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.Course;
public class CourseTest {


    Course c = new Course ("Vaditajs", "Labs kurss", CourseType coType) ;
	// Course c2 = new Course (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testEmployeeCreation() {
		//atļauto datu saglabāšana
		assertEquals("Vaditajs", c.getTitle());
		assertEquals("Labs kurss", c.getDescription());
		assertEquals("Vaditajs", c.getCoType());
		
	
}
}