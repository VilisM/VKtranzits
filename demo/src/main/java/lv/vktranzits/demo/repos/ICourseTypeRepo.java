package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.CourseType;

public interface ICourseTypeRepo extends CrudRepository<CourseType, Integer> {
    
}
