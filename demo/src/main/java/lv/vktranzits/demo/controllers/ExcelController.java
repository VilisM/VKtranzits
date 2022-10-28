package lv.vktranzits.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.vktranzits.demo.services.IEmployeeService;
import lv.vktranzits.demo.services.IExcelService;


@Controller
public class ExcelController {

	@Autowired
	IExcelService excelService;

    @Autowired
    private IEmployeeService employeeService;
	
    @GetMapping("/employee/showAll/Excel")
    public String saveDataInExcel(Model model){
        
            System.out.println("Excel file has been created");

            excelService.saveDataInExcel();
            return "employee-show-all";
            
        
    }
	
	
	
	// @GetMapping("/login")// url - localhost:8082/login
	// public String getExcel ()
	// {
	// 	System.out.println("Excel file has been created");

		
	// 	excelService.loadDataFromExcel();
		
	// 	return "login"; //parādīs home.html lapu
		
		
	// }
	
}