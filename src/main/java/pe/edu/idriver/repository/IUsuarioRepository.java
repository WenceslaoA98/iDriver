package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.Usuario;


@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query("from Usuario u where u.nameUsuario like %:nameUsuario%")
	List<Usuario> buscarNombre(@Param("nameUsuario") String nameUsuario);
	
	@Query("from Usuario u where u.district.nameDistrict like %:nameDistrict%")
	List<Usuario> buscarDistrict(@Param("nameDistrict") String nameDistrict);
	
}
