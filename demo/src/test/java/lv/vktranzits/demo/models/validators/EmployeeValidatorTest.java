package lv.vktranzits.demo.models.validators;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.Employee;

public class EmployeeValidatorTest {
    
@Test
void testPositiveEmployeeValidation() {
    
    
    Employee e = new Employee("John", "Green", 20000000, "john@gmail.com", "password" ) ;
    ValidatorFactory valF = Validation.buildDefaultValidatorFactory();
    Validator val = valF.getValidator();
    
    Set<ConstraintViolation<Employee>> result = val.validate(e);
    assertEquals(0, result.size());

}
void testNegativeEmployeeValidation(){
    Employee e = new Employee("", "Green", 20000000, "john@gmail.com", "password" ) ;
    ValidatorFactory valF = Validation.buildDefaultValidatorFactory();
    Validator val = valF.getValidator();
    
    Set<ConstraintViolation<Employee>> result = val.validate(e);
    assertEquals(1, result.size());
    String msg = result.iterator().next().getMessage();
    assertEquals("Vārds ir obligāts", msg);


}

}
