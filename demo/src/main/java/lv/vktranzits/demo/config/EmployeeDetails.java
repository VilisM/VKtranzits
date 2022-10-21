package lv.vktranzits.demo.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.models.Position;


public class EmployeeDetails implements UserDetails  {
	
	private Employee employee;

	public EmployeeDetails(Employee employee) {
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> allAuthoritiesForUser = new ArrayList<>();
		Iterator<Position> iter = employee.getAllPositions().iterator();
		while(iter.hasNext()) {
			allAuthoritiesForUser.add(new SimpleGrantedAuthority(iter.next().getTitle()));
		}
		return allAuthoritiesForUser;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
