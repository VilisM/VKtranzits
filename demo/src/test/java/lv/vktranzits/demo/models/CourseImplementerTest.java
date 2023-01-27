package lv.vktranzits.demo.models;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.CourseImplementer;
public class CourseImplementerTest {


    CourseImplementer ci = new CourseImplementer () ;
	// CourseImplementer co2 = new CourseImplementer (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testCourseImplementerCreation() {
		//atļauto datu saglabāšana
		assertEquals("Vaditajs", ci.getNotes());
    
}
}