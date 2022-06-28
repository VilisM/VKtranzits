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


    @GetMapping("/employee/showAll")
    public String selectAllEmployees(Model model){
        if (employeeService.isLoggedIn()) {
            model.addAttribute("object", employeeService.selectAllEmployees());
            return "employee-show-all";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/employee/showAll/{id}")
    public String selectEmployeeById(@PathVariable(name = "id") int id, Model model){
        if (employeeService.isLoggedIn()) {
            try {
                model.addAttribute("object", employeeService.selectEmployeeById(id));
                return "one-employee-page";
            }
            catch(Exception e){
                return "error";
            }
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/employee/show")
    public String selectEmployeesByPosition(@RequestParam(name = "position") String position, Model model){
        if (employeeService.isLoggedIn()) {
            model.addAttribute("object", employeeService.selectAllEmployeesByPosition(position));
            return "employee-show-all";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable(name = "id") int id, Model model){
        if (employeeService.isLoggedIn()) {
            if(employeeService.deleteEmployeeById(id)){
                model.addAttribute("object", employeeService.selectAllEmployees());
                return "employee-show-all";
            }
            else{
                return"redirect:/employee/showAll";
            }
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/employee/add")
    public String getInsertNewEmployee(Employee employee){
        if (employeeService.isLoggedIn()) {
            return "add-employee-page";
        }
        else {
            return "redirect:/login";
        }
    }

    @PostMapping("/employee/add")
    public String postInsertNewEmployee(@Valid Employee employee, BindingResult result){
        if (employeeService.isLoggedIn()) {
            if(!result.hasErrors()){
                if(employeeService.insertNewEmployee(employee))
                    return "redirect:/employee/showAll";
                else
                    return "redirect:/error";
            }
            else{
                return"add-employee-page";
            }
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/employee/update/{id}")
    public String getUpdateEmployee(@PathVariable(name = "id") int id, Model model){
        if (employeeService.isLoggedIn()) {
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
        else {
            return "redirect:/login";
        }
    }

    @PostMapping("/employee/update/{id}")
    public String postUpdateEmployee(@PathVariable(name = "id") int id,@Valid Employee employee, BindingResult result){
        if (employeeService.isLoggedIn()) {
            if(!result.hasErrors()){
                if(employeeService.updateEmployeeById(id, employee))
                    return "redirect:/employee/showAll/" + id;
                else
                    return "redirect:/error";
            }
            else{
                return"update-employee-page";
            }
        }
        else {
            return "redirect:/login";
        }
    }
    
}
