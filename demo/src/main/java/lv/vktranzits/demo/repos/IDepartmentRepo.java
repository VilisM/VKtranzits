package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import lv.vktranzits.demo.models.Department;

public interface IDepartmentRepo extends PagingAndSortingRepository<Department, Integer> {
    
	boolean existsByTitle(String title);
}
