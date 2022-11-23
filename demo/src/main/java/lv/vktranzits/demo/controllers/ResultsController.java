package lv.vktranzits.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lv.vktranzits.demo.services.IEmployeeCourseService;

@Controller
public class ResultsController {

    @Autowired
	IEmployeeCourseService empCourseService;
    
    @GetMapping("/results/showAll")
    public String selectAllEmployees(Model model){
        model.addAttribute("object", empCourseService.selectAllEmployeeResults());
        return "results-show-all";
    }

    @GetMapping("/results/show/{id}")
    public String selectEmployeeById(@PathVariable(name = "id") int id, Model model){
            try {
                return "one-result-page";
            }
            catch(Exception e){
                return "error";
            }
    }
    
}
