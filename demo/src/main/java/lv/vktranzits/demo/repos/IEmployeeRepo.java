package lv.vktranzits.demo.repos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import lv.vktranzits.demo.models.Employee;

public interface IEmployeeRepo extends PagingAndSortingRepository<Employee, Integer> {

    boolean existsByNameAndSurname(String name, String surname);

	ArrayList<Employee> findAllByDepartmentIdDe(int departmentId);

    boolean existsByEmail(String email);

    Employee findByEmail(String email);
    
    ArrayList<Employee> findAllByAllPositionsTitle(String title);

    Employee findByNameAndSurname(String name, String surname);
    
}
