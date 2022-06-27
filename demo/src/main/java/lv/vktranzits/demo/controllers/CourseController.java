package lv.vktranzits.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.services.ICourseService;

@Controller
public class CourseController {
    
    @Autowired
    private ICourseService courseService;

    @GetMapping("/course/showAll")
    public String selectAllCourses(Model model){
        model.addAttribute("Object", courseService.selectAllCourses());
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
    public String getInsertNewCourse(Course course){
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
            return "course-update";
        }
        catch(Exception e){
            return "error";
        }
    }

    @PostMapping("/course/update/{id}")
    public String postUpdateCourse(@PathVariable(name = "id") int id,@Valid Course course, BindingResult result){
        if(!result.hasErrors()){
            if(courseService.updateCourseById(id, course))
                return "redirect:/course/showAll/" + id;
            else
                return"redirect:/error";
        }
        else{
            return "course-update";
        }
    }
}