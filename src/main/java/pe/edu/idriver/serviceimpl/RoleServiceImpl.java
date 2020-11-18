package pe.edu.login.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.login.idriver.entity.Role;
import pe.edu.login.idriver.repository.RoleRepository;
import pe.edu.login.idriver.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role create(Role entity) throws Exception {
		return roleRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> readAll() throws Exception {
		return roleRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Role> findById(Long id) throws Exception {
		return roleRepository.findById(id);
	}


	@Override
	public Role update(Role entity) throws Exception {
		return roleRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		roleRepository.deleteById(id);
	}

	@Override
	public void deleteByAll() throws Exception {
		roleRepository.deleteAll();
		
	}

	@Override
	public List<Role> findByNameRole(String nameRole) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
   
	
}
