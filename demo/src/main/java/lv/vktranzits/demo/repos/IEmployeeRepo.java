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
<<<<<<< HEAD

    boolean existsByEmailAndPassword(String email, String password);
=======
>>>>>>> 40bcb2530f78637b42bf641c749a4a3a898fc282
>>>>>>> fe5e0d1aed4393cd9b2077aeaa9953004579cb6d
    
}
