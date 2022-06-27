package lv.vktranzits.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.services.IEmployeeService;

@Controller
public class LoginController {

	@Autowired
	private IEmployeeService employeeService;
    
	@GetMapping("/login")
	public String login(Employee employee) {
		return "login";
	}

	@PostMapping("/login")
    public String postInsertNewEmployee(Employee employee){
            if(employeeService.getEmployeeByEmailAndPassword(employee.getEmail(), employee.getPassword())) {
				employeeService.setLoggedIn(true);
                return "redirect:/employee/showAll";
			}
            else {
                return "login";
			}
    }
}
