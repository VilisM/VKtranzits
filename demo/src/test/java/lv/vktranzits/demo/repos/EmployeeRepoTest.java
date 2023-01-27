package lv.vktranzits.demo.repos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lv.vktranzits.demo.models.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepoTest {
	
	@Autowired
	IEmployeeRepo empRepo;
	
    
	@Test
	void testInsertEmployee() {
		Employee employee = new Employee("Janis", "Berzins", 22222222, "janisberzins@inbox.lv", "1234567");
	    empRepo.save(employee);
	    
	    Employee repoGetEmployee = empRepo.findByEmail(employee.getEmail());
	    assertNotNull(repoGetEmployee);
	    assertEquals(employee.getName(), repoGetEmployee.getName());
	    assertEquals(employee.getSurname(), repoGetEmployee.getSurname());
	    assertEquals(employee.getPhone(), repoGetEmployee.getPhone());
	    assertEquals(employee.getEmail(), repoGetEmployee.getEmail());
	}
	
	@Test
	void testUpdateEmployee() {
		Employee employee = new Employee("Janis", "Berzins", 22222222, "janisberzins@inbox.lv", "1234567");
	    empRepo.save(employee);
	    
	    Employee repoGetEmployee = empRepo.findByEmail(employee.getEmail());
	    repoGetEmployee.setName("Janka");
	    empRepo.save(repoGetEmployee);
	    
	    Employee reRepoGetEmployee = empRepo.findByEmail(repoGetEmployee.getEmail());
	    assertEquals("Janka", reRepoGetEmployee.getName());
	    
	}

}
