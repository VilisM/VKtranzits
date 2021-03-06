package lv.vktranzits.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vktranzits.demo.models.Department;
import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.models.Position;
import lv.vktranzits.demo.repos.*;
import lv.vktranzits.demo.services.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService  {

    @Autowired
    private IEmployeeRepo employeeRepo;

    @Autowired
    private IDepartmentRepo depRepo;

    @Autowired
    private IPositionRepo posRepo;

    private static boolean isLoggedIn = false;

    @Override
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    @Override
    public ArrayList<Employee> selectAllEmployees() {
        return (ArrayList<Employee>) employeeRepo.findAll();
    }

    @Override
    public Employee selectEmployeeById(int id) {
        return employeeRepo.findById(id).get();
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        if(employeeRepo.existsById(id)){
            Employee em = employeeRepo.findById(id).get();
            em.setPosition(null);
            em.setDepartment(null);
            employeeRepo.deleteById(id);
            
            return true;
        }
        return false;
    }

    @Override
    public boolean insertNewEmployee(Employee employee) {
        if(employeeRepo.existsByNameAndSurname(employee.getName(), employee.getSurname())){
            return false;
        }
        else {
            employeeRepo.save(employee);
            Employee em = employeeRepo.findById(employee.getIdEm()).get();
            em.setPasswordHashed(employee.getPassword());
            employeeRepo.save(employee);
            return true;
        }
    }

    @Override
    public boolean updateEmployeeById(int id, Employee employee) {
        if(employeeRepo.existsById(id)){
            Employee em = employeeRepo.findById(id).get();

            em.setName(employee.getName());
            em.setSurname(employee.getSurname());
            em.setPhone(employee.getPhone());
            em.setEmail(employee.getEmail());
            Department dep = depRepo.findById(employee.getDepartment().getIdDe()).get();
            Position pos = posRepo.findById(employee.getPosition().getIdPos()).get();
            em.setDepartment(dep);
            em.setPosition(pos);
            // em.setPasswordHashed(employee.getPassword());
            employeeRepo.save(em);
        
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Employee> selectAllEmployeesByPosition(String position) {
        return employeeRepo.findAllByPositionTitle(position);
    }

    @Override
    public boolean getEmployeeByEmailAndPassword(String email, String password) {
        if(employeeRepo.existsByEmail(email)){
            Employee em = employeeRepo.findByEmail(email);
            if(em.checkPassword(password)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Employee> selectAllEmployeesByDepartmentId(int departmentId) {
        return employeeRepo.findAllByDepartmentIdDe(departmentId);
    }
    
}
