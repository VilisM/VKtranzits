package lv.vktranzits.demo.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.models.Employee;

public interface ICourseService {

    public abstract ArrayList<Course> selectAllCourses();

    public abstract Course selectCourseById(int id);

    public abstract boolean deleteCourseById(int id);

    public abstract boolean insertNewCourse(Course course);

    public abstract boolean updateCourseById(int id, Course course);

	Page<Course> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    
}
