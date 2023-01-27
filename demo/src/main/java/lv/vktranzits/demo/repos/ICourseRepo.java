package lv.vktranzits.demo.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import lv.vktranzits.demo.models.Course;

public interface ICourseRepo extends PagingAndSortingRepository<Course, Integer> {

    boolean existsByTitle(String title);

}
