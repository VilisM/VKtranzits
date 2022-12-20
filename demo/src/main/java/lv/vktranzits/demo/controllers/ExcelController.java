package lv.vktranzits.demo.controllers;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lv.vktranzits.demo.models.Employee;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.models.EmployeeCourse;
import lv.vktranzits.demo.models.ResultView;
import lv.vktranzits.demo.services.IEmployeeCourseService;
import lv.vktranzits.demo.services.IEmployeeService;
import lv.vktranzits.demo.services.IExcelService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


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
		return "redirect:/results/download/Excel";
	}

	@RequestMapping(value = "/results/download/Excel", method = RequestMethod.GET)
	public void getFile(HttpServletResponse response) {
		response.reset();
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=resultsSaved.xlsx";
		response.setHeader(headerKey, headerValue);

		excelService.exportExcelResult(response);
	}

	@PostMapping("/results/load/Excel")
    public String postDataInExcel(EmployeeCourse emCourse, @RequestParam("file") MultipartFile file, RedirectAttributes attributes){
		if(!emCourse.getTitle().isEmpty() && !emCourse.getTitle().isBlank()){
			if (file.isEmpty()) {
				attributes.addFlashAttribute("message", "You need to select a file!");
				return "redirect:/results/showAll";
			}
			InputStream savedFile = null;
			try {
				savedFile = file.getInputStream();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			if (excelService.loadResultsFromExcel(emCourse.getTitle(), emCourse.getEmCourse(), savedFile)) {
				return "redirect:/results/showAll";
			} else {
				return "redirect:/results/showAll";
			}
		}
		else{
			attributes.addFlashAttribute("message", "Complete all fields before importing!");
			return "redirect:/results/showAll";
		}
	}
	
}