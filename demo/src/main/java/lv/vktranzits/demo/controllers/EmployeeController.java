package lv.vktranzits.demo.controllers;

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


    // @GetMapping("/employee/showAll")
    // public String selectAllEmployees(Model model){
    //         model.addAttribute("object", employeeService.selectAllEmployees());
    //         return "employee-show-all";
    // }

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

    // @GetMapping("/employee/page/{pageNr}/{id}")
    // public String selectEmployeeById( @PathVariable("pageNr") int currentPage, @PathVariable(name = "id")  int id, Model model){
    //         try {
    //             model.addAttribute("object", employeeService.selectEmployeeById(id));
    //             return "one-employee-page";
    //         }
    //         catch(Exception e){
    //             return "error";
    //         }
    // }

    // @RequestMapping("/employee/showAll")
	// public String getEmployees(Model model) {
	// 	return employeesByPage(model,10);
	// }
	
	// private String employeesByPage(Model model, int i) {
    //     return null;
    // }

    @GetMapping("/employee/page/{pageNr}")
	public String productsByPage(Model model, @PathVariable("pageNr") int currentPage) {
		Page<Employee> page = employeeService.findAll(currentPage);
        
		model.addAttribute("object", page);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		return "employee-show-all";
	}

//    @GetMapping("/employee/show")
//    public String selectEmployeesByPosition(@RequestParam(name = "position") String position, Model model){
//            model.addAttribute("object", employeeService.selectAllEmployeesByPosition(position));
//            return "employee-show-all";
//    }

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

	// @PostMapping("/addProduct2")
	// public ResponseEntity<?> postAddProduct2(@RequestBody @Valid Product product, BindingResult result)//aizpildītais produkts
	// {
	// 	if(!result.hasErrors())
	// 	{
	// 		if(productCRUDService.createNewProduct(product))
	// 			return new ResponseEntity<>(HttpStatus.OK);
	// 		else
	// 			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	// 	}
	// 	else
	// 	{
	// 		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	// 	}
			
	// }
	
	
	// //1.uztaisīt kontrolējošo funkciju, kas pados allProducts uz frontend
	// 	@GetMapping("/allProducts2") //url - localhost:8080/allProducts2
	// 	public ResponseEntity<?> getAllProducts2()
	// 	{
	// 		//model.addAttribute("object", productCRUDService.readAllProducts());
	// 		//return "all-product-page";//all-product-page.html
	// 		return new ResponseEntity<>(productCRUDService.readAllProducts(), HttpStatus.OK);
	// 	}

//todo 

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
