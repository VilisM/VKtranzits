package lv.vktranzits.demo.controllers;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.vktranzits.demo.models.Department;
import lv.vktranzits.demo.services.DepartmentService;
import lv.vktranzits.demo.services.IEmployeeService;

@Controller

public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService; //te liek tikai interface, lai sasaitītu ar impl lieto autowired
<<<<<<< HEAD
	
	@Autowired
	private EmployeeService employeeService;
=======

	@Autowired
	private IEmployeeService employeeService;
			
>>>>>>> ad03bf5e8567a7e1954f061c10d08f1dbebb9a6d
	

	
//1. uztaisīt kontrolējošo funkciju, kas pados allDepartments uz frontend
	
@GetMapping("/department/showAll")
public String getAllDepartments (Model model) // localhost:8080/allDepartments
{
	if (employeeService.isLoggedIn()) {
		model.addAttribute("object", departmentService.readAllDepartments());
		return "all-department-page";
	}
	else {
		return "redirect:/login";
	}
	
}
	
	

@GetMapping ("/allDepartmentsFilter") //localhost:8080/allDepartmentsFilter?id=2
public String getAllDepartmentFilter (@RequestParam(name ="id")int id, Model model)
{
	if (employeeService.isLoggedIn()) {
		try {
			model.addAttribute("object", departmentService.readDepartmentById(id));
		} catch (Exception e) {
			return "error-page";
		}
		return "one-department-page";
	}
	else {
		return "redirect:/login";
	}
}


//nospiež un departmenta atlasa visus darbiniekus 


	//3. Apskatīties, kas ir @RequestParam un @PathVariable un uztaisīt kontrolējošās
	// funkcijas
@GetMapping ("/department/showAll/{id}")
public String getAllDepartmentsById (@PathVariable (name = "id")int id, Model model)
{
	if (employeeService.isLoggedIn()) {
		try {
			model.addAttribute("object", departmentService.readDepartmentById(id));
		} catch (Exception e) {
			return "error-page";
		}
		return "one-department-page";
	}
	else {
		return "redirect:/login";
	}
}


//1. ADD getMapping kontroliera funkcija, kas nosūtītu tukšu objektu

@GetMapping ("/addDepartment") // localhost:8080/addDepartment
public String getAddDepartment (Department department)

{
	if (employeeService.isLoggedIn()) {
		return "add-department-page";
	}
	else {
		return "redirect:/login";
	}
}

//2. uztaisīt produktu kur var iecvadīt visus objekta parametrus izņemot id

//3. postMapping funkcija, kas saņem aizpildīto objektu un saglabā sarakstā

@PostMapping ("/addDepartment")
public String postAddDepartment (@Valid Department department, BindingResult result) //Aizpildītais produkts
{
	if (employeeService.isLoggedIn()) {
		if (result.hasErrors()) {
			if(departmentService.createNewDepartment(department))
			return "redirect:/allDepartments"; //post norāda uz kuru adresi pāradresēt produktus
			else 
				return "redirect:/error";
		}
		else {
			return"add-department-page";
		}
	}
	else {
		return "redirect:/login";
	}
	
}


@GetMapping ("/updateDepartment/{id}")
public String getUpdateDepartment (@PathVariable (name = "id")int id, Model model)
{
	if (employeeService.isLoggedIn()) {
		try {
			model.addAttribute("object", departmentService.readDepartmentById(id));
		} catch (Exception e) {
			return "error-page";
		}
		return "update-department-page";
	}
	else {
		return "redirect:/login";
	}

}


@PostMapping ("/updateDepartment/{id}")
public String postUpdateDepartment (@PathVariable (name = "id")int id, @Valid Department department, BindingResult result)
	
{
	if (employeeService.isLoggedIn()) {
		if (result.hasErrors()) {
			if(departmentService.updateDepartmentById(id, department))
			return "redirect:/allDepartments" + id;
			else 
				return "redirect:/error";
		}
		else {
			return"update-department-page";
		}
	}
	else {
		return "redirect:/login";
	}
	
}

@GetMapping ("/error")
public String getError ()
{
	return "error-page";
}

//1. getMapping Dzēšam struktūrvienību


@GetMapping ("/deleteDepartment/{id}")
public String getDeleteDepartment (@PathVariable (name = "id")int id, Model model)// model backend uz frontend sūtīšana
{
	if (employeeService.isLoggedIn()) {
	
		if (departmentService.deleteDepartmentById(id))
		{	
			model.addAttribute("object", departmentService.readAllDepartments());
			return "all-department-page";
			
		}
	else {
		
		
	return "error-page";
	}
	}
	else {
		return "redirect:/login";
	}
	
}

<<<<<<< HEAD
//@GetMapping ("/allDepartmentEmployees/{id}")
//public String getAllEmployeesInDepartmentByDepartmentId (@PathVariable (name = "id")int id, Model model)
//{
//	try {
//		model.addAttribute("object", departmentService.readDepartmentById(id));
//		
//		return "one-department-page";
//		
//		
//		
//	} catch (Exception e) {
//		// TODO: handle exception
//		return "error-page";
//	}
//	
	
	
	
}

//@GetMapping("/employees/dept/{id}") //localhost:8080/employees/dept/3
//public String getEmployeesByDepartmentId(Model model, 
//                @PathVariable(name="id") int id)
//{
//        model.addAttribute("object", employeeService.getAllEmployeesFromDepartmentById(id));
//        return "employees-page";//atvers grades-page.html
//}


=======
>>>>>>> ad03bf5e8567a7e1954f061c10d08f1dbebb9a6d
}
