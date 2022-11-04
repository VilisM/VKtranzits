package lv.vktranzits.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.vktranzits.demo.services.ICourseService;
import lv.vktranzits.demo.services.IWordService;

@Controller
public class WordController {
    
    @Autowired
    private ICourseService courseService;


    @Autowired
    private IWordService wordService;

    @GetMapping("/course/showAll/word")
    public String saveDataInWord(Model model){
        
            System.out.println("Word file has been created");

            wordService.saveDataInWord();model.addAttribute("object",courseService.selectAllCourses());
            return "course-show-all";
            
        
    }


}
