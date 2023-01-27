package lv.vktranzits.demo.models.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.Implementer;

public class ImplementerValidatorTest {


    @Test
    void testPositiveImplementerValidation() {
        
        
        Implementer im = new Implementer ("Vaditajs" ) ;
        ValidatorFactory valF = Validation.buildDefaultValidatorFactory();
        Validator val = valF.getValidator();
        
        Set<ConstraintViolation<Implementer>> result = val.validate(im);
        assertEquals(0, result.size());
    
    }
    @Test
    void testNegativeImplementerValidation() {
        
        
        Implementer im = new Implementer ("" ) ;
        ValidatorFactory valF = Validation.buildDefaultValidatorFactory();
        Validator val = valF.getValidator();
        
        Set<ConstraintViolation<Implementer>> result = val.validate(im);
        assertEquals(1, result.size());
        String msg = result.iterator().next().getMessage();
        assertEquals("Nosaukums ir obligāts", msg);
    
    }


    
}
