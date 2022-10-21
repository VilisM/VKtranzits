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
	
	@Autowired
	private IEmployeeService employeeService;
	

	
//1. uztaisīt kontrolējošo funkciju, kas pados allDepartments uz frontend
	
@GetMapping("/department/showAll")
public String getAllDepartments (Model model) // localhost:8080/allDepartments
{
	model.addAttribute("object", departmentService.readAllDepartments());
	return "all-department-page";
	
	
}
	
	

@GetMapping ("/allDepartmentsFilter") //localhost:8080/allDepartmentsFilter?id=2
public String getAllDepartmentFilter (@RequestParam(name ="id")int id, Model model)
{
		try {
			model.addAttribute("object", departmentService.readDepartmentById(id));
			model.addAttribute("employees", employeeService.selectAllEmployeesByDepartmentId(id));
		} catch (Exception e) {
			return "error-page";
		}
		return "one-department-page";
}


//nospiež un departmenta atlasa visus darbiniekus 


	//3. Apskatīties, kas ir @RequestParam un @PathVariable un uztaisīt kontrolējošās
	// funkcijas
@GetMapping ("/department/showAll/{id}")
public String getAllDepartmentsById (@PathVariable (name = "id")int id, Model model)
{
		try {
			model.addAttribute("object", departmentService.readDepartmentById(id));
		} catch (Exception e) {
			return "error-page";
		}
		return "one-department-page";
}


//1. ADD getMapping kontroliera funkcija, kas nosūtītu tukšu objektu

@GetMapping ("/addDepartment") // localhost:8080/addDepartment
public String getAddDepartment (Department department)

{
	return "add-department-page";
}

//2. uztaisīt produktu kur var iecvadīt visus objekta parametrus izņemot id

//3. postMapping funkcija, kas saņem aizpildīto objektu un saglabā sarakstā

@PostMapping ("/addDepartment")
public String postAddDepartment (@Valid Department department, BindingResult result) //Aizpildītais produkts
{
		if (!result.hasErrors()) {
			if(departmentService.createNewDepartment(department))
			return "redirect:/department/showAll"; //post norāda uz kuru adresi pāradresēt produktus
			else 
				return "redirect:/error";
		}
		else {
			return"add-department-page";
		}
	
}


@GetMapping ("/updateDepartment/{id}")
public String getUpdateDepartment (@PathVariable (name = "id")int id, Model model)
{
		try {
			model.addAttribute("department", departmentService.readDepartmentById(id));
			return "update-department-page";

		} catch (Exception e) {
			return "error-page";
		}

}


@PostMapping ("/updateDepartment/{id}")
public String postUpdateDepartment (@PathVariable (name = "id")int id, @Valid Department department, BindingResult result)
{
		if (!result.hasErrors()) {
			if(departmentService.updateDepartmentById(id, department))
				return "redirect:/allDepartmentsFilter?id=" + id;
			else 
				return "redirect:/error";
		}
		else {
			return "update-department-page";
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
	
	if (departmentService.deleteDepartmentById(id))
		{	
		model.addAttribute("object", departmentService.readAllDepartments());
		return "all-department-page";
	}
	else {
		return "update-department-page";
	}
	
}

}
