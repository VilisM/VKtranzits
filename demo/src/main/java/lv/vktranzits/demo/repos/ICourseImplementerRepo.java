package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.CourseImplementer;
import lv.vktranzits.demo.models.Implementer;

public interface ICourseImplementerRepo extends CrudRepository<CourseImplementer, Integer> {
    
}
