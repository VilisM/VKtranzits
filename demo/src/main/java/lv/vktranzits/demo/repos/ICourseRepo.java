package lv.vktranzits.demo.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.models.Employee;

public interface ICourseRepo extends CrudRepository<Course , Integer>, PagingAndSortingRepository<Course, Integer> {

    boolean existsByTitle(String title);

}
