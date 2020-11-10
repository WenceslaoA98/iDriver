package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.Usuario;


@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, String>{

	@Query("from Usuario u where u.idUsuario like %:idUsuario%")
	List<Usuario> buscarId(@Param("idUsuario") String idUsuario);
	
}
