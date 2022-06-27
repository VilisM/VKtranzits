package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.Employee;

public interface IEmployeeRepo extends CrudRepository<Employee, Integer> {
    
}
