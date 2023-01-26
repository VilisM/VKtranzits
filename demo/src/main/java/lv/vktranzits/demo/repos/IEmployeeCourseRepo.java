package lv.vktranzits.demo.repos;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.EmployeeCourse;

public interface IEmployeeCourseRepo extends CrudRepository<EmployeeCourse , Integer> {

    boolean existsByTitle(String title);

    ArrayList<EmployeeCourse> findTop5ByEmployeeIdEmOrderByDateDesc(int employeeId);

    @Query(value ="SELECT DISTINCT title FROM employee_course WHERE id_em = ?1", nativeQuery = true)
    ArrayList<String> findDistinctTitleByEmployeeIdEm(int employeeId);

    ArrayList<EmployeeCourse> findAllByTitleAndEmployeeIdEmOrderByDateDesc(String title, int employeeId);

    @Query(value ="SELECT AVG(value_pr) FROM employee_course WHERE title = ?1 AND id_em = ?2", nativeQuery = true)
    double getAverageGradeByTitleAndEmployeeId(String title, int employeeId);
    
}
