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

import lv.vktranzits.demo.models.Department;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentRepoTest {

    @Autowired IDepartmentRepo departmentRepo;

    //insert
@Test
void testInsert()
{

    Department d1 = new Department("Krāvēji", "Joņs", "Joņs");
    departmentRepo.save(d1);

    Department returnedFromRepo = departmentRepo.findByTitle(d1.getTitle());
    assertNotNull(returnedFromRepo);
    assertEquals(d1.getTitle(), returnedFromRepo.getTitle());
    

    
}

//update
void testUpdate()
{

    Department d1 = new Department("Krāvēji", "Joņs", "Joņs");
    departmentRepo.save(d1);

    Department returnedFromRepo = departmentRepo.findByTitle(d1.getTitle());
    returnedFromRepo.setTitle("Celeji");
    departmentRepo.save(returnedFromRepo);

    Department reReturnedFromRepo = departmentRepo.findByTitle(returnedFromRepo.getTitle());
    assertEquals("Celeji", reReturnedFromRepo.getTitle());
    

    
}

//delete

void testDelete()
{

    Department d1 = new Department("Krāvēji", "Joņs", "Joņs");
    departmentRepo.save(d1);

    Department returnedFromRepo = departmentRepo.findByTitle(d1.getTitle());
    int id = returnedFromRepo.getIdDe();
    departmentRepo.delete(returnedFromRepo);

    Department reReturnedFromRepo = departmentRepo.findByTitle(returnedFromRepo.getTitle());
    assertNull(reReturnedFromRepo);   

    Department reReturnedFromRepo2 = departmentRepo.findById(id).get();
    assertNull(reReturnedFromRepo2);    

    
}


    
}
