package pe.edu.login.idriver.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import pe.edu.login.idriver.entity.User;

public class UDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	// Inyección de dependencia
	private User user;
	public UDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedRoles = new ArrayList<>();
		this.user.getRoles().forEach(role -> {
			GrantedAuthority grantedRole = new SimpleGrantedAuthority(role.getNameRole());
			grantedRoles.add(grantedRole);
		});
		return grantedRoles;
	}


	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.user.isEnable();
	}

	
}
