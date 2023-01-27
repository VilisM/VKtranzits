package lv.vktranzits.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
public class CourseTest {


	CourseType courseType = new CourseType(true, "test");
	
    Course c = new Course ("Vaditajs", "Labs kurss", courseType) ;
	// Course c2 = new Course (12345667, 3453456, "carry", "johngmail.com", "password");
    Course c2 = new Course();
    
    Department dep = new Department();
    List<Department> departments = new ArrayList<Department>();
   
	
	@Test
	void testEmployeeCreation() {
		//atļauto datu saglabāšana
		assertEquals("Vaditajs", c.getTitle());
		assertEquals("Labs kurss", c.getDescription());
		assertEquals(courseType, c.getCoType());
		assertEquals("Course(idCou=0, title=null, description=null, coType=null, departments=[])", c2.toString());
		assertEquals(null, c.getCourses());
		assertEquals(null, c.getCourseCalendars());
		departments.add(dep);
		c.setDepartments(departments);
		c.setCourseCalendars(null);
		c.setCourses(null);
}
}