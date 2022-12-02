package lv.vktranzits.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.models.EmployeeCourse;
import lv.vktranzits.demo.models.ResultView;
import lv.vktranzits.demo.services.IEmployeeCourseService;
import lv.vktranzits.demo.services.IEmployeeService;
import lv.vktranzits.demo.services.IExcelService;


@Controller
public class ExcelController {

	@Autowired
	IExcelService excelService;

    @Autowired
    private IEmployeeService employeeService;

	@Autowired
	IEmployeeCourseService empCourseService;
	
    @GetMapping("/employee/showAll/Excel")
    public String saveDataInExcel(Model model){
        
            System.out.println("Excel file has been created");

            excelService.saveDataInExcel();
            
            excelService.saveDataInExcel();
			model.addAttribute("object",employeeService.selectAllEmployees());
			return "employee-show-all";

        
    }

	@PostMapping("/results/save/Excel")
    public String saveResultsInExcel(@Valid ResultView resultView, BindingResult result){
		excelService.saveResultDataInExcel(resultView);
		return "redirect:/results/showAll";
    }

	@PostMapping("/results/load/Excel")
    public String postDataInExcel(@Valid EmployeeCourse emCourse, @Valid Course course, Model model, BindingResult result){
		if(!result.hasErrors()){
			if (excelService.loadResultsFromExcel(emCourse.getTitle(), emCourse.getEmCourse())) {
				return "redirect:/results/showAll";
			} else {
				return "redirect:/results/showAll";
			}
		}
		else{
			return "redirect:/results/showAll";
		}
	}
	
}