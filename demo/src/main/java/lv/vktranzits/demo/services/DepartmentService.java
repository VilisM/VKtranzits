package lv.vktranzits.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lv.vktranzits.demo.models.*;
import lv.vktranzits.demo.models.Employee;


public interface DepartmentService {
	
	boolean insertNewEmployeeInDepartmentById  (int departmentId, Employee employee) throws Exception;

	ArrayList<Employee> selectAllEmployeesInDepartmentById  (int departmentId) throws Exception;

	boolean createNewDepartment (Department department);
	
	ArrayList<Department> readAllDepartments ();

	Department readDepartmentById (int departmentId) throws Exception;
	
	boolean updateDepartmentById (int departmentId, Department department);
	
	boolean deleteDepartmentById (int departmentId);
	
	List<Department> getAllDepartments();

	Page<Department> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
