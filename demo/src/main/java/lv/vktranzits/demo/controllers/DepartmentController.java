package lv.vktranzits.demo.controllers;
import java.util.List;

import javax.validation.Valid;

import lv.vktranzits.demo.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	
	
//@GetMapping("/department/showAll")
//public String getAllDepartments (Model model)
//{
//	model.addAttribute("object", departmentService.readAllDepartments());
//	return "all-department-page";	
//}

@GetMapping("/department/page/{pageNo}")
public String findPaginated(@PathVariable (value="pageNo") int pageNo,
		@RequestParam(name="sortField", defaultValue = "idDe") String sortField,
		@RequestParam(name="sortDir", defaultValue = "asc") String sortDir,
		Model model) {
			
	int pageSize = 10;
	Page<Department> page = departmentService.findPaginated(pageNo, pageSize, sortField, sortDir);
	List<Department> listDepartment = page.getContent();
	model.addAttribute("currentPage", pageNo);
	model.addAttribute("totalPages" , page.getTotalPages());
	model.addAttribute("totalElements", page.getTotalElements());
	
	model.addAttribute("sortField", sortField);
	model.addAttribute("sortDir", sortDir);
	model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	
	model.addAttribute("listDepartments", listDepartment);
	System.out.println(listDepartment);
	return "all-department-page";
}


@GetMapping ("/allDepartmentsFilter")
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

@GetMapping ("/addDepartment") // localhost:8080/addDepartment
public String getAddDepartment (Department department)

{
	return "add-department-page";
}

@PostMapping ("/addDepartment")
public String postAddDepartment (@Valid Department department, BindingResult result)
{
		if (!result.hasErrors()) {
			if(departmentService.createNewDepartment(department))
			return "redirect:/department/showAll"; 
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
