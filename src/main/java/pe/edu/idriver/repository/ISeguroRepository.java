package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.Seguro;

@Repository
public interface ISeguroRepository extends JpaRepository<Seguro, Integer>{

	@Query("from Seguro se where se.idSeguro like %:idSeguro%")
	List<Seguro> buscarNombre(@Param("idSeguro") int idSeguro);
	
	@Query("from Seguro se where se.usuario.nameUsuario like %:nameUsuario%")
	List<Seguro> buscarUsuario(@Param("nameUsuario") String nameUsuario);
	
	@Query("from Seguro se where se.vehiculo.placaVehiculo like %:placaVehiculo%")
	List<Seguro> buscarVehiculo(@Param("placaVehiculo") String placaVehiculo);
	
}
