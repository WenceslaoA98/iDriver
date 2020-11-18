package pe.edu.login.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.login.idriver.entity.User;
import pe.edu.login.idriver.repository.UserRepository;
import pe.edu.login.idriver.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User create(User entity) throws Exception {
		return userRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> readAll() throws Exception {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) throws Exception {
		return userRepository.findById(id);
	}

	@Override
	public User update(User entity) throws Exception {
		return userRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		userRepository.deleteById(id);
	}

	@Override
	public void deleteByAll() throws Exception {
		userRepository.deleteAll();
	}

}
