package lv.vktranzits.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CompanyTest {
   
    Company co = new Company ("VK Tranzits") ;
	// Company co2 = new Company (12345667, 3453456, "carry", "johngmail.com", "password");
	
	@Test
	void testCompanyCreation() {
		//atļauto datu saglabāšana
		assertEquals("VK Tranzits", co.getTitle());

	}

}
