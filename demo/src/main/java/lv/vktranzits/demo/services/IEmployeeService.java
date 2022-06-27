package lv.vktranzits.demo.services;

import java.util.ArrayList;
import lv.vktranzits.demo.models.*;


public interface IEmployeeService {

    public abstract ArrayList<Employee> selectAllEmployees();

    public abstract Employee selectEmployeeById(int id);

    public abstract boolean deleteEmployeeById(int id);

    public abstract boolean insertNewEmployee(Employee course);

    public abstract boolean updateEmployeeById(int id, Employee course);

    public abstract ArrayList<Employee> selectAllEmployeesByPosition(String position);

    public abstract boolean getEmployeeByEmailAndPassword(String email, String password);

    public abstract void setLoggedIn(boolean loggedIn);

    public abstract boolean isLoggedIn();
    
}
