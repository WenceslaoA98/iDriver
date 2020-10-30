package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.Licencia;

@Repository
public interface ILicenciaRepository extends JpaRepository<Licencia, Integer>{

	@Query("from Licencia l where l.claseLicencia like %:claseLicencia%")
	List<Licencia> buscarNombre(@Param("claseLicencia") String claseLicencia);
	
	@Query("from Licencia l where l.usuario.nameUsuario like %:nameUsuario%")
	List<Licencia> buscarUsuario(@Param("nameUsuario") String nameUsuario);
	
}
