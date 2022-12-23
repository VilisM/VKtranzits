package lv.vktranzits.demo.models;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lv.vktranzits.demo.models.Implementer;

public class ImplementerTest {
    

    Implementer co = new Implementer ("Vaditajs") ;
	// Implementer co2 = new Implementer (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testImplementerCreation() {
		//atļauto datu saglabāšana
		assertEquals("Vaditajs", co.getTitle());
		
}
}