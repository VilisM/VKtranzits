package lv.vktranzits.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lv.vktranzits.demo.models.*;
import lv.vktranzits.demo.repos.*;
import lv.vktranzits.demo.services.*;
@Service
public class IDepartmentService implements DepartmentService {
	
	
	@Autowired
	private IEmployeeRepo employeeRepo;
	
	@Autowired
	private IDepartmentRepo departmentRepo;
	
	public List<Department> getAllDepartments(){
		return (List<Department>) departmentRepo.findAll();
	}
	 
	@Override
	public Page<Department> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return departmentRepo.findAll(pageable);
	}
	
	@Override
    public boolean insertNewEmployeeInDepartmentById(int departmentId, Employee employee) throws Exception {
		
		if(departmentRepo.existsById(departmentId))
		{
			
			if (employeeRepo.existsByNameAndSurname(employee.getName(), employee.getSurname()))
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
	public ArrayList<Employee> selectAllEmployeesInDepartmentById  (int departmentId)  throws Exception {

		if(employeeRepo.existsById(departmentId)) {
			
			return(ArrayList<Employee>) employeeRepo.findAll();
		}
		throw new Exception("Struktūrvienība neeksistē");
	
	}
	
	@Override
    public boolean createNewDepartment(Department department) {
		
		
		if (departmentRepo.existsByTitle(department.getTitle()))
			
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
			Department dep = departmentRepo.findById(departmentId).get();
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
			dep.setVsurname(department.getVsurname());
			
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
	


