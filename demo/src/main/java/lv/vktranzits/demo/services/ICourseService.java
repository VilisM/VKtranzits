package lv.vktranzits.demo.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import lv.vktranzits.demo.models.Course;

public interface ICourseService {

    ArrayList<Course> selectAllCourses();

    Course selectCourseById(int id);

    boolean deleteCourseById(int id);

    boolean insertNewCourse(Course course);

    boolean updateCourseById(int id, Course course);

	Page<Course> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    
}
