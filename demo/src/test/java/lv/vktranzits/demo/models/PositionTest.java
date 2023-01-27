package lv.vktranzits.demo.models;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.Position;
public class PositionTest {
  
    
    Position co = new Position ("Darbinieks", "Labs Stradnieks") ;
	// Position co2 = new Position (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testPositionCreation() {
		//atļauto datu saglabāšana
		assertEquals("Darbinieks", co.getTitle());
		assertEquals("Labs Stradnieks", co.getDescription());



}
}