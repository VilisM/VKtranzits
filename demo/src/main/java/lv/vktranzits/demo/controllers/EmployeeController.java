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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import lv.vktranzits.demo.models.Department;
import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.services.DepartmentService;
import lv.vktranzits.demo.services.IEmployeeService;
import lv.vktranzits.demo.services.IPositionService;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private DepartmentService depService;

    @Autowired
    private IPositionService posService;

    @GetMapping("/employee/showAll/{id}")
    public String selectEmployeeById(@PathVariable(name = "id") int id, Model model){
            try {
                model.addAttribute("object", employeeService.selectEmployeeById(id));
                model.addAttribute("position", posService.selectAllEmployeePositions(id));
                return "one-employee-page";
            }
            catch(Exception e){
                return "error";
            }
    }

    @GetMapping("/employee/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam(name="sortField", defaultValue = "idEm") String sortField,
			@RequestParam(name="sortDir", defaultValue = "asc") String sortDir,
			Model model) {
		int pageSize = 5;
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "employee-show-all";
	}
    
    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable(name = "id") int id, Model model){
            if(employeeService.deleteEmployeeById(id)){
                model.addAttribute("object", employeeService.selectAllEmployees());
                return "redirect:/employee/page/1";
            }
            else{
                return"redirect:/employee/page/1";
            }
    }

    @GetMapping("/employee/add")
    public String getInsertNewEmployee(Model model){
            model.addAttribute("employee", new Employee());
            model.addAttribute("positions", posService.selectAllPositions());
            return "add-employee-page";
    }

    @PostMapping("/employee/add")
    public String postInsertNewEmployee(@Valid Employee employee, BindingResult result){
            if(!result.hasErrors()){
                if(employeeService.insertNewEmployee(employee))
                    return "redirect:/employee/page/1";
                else
                    return "redirect:/error";
            }
            else{
                return"add-employee-page";
            }
    }

    @GetMapping("/employee/update/{id}")
    public String getUpdateEmployee(@PathVariable(name = "id") int id, Model model){
            try {
                model.addAttribute("employee", employeeService.selectEmployeeById(id));
                model.addAttribute("department", depService.readAllDepartments());
                model.addAttribute("position", posService.selectAllPositions());
                return "update-employee-page";
            }
            catch(Exception e){
                return "error";
            }
    }

    @PostMapping("/employee/update/{id}")
    public String postUpdateEmployee(@PathVariable(name = "id") int id,@Valid Employee employee, BindingResult result, Model model){
            if(!result.hasErrors()){
                if(employeeService.updateEmployeeById(id, employee))
                    return "redirect:/employee/page/1";
                else
                    return "redirect:/error";
            }
            else{
                model.addAttribute("department", depService.readAllDepartments());
                model.addAttribute("position", posService.selectAllPositions());
                return"update-employee-page";
            }
    }
    
}
