package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;
import lv.vktranzits.demo.models.CourseCalendar;

public interface ICourseCalendarRepo extends CrudRepository<CourseCalendar, Integer> {
    
}
