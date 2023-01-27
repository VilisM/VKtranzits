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
	
	private final Employee employee;

	public EmployeeDetails(Employee employee) {
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> allAuthoritiesForUser = new ArrayList<>();
		Iterator<Position> iter = employee.getAllPositions().iterator();
		while(iter.hasNext()) {
			allAuthoritiesForUser.add(new SimpleGrantedAuthority(iter.next().getTitle()));
		}
		return allAuthoritiesForUser;
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		return employee.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public Employee getEmployee() {
        return employee;
    }

}
