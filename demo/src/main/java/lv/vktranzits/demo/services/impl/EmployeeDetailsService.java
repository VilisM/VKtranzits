package lv.vktranzits.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lv.vktranzits.demo.config.EmployeeDetails;
import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.repos.IEmployeeRepo;

@Service
public class EmployeeDetailsService implements UserDetailsService {
	
	@Autowired
	private IEmployeeRepo employeeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee user = employeeRepo.findByEmail(email);
		if(user != null) {
			EmployeeDetails details = new EmployeeDetails(user);
			return details;
		} else {
			throw new UsernameNotFoundException(email + " not found");
		}
	}
	
	
	
}
