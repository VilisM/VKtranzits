package lv.vktranzits.demo.models;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.CourseType;

public class CourseTypeTest {
   
    
    CourseType ct = new CourseType (true, "Obligats kurss") ;
	// CourseType ct2 = new CourseType (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testCourseTypeCreation() {
		//atļauto datu saglabāšana
		assertEquals(true, ct.isObligatory());
		assertEquals("Obligats kurss", ct.getDescription());

}
}
