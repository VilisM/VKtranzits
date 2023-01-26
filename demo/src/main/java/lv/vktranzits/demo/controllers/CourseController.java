package lv.vktranzits.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.services.ICourseService;
import lv.vktranzits.demo.services.ICourseTypeService;

@Controller
public class CourseController {
    
    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseTypeService courseTypeService;
    
    @GetMapping("/course/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam(name="sortField", defaultValue = "idCou") String sortField,
			@RequestParam(name="sortDir", defaultValue = "asc") String sortDir,
			Model model) {
		int pageSize = 5;
		Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Course> listCourse = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listCourse", listCourse);
		return "course-show-all";
	}

    @GetMapping("/course/showAll")
    public String selectAllCourses(Model model){
        model.addAttribute("object", courseService.selectAllCourses());
        return "course-show-all";
    }

    @GetMapping("/course/showAll/{id}")
    public String selectCourseById(@PathVariable(name = "id") int id, Model model){
        try{
            model.addAttribute("object", courseService.selectCourseById(id));
            return "course-show-all-id";
        }
        catch(Exception e){
            return "error";
        }
    }

    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable(name = "id") int id, Model model){
        if(courseService.deleteCourseById(id)){
            model.addAttribute("object", courseService.selectAllCourses());
            return "course-show-all";
        }
        else{
            return"redirect:/course/showAll";
        }
    }

    @GetMapping("/course/add")
    public String getInsertNewCourse(Model model){
        model.addAttribute("course", new Course());
        model.addAttribute("coursetype", courseTypeService.selectAllCourseTypes());
        return "course-add";
    }

    @PostMapping("/course/add")
    public String postInsertNewCourse(@Valid Course course, BindingResult result){
        if(!result.hasErrors()){
            if(courseService.insertNewCourse(course))
                return "redirect:/course/showAll";
            else
                return "redirect:/error";
        }
        else{
            return"course-add";
        }
    }

    @GetMapping("/course/update/{id}")
    public String getUpdateCourse(@PathVariable(name = "id") int id, Model model){
        try{
            model.addAttribute("course", courseService.selectCourseById(id));
            model.addAttribute("coursetype", courseTypeService.selectAllCourseTypes());
            return "course-update";
        }
        catch(Exception e){
            return "error";
        }
    }

    @PostMapping("/course/update/{id}")
    public String postUpdateCourse(@PathVariable(name = "id") int id,@Valid Course course, BindingResult result, Model model){
        
        if(!result.hasErrors()){
            if(courseService.updateCourseById(id, course))
                return "redirect:/course/showAll/" + id;
            else
                return"redirect:/error";
        }
        else{
            model.addAttribute("position", courseTypeService.selectAllCourseTypes());
            return "course-update";
        }
    }
}