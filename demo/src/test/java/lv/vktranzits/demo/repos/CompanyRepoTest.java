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

import lv.vktranzits.demo.models.Company;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyRepoTest {

    @Autowired ICompanyRepo companyRepo;

    //insert
@Test
void testInsert()
{

    Company c1 = new Company("Tranzits");
    companyRepo.save(c1);

    Company returnedFromRepo = companyRepo.findByTitle(c1.getTitle());
    assertNotNull(returnedFromRepo);
    assertEquals(c1.getTitle(), returnedFromRepo.getTitle());
    

    
}

//update
void testUpdate()
{

    Company c1 = new Company("Tranzits");
    companyRepo.save(c1);

    Company returnedFromRepo = companyRepo.findByTitle(c1.getTitle());
    returnedFromRepo.setTitle("Apvedcels");
    companyRepo.save(returnedFromRepo);

    Company reReturnedFromRepo = companyRepo.findByTitle(returnedFromRepo.getTitle());
    assertEquals("Apvedcels", reReturnedFromRepo.getTitle());
    

    
}

//delete

void testDelete()
{

    Company c1 = new Company("Tranzits");
    companyRepo.save(c1);

    Company returnedFromRepo = companyRepo.findByTitle(c1.getTitle());
    int id = returnedFromRepo.getIdCo();
    companyRepo.delete(returnedFromRepo);

    Company reReturnedFromRepo = companyRepo.findByTitle(returnedFromRepo.getTitle());
    assertNull(reReturnedFromRepo);   

    Company reReturnedFromRepo2 = companyRepo.findById(id).get();
    assertNull(reReturnedFromRepo2);    

    
}


    
}
