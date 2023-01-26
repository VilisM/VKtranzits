package lv.vktranzits.demo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.services.IEmployeeCourseService;
import lv.vktranzits.demo.services.IEmployeeService;

@Controller
public class ProfileController {
    
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IEmployeeCourseService employeeCourseService;

    @GetMapping("/profile")
    public String showEmployeeProfile(Principal principal, Model model){
            try {
                String email = principal.getName();
                Employee employee = employeeService.selectEmployeeByEmail(email);
                model.addAttribute("employee", employee);
                model.addAttribute("grades", employeeCourseService.selectEmployeeResultsByEmployeeId(employee.getIdEm()));
                model.addAttribute("employeecourses", employeeCourseService.selectAllEmployeeCourses(employee.getIdEm()));
                return "profile-page";
            }
            catch(Exception e){
                return "error";
            }
    }

    @GetMapping("/profile/viewCourse/{title}")
    public String showEmployeeSpecificCourse(@PathVariable (value = "title") String title, Principal principal, Model model){
            try {
                String email = principal.getName();
                Employee employee = employeeService.selectEmployeeByEmail(email);
                model.addAttribute("grades", employeeCourseService.selectEmployeeSpecificCourse(title, employee.getIdEm()));
                model.addAttribute("avgGrade", employeeCourseService.getEmployeeSpecificCourseAverageGrade(title, employee.getIdEm()));
                return "profile-view-course";
            }
            catch(Exception e){
                return "error";
            }
    }

    @GetMapping("/profile/changepassword")
    public String getUpdateEmployee(Principal principal, Model model){
            try {
                String email = principal.getName();
                Employee employee = employeeService.selectEmployeeByEmail(email);
                model.addAttribute("employee", employee);
                return "profile-change-password";
            }
            catch(Exception e){
                return "error";
            }
    }

    @PostMapping("/profile/changepassword")
    public String postUpdateEmployee(Principal principal, @RequestParam("password") String password, 
    @RequestParam("oldpassword") String oldPassword){
                String email = principal.getName();
                Employee emp = employeeService.selectEmployeeByEmail(email);
                if(employeeService.checkIfValidOldPasswordAndChangePassword(emp.getIdEm(), oldPassword, password))
                    return "redirect:/profile";
                else
                    return "redirect:/profile/changepassword";
    }

    
}
