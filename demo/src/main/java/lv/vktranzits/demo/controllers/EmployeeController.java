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
import lv.vktranzits.demo.services.IEmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/employee/showAll")
    public String selectAllEmployees(Model model){
        model.addAttribute("object", employeeService.selectAllEmployees());
        return "employee-show-all";
    }

    @GetMapping("/employee/showAll/{id}")
    public String selectEmployeeById(@PathVariable(name = "id") int id, Model model){
        try{
            model.addAttribute("object", employeeService.selectEmployeeById(id));
            return "one-employee-page";
        }
        catch(Exception e){
            return "error";
        }
    }

    @GetMapping("/employee/show")
    public String selectEmployeesByPosition(@RequestParam(name = "position") String position, Model model){
        try{
            model.addAttribute("object", employeeService.selectAllEmployeesByPosition(position));
            return "employee-show-all";
        }
        catch(Exception e){
            return "error";
        }
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable(name = "id") int id, Model model){
        if(employeeService.deleteEmployeeById(id)){
            model.addAttribute("object", employeeService.selectAllEmployees());
            return "employee-show-all";
        }
        else{
            return"redirect:/employee/showAll";
        }
    }

    @GetMapping("/employee/add")
    public String getInsertNewEmployee(Employee employee){
        return "add-employee-page";
    }

    @PostMapping("/employee/add")
    public String postInsertNewEmployee(@Valid Employee employee, BindingResult result){
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

    @GetMapping("/employee/update/{id}")
    public String getUpdateEmployee(@PathVariable(name = "id") int id, Model model){
        try{
            model.addAttribute("employee", employeeService.selectEmployeeById(id));
            return "update-employee-page";
        }
        catch(Exception e){
            return "error";
        }
    }

    @PostMapping("/employee/update/{id}")
    public String postUpdateEmployee(@PathVariable(name = "id") int id,@Valid Employee employee, BindingResult result){
        if(!result.hasErrors()){
            if(employeeService.updateEmployeeById(id, employee))
                return "redirect:/employee/showAll/" + id;
            else
                return"redirect:/error";
        }
        else{
            return "update-employee-page";
        }
    }
    
}
