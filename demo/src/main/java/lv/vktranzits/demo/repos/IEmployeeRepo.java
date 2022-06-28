package lv.vktranzits.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.Employee;

public interface IEmployeeRepo extends CrudRepository<Employee, Integer> {

    boolean existsByNameAndSurname(String name, String surname);

	ArrayList<Employee> findAllByDepartmentIdDe(int departmentId);

    ArrayList<Employee> findAllByPositionTitle(String position);

    boolean existsByEmail(String email);

    Employee findByEmail(String email);
    
}
