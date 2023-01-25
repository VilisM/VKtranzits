package lv.vktranzits.demo.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import lv.vktranzits.demo.models.*;


public interface IEmployeeService {

    public Page<Employee> findAll(int pageNr);


    public abstract ArrayList<Employee> selectAllEmployees();

    public abstract Employee selectEmployeeById(int id);

    public abstract boolean deleteEmployeeById(int id);

    public abstract boolean insertNewEmployee(Employee course);

    public abstract boolean updateEmployeeById(int id, Employee employee);

    public abstract ArrayList<Employee> selectAllEmployeesByPosition(String position);

    public abstract ArrayList<Employee> selectAllEmployeesByDepartmentId(int departmentId);

    public abstract Employee selectEmployeeByEmail(String email);

    public abstract boolean checkIfValidOldPasswordAndChangePassword(int id, String oldPass, String newPass);
    
}
