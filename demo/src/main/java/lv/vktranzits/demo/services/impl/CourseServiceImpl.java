package lv.vktranzits.demo.services.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.repos.ICourseRepo;
import lv.vktranzits.demo.services.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {
    
    @Autowired
    private ICourseRepo courseRepo;

    @Override
    public ArrayList<Course> selectAllCourses(){
        return (ArrayList<Course>) courseRepo.findAll();
    }

    @Override
    public Course selectCourseById(int id){
    	Optional<Course> course = courseRepo.findById(id);
        if (course.isPresent()) {
            return course.get();
        } else {
            return null;
        }
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
            Course co = courseRepo.findById(id).orElse(null);
            if(co != null) {
	            co.setTitle(course.getTitle());
	            co.setCoType(course.getCoType());
	            co.setDepartments(course.getDepartments());
	            co.setDescription(course.getDescription());
	            
	            courseRepo.save(co);
	            return true;
            }else {
            	return false;
            }
        }
        return false;
    }
    
    @Override
	public Page<Course> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return courseRepo.findAll(pageable);
	}
}
