package lv.vktranzits.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lv.vktranzits.demo.models.*;
import lv.vktranzits.demo.models.Employee;


public interface DepartmentService {
	
	public abstract boolean insertNewEmployeeInDepartmentById  (int departmentId, Employee employee) throws Exception;

	public abstract ArrayList<Employee> selectAllEmployeesInDepartmentById  (int departmentId) throws Exception;

	public abstract boolean createNewDepartment (Department department);
	
	public abstract ArrayList<Department> readAllDepartments ();

	public abstract Department readDepartmentById (int departmentId) throws Exception;
	
	public abstract boolean updateDepartmentById (int departmentId, Department department);
	
	public abstract boolean deleteDepartmentById (int departmentId);
	
	List<Department> getAllDepartments();

	Page<Department> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
