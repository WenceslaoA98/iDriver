package pe.edu.login.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.login.idriver.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByNameRole( String nameRole ) throws Exception;
}
