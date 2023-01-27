package lv.vktranzits.demo.models.validators;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.Company;
public class CompanyValidatorTest {
    
    @Test
    void testPositiveCompanyValidation() {
        
        
        Company e = new Company("Vktranzits" ) ;
        ValidatorFactory valF = Validation.buildDefaultValidatorFactory();
        Validator val = valF.getValidator();
        
        Set<ConstraintViolation<Company>> result = val.validate(e);
        assertEquals(0, result.size());
    
    }
    void testNegativeCompanyValidation() {
        
        
        Company e = new Company("" ) ;
        ValidatorFactory valF = Validation.buildDefaultValidatorFactory();
        Validator val = valF.getValidator();
        
        Set<ConstraintViolation<Company>> result = val.validate(e);
        assertEquals(1, result.size());
        String msg = result.iterator().next().getMessage();
        assertEquals("Nosaukums ir obligāts", msg);
    
    }



}
