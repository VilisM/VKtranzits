package lv.vktranzits.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lv.vktranzits.demo.models.*;


public interface IEmployeeService {

    //public Page<Employee> findAll(int pageNr);


    ArrayList<Employee> selectAllEmployees();

    Employee selectEmployeeById(int id);

    boolean deleteEmployeeById(int id);

    boolean insertNewEmployee(Employee course);

    boolean updateEmployeeById(int id, Employee employee);

    ArrayList<Employee> selectAllEmployeesByPosition(String position);

    ArrayList<Employee> selectAllEmployeesByDepartmentId(int departmentId);

	List<Employee> getAllEmployees();

	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    
    Employee selectEmployeeByEmail(String email);

    boolean checkIfValidOldPasswordAndChangePassword(int id, String oldPass, String newPass);
    
}
