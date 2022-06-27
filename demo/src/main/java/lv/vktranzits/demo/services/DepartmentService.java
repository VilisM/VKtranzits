package lv.vktranzits.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Child;
import lv.venta.demo.models.ChildrenGroup;


public interface DepartmentService {
	
//	//pievieno jaunu darbinieku struktūrvienībā, ja ir zināms struktūrvienības id
	public abstract boolean insertNewEmployeeInDepartmentById  (int departmentId, Employee employee) throws Exception;
	
	
	//atgriež visus darbiniekus, kas ir struktūrvienībā, ja ir zināms struktūrvienības id
	
	public abstract ArrayList<Employee> selectAllEmployeesInDepartmentByDepartmentId  (int departmentId) throws Exception;
	
	
//	
//	//- nomaina esošā bērna grupiņu uz jaunu, ja ir zināms jaunās grupiņas id
//	public abstract boolean changeChildByIdGroupById (int childId, int groupId) throws Exception;
//	
//	
//	//izdzēš bērnu no grupiņas, ja ir zināms grupiņas id un bērna id
//	public abstract boolean deleteChildByIdFromGroupById  (int childId, int groupId)throws Exception;

}
