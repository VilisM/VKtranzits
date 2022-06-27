package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Child;
import lv.venta.demo.models.ChildrenGroup;
import lv.venta.demo.models.department;
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
    public boolean createNewDepartment(Department department) {
		
		
		if (departmentRepo.existsByTitle(department.getTitle())
			
		{
			return false;
		}
		else {
			departmentRepo.save(department);
			return true;
		}
            
    }


	@Override
	public ArrayList<Department> readAllDepartments() {
	
//		return alldepartments;
		
		return(ArrayList<Department>) departmentRepo.findAll();
	}

	@Override
	public Department readDepartmentById(int departmentId) throws Exception {
		// throws Exception arī jāpievieno interfacam 
		
		if(departmentRepo.existsById(departmentId))
		{
			
			department dep = departmentRepo.findById(departmentId).get();
			return dep;
			
		}
		

		throw new Exception("Struktūrvienība neeksistē");
	}

	@Override
	public boolean updateDepartmentById(int departmentId, Department department) {
		
		
		if(departmentRepo.existsById(departmentId))
		{
			
			Department dep = departmentRepo.findById(departmentId).get();
			dep.setTitle(department.getTitle());
			dep.setVname(department.getVname());
			dep.setVSurname(department.getVSurname());
			
			departmentRepo.save(dep);
	
			return true;
			
		}
		
		

		return false;
	}

	@Override
	public boolean deleteDepartmentById(int departmentId) {
	

		if(departmentRepo.existsById(departmentId))
		{
			departmentRepo.deleteById(departmentId);
			return true;
		}
		

			return false;
		
}

		
		
	
}
	


