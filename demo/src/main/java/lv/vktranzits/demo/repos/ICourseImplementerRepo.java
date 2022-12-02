package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.CourseImplementer;

public interface ICourseImplementerRepo extends CrudRepository<CourseImplementer, Integer> {
    
}
