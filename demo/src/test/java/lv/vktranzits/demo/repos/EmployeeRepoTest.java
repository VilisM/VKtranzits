package lv.vktranzits.demo.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lv.vktranzits.demo.models.Employee;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepoTest {


    @Autowired IEmployeeRepo employeeRepo;

    //insert
@Test
void testInsert()
{

    Employee e1 = new Employee("John", "Green", 20000000, "john@gmail.com", "password" ) ;
    employeeRepo.save(e1);

    Employee returnedFromRepo = employeeRepo.findByNameAndSurname(e1.getName(),e1.getSurname());
    assertNotNull(returnedFromRepo);
    assertEquals(e1.getName(), returnedFromRepo.getName());
    assertEquals(e1.getSurname(), returnedFromRepo.getSurname());

    
}

//update
void testUpdate()
{

    Employee e1 = new Employee("John", "Green", 20000000, "john@gmail.com", "password" ) ;
    employeeRepo.save(e1);

    Employee returnedFromRepo = employeeRepo.findByNameAndSurname(e1.getName(),e1.getSurname());
    returnedFromRepo.setName("Janis");
    employeeRepo.save(returnedFromRepo);

    Employee reReturnedFromRepo = employeeRepo.findByNameAndSurname(returnedFromRepo.getName(),returnedFromRepo.getSurname());
    assertEquals("Janis", reReturnedFromRepo.getName());
    

    
}

//delete

void testDelete()
{

    Employee e1 = new Employee("John", "Green", 20000000, "john@gmail.com", "password" ) ;
    employeeRepo.save(e1);

    Employee returnedFromRepo = employeeRepo.findByNameAndSurname(e1.getName(),e1.getSurname());
    int id = returnedFromRepo.getIdEm();
    employeeRepo.delete(returnedFromRepo);

    Employee reReturnedFromRepo = employeeRepo.findByNameAndSurname(returnedFromRepo.getName(),returnedFromRepo.getSurname());
    assertNull(reReturnedFromRepo);   

    Employee reReturnedFromRepo2 = employeeRepo.findById(id).get();
    assertNull(reReturnedFromRepo2);    

    
}

    
}
