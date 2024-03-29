package lv.vktranzits.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lv.vktranzits.demo.models.Department;
import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.models.Position;
import lv.vktranzits.demo.repos.*;
import lv.vktranzits.demo.services.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService  {

    @Autowired
    private IEmployeeRepo employeeRepo;

    @Autowired
    private IDepartmentRepo depRepo;

    @Autowired
    private IPositionRepo posRepo;

 
    
    @Override
	public List<Employee> getAllEmployees() {
		return (List<Employee>) employeeRepo.findAll();
	}
	
	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return employeeRepo.findAll(pageable);
	}

    @Override
    public ArrayList<Employee> selectAllEmployees() {
        return (ArrayList<Employee>) employeeRepo.findAll();
    }

    @Override
    public Employee selectEmployeeById(int id) {
        return employeeRepo.findById(id).get();
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        if(employeeRepo.existsById(id)){
            Employee em = employeeRepo.findById(id).get();
            em.setAllPositions(null);
            em.setDepartment(null);
            employeeRepo.deleteById(id);
            
            return true;
        }
        return false;
    }

    @Override
    public boolean insertNewEmployee(Employee employee) {
        if(employeeRepo.existsByNameAndSurname(employee.getName(), employee.getSurname())){
            return false;
        }
        else {
            employeeRepo.save(employee);
            Employee em = employeeRepo.findById(employee.getIdEm()).get();
            em.setPasswordHashed(employee.getPassword());
            em.setAllPositions(employee.getAllPositions());
            for (Position pos : employee.getAllPositions()) {
                pos.addEmployee(em);
                posRepo.save(pos);
            }
            employeeRepo.save(employee);
            return true;
        }
    }

    @Override
    public boolean updateEmployeeById(int id, Employee employee) {
        if(employeeRepo.existsById(id)){
            Employee em = employeeRepo.findById(id).get();

            em.setName(employee.getName());
            em.setSurname(employee.getSurname());
            em.setPhone(employee.getPhone());
            em.setEmail(employee.getEmail());
            Department dep = depRepo.findById(employee.getDepartment().getIdDe()).get();
            em.setDepartment(dep);

            for (Position pos : em.getAllPositions()) {
                pos.deleteEmployee(em);
                posRepo.save(pos);
            }

            em.setAllPositions(employee.getAllPositions());
            for (Position pos : employee.getAllPositions()) {
                pos.addEmployee(em);
                posRepo.save(pos);
            }

            employeeRepo.save(em);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Employee> selectAllEmployeesByPosition(String position) {
        return employeeRepo.findAllByAllPositionsTitle(position);
    }
   

    @Override
    public ArrayList<Employee> selectAllEmployeesByDepartmentId(int departmentId) {
        return employeeRepo.findAllByDepartmentIdDe(departmentId);
    }

    @Override
    public Employee selectEmployeeByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }

    @Override
    public boolean checkIfValidOldPasswordAndChangePassword(int id, String oldPass, String newPass) {
        if(employeeRepo.existsById(id)){
            Employee em = employeeRepo.findById(id).get();
            if (em.checkPassword(oldPass)) {
                em.setPasswordHashed(newPass);
                return true;
            }
        }
        return false;
    }

    

    
}
