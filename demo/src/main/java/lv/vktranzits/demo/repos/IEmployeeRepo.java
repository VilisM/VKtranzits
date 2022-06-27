package lv.vktranzits.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Grade;
import lv.vktranzits.demo.models.Employee;

public interface IEmployeeRepo extends CrudRepository<Employee, Integer> {

    boolean existsByNameAndSurname(String name, String surname);
<<<<<<< HEAD
	public abstract ArrayList<Employee> findByDepartmentId(int departmentId);

=======

    ArrayList<Employee> findAllByPositionTitle(String position);
>>>>>>> 40bcb2530f78637b42bf641c749a4a3a898fc282
    
}
