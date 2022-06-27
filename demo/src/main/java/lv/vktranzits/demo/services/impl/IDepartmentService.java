package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Child;
import lv.venta.demo.models.ChildrenGroup;
import lv.venta.demo.repos.IChildRepo;
import lv.venta.demo.repos.IChildreGroupRepo;
import lv.venta.demo.services.ChildService;

@Service

public class IDepartmentService implements DepartmentService {
	
	
	@Autowired
	private IEmployeeRepo employeeRepo;
	
	@Autowired
	
	private IDepartmentRepo departmentRepo;
	 
	
	@Override
    public boolean insertNewEmployeeInDepartmentById(int departmentId, Employee employee) throws Exception {
		
		if(departmentRepo.existsById(departmentId))
		{
			
			if (employeeRepo.existsByNameAndSurname (employee.getName(),employee.getSurname()))
				
			{
				return false;
			}
			
			else {
				employeeRepo.save(employee);
				return true;
			}
		}
		throw new Exception("Struktūrvienība neeksistē");
			
		}
		
	
	@Override
	public ArrayList<Employee> selectAllEmployeesInDepartmentByDepartmentId  (int departmentId)  throws Exception {

		if(employeeRepo.existsById(departmentId)) {
			
			return(ArrayList<Employee>) employeeRepo.findAll();
		}
		throw new Exception("Struktūrvienība neeksistē");
	
	}
	
	
	@Override
	public boolean changeEmployeeByIdDepartmentById (int childId, int groupId) throws Exception {
		
		
		if(childRepo.existsById(childId))
		{
			if(childrenGroupRepo.existsById(groupId))
			{
				Child chi = childRepo.findById(childId).get();
				ChildrenGroup group = childrenGroupRepo.findById(groupId).get();
				chi.setChildrenGroup(group);

				return true;
			}
			
			return false;
	
			
		
			
		}
		throw new Exception("Bērns neeksistē");
		}
	
	
	
//	@Override
//	public boolean updateTeacherById(int teacherID, Teacher teacher) {
//	
//		
//		
//		if(teacherRepo.existsById(teacherID))
//		{
//			
//			Teacher teach = teacherRepo.findById(teacherID).get();
//			teach.setName(teacher.getName());
//			teach.setSurname(teacher.getSurname());
//			
//			teacherRepo.save(teach);
//	
//			return true;
//			
//		}
//		
//	
//		return false;
//	}
	
	@Override
	public boolean deleteChildByIdFromGroupById  (int childId, int groupId)throws Exception {
		// TODO Auto-generated method stub
		
		

		if(childRepo.existsById(childId))
		{
			if(childrenGroupRepo.existsById(groupId))
			{
			childrenGroupRepo.deleteById(childId);
			return true;
			}

			
		}
		
//		for (Product temp: allProducts)
//		{
//			if (temp.getId()==id)
//			{
//				
//				allProducts.remove (temp);
//		return true;
//	}
//		}
			throw new Exception("Bērns neeksistē");
		}	
}
	


