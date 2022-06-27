package lv.vktranzits.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Child;
import lv.venta.demo.models.ChildrenGroup;
import lv.venta.demo.models.Product;


public interface DepartmentService {
	
//	//pievieno jaunu darbinieku struktūrvienībā, ja ir zināms struktūrvienības id
	public abstract boolean insertNewEmployeeInDepartmentById  (int departmentId, Employee employee) throws Exception;
	
	
	//atgriež visus darbiniekus, kas ir struktūrvienībā, ja ir zināms struktūrvienības id
	
	public abstract ArrayList<Employee> selectAllEmployeesInDepartmentByDepartmentId  (int departmentId) throws Exception;
	
	

// pievieno jaunu struktūrvienību
//	public abstract boolean changeChildByIdGroupById (int childId, int groupId) throws Exception;
//	
//	
//	//izdzēš bērnu no grupiņas, ja ir zināms grupiņas id un bērna id
//	public abstract boolean deleteChildByIdFromGroupById  (int childId, int groupId)throws Exception;

	
	
	
	//CRUD
	//C - create a department
	public abstract boolean createNewDepartment (Department department);
	
	
	//R - read or retrieve all departments
	
	public abstract ArrayList<Department> readAllDepartments ();
	
	
	//R - read or retrieve one department

	public abstract Department readDepartmentById (int departmentId) throws Exception;
	
	//U - update department
	public abstract boolean updateDepartmentById (int departmentId, Product product);
	
	
	//D - delete department
	public abstract boolean deleteDepartmentById (int departmentId);
}
