package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.Course;

public interface ICourseRepo extends CrudRepository<Course , Integer> {
    
}
