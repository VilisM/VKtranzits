package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.Department;

public interface IDepartmentRepo extends CrudRepository<Department, Integer> {
    
}
