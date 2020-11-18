package pe.edu.login.idriver.service;

import java.util.List;

import pe.edu.login.idriver.entity.Role;


public interface RoleService extends CrudService<Role, Long>{

	List<Role> findByNameRole( String nameRole ) throws Exception;
	
}
