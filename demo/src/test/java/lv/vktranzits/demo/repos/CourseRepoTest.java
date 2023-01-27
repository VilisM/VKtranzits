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

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.models.CourseType;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepoTest {

    @Autowired ICourseRepo courseRepo;
    @Autowired ICourseTypeRepo courseTypeRepo;

    //insert
@Test
void testInsert()
{
    CourseType t1 = new CourseType(true, "Piecas reizi gadā");
   courseTypeRepo.save(t1);

    Course c1 = new Course("Programmētājs", "te kaut kada informācija", t1);
    courseRepo.save(c1);

    Course returnedFromRepo = courseRepo.findByTitle(c1.getTitle());
    assertNotNull(returnedFromRepo);
    assertEquals(c1.getTitle(), returnedFromRepo.getTitle());
    

    
}

//update
void testUpdate()
{

    CourseType t1 = new CourseType(true, "Piecas reizi gadā");
   courseTypeRepo.save(t1);

    Course c1 = new Course("Programmēšana", "te kaut kada informācija", t1);
    courseRepo.save(c1);

    Course returnedFromRepo = courseRepo.findByTitle(c1.getTitle());
    returnedFromRepo.setTitle("Celšana");
    courseRepo.save(returnedFromRepo);

    Course reReturnedFromRepo = courseRepo.findByTitle(returnedFromRepo.getTitle());
    assertEquals("Celšana", reReturnedFromRepo.getTitle());
    

    
}

//delete

void testDelete()
{

    CourseType t1 = new CourseType(true, "Piecas reizi gadā");
   courseTypeRepo.save(t1);

   Course c1 = new Course("Programmēšana", "te kaut kada informācija", t1);
   courseRepo.save(c1);

    Course returnedFromRepo = courseRepo.findByTitle(c1.getTitle());
    int id = returnedFromRepo.getIdCou();
    courseRepo.delete(returnedFromRepo);

    Course reReturnedFromRepo = courseRepo.findByTitle(returnedFromRepo.getTitle());
    assertNull(reReturnedFromRepo);   

    Course reReturnedFromRepo2 = courseRepo.findById(id).get();
    assertNull(reReturnedFromRepo2);    

    
}


    
}
