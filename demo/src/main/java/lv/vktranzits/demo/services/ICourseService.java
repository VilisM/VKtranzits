package lv.vktranzits.demo.services;

import java.util.ArrayList;


import lv.vktranzits.demo.models.Course;

public interface ICourseService {

    public abstract ArrayList<Course> selectAllCourses();

    public abstract Course selectCourseById(int id);

    public abstract boolean deleteCourseById(int id);

    public abstract boolean insertNewCourse(Course course);

    public abstract boolean updateCourseById(int id, Course course);

    
}
