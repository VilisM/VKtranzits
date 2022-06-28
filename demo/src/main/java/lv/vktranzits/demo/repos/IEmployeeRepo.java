package lv.vktranzits.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.Employee;

public interface IEmployeeRepo extends CrudRepository<Employee, Integer> {

    boolean existsByNameAndSurname(String name, String surname);

	public abstract ArrayList<Employee> findAllByDepartmentIdDe(int departmentId);

    ArrayList<Employee> findAllByPositionTitle(String position);

    boolean existsByEmailAndPassword(String email, String password);
    
}
