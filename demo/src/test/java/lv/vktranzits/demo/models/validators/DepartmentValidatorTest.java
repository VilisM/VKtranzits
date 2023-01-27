package lv.vktranzits.demo.models.validators;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.Department;

public class DepartmentValidatorTest {


    @Test
    void testPositiveDepartmentValidation() {
        
        
        Department d = new Department ("Kraveji","Janis", "Berzins" ) ;
        ValidatorFactory valF = Validation.buildDefaultValidatorFactory();
        Validator val = valF.getValidator();
        
        Set<ConstraintViolation<Department>> result = val.validate(d);
        assertEquals(0, result.size());
    
    }


    

    
}
