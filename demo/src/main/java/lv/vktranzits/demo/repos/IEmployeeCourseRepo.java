package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.Course;

public interface IEmployeeCourseRepo extends CrudRepository<EmployeeCourse , Integer> {

    boolean existsByTitle(String title);
    
    
}
