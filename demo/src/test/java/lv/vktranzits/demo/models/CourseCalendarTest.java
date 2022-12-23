package lv.vktranzits.demo.models;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.CourseCalendar;
public class CourseCalendarTest {
    

    CourseCalendar co = new CourseCalendar (2022, Date start_date, Date end_date) ;
	// CourseCalendar co2 = new CourseCalendar (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testCourseCalendarCreation() {
		//atļauto datu saglabāšana
		assertEquals(2022, co.getCalYear());
		assertEquals(start_date, co.getStart_date());
		assertEquals(end_date, co.getEnd_date());

}
}
