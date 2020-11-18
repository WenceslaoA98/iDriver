package pe.edu.login.idriver.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.login.idriver.entity.User;
import pe.edu.login.idriver.repository.UserRepository;

@Service
public class UDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Optional<User> optional = this.userRepository.findByUsername(username);
			if (optional.isPresent()) {
				UDetails uDetails = new UDetails( optional.get() );
				return uDetails;
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new UsernameNotFoundException("El usuario ingresado no existe");	
	}

}





