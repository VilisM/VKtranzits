package lv.vktranzits.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.repos.ICourseRepo;
import lv.vktranzits.demo.services.impl.ICourseService;

public class CourseServiceImpl implements ICourseService {
    
    @Autowired
    private ICourseRepo courseRepo;

    @Override
    public ArrayList<Course> selectAllCourses(){
        return (ArrayList<Course>) courseRepo.findAll();
    }

    @Override
    public Course selectCourseById(int id){
        Course result = courseRepo.findById(id).get();
        return result;
    }

    @Override
    public boolean deleteCourseById(int id){
        if(courseRepo.existsById(id)){
            courseRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertNewCourse(Course course){
        if(courseRepo.existsByTitle(course.getTitle())){
            return false;
        }
        else{
            courseRepo.save(course);
            return true;
        }
    }

    @Override
    public boolean updateCourseById(int id, Course course){
        if(courseRepo.existsById(id)){
            Course co = courseRepo.findById(id).get();

            co.setTitle(course.getTitle());
            co.setCoType(course.getCoType());
            co.setDepartments(course.getDepartments());
            co.setDescription(course.getDescription());
            
            courseRepo.save(co);
            return true;
        }
        return false;
    }
}
