package pe.edu.login.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.login.idriver.entity.Seguro;


@Repository
public interface ISeguroRepository extends JpaRepository<Seguro, String>{

	@Query("from Seguro se where se.idSeguro like %:idSeguro%")
	List<Seguro> buscarId(@Param("idSeguro") String idSeguro);
	
	@Query("from Seguro se where se.usuario.nameUsuario like %:nameUsuario%")
	List<Seguro> buscarNombreUsuario(@Param("nameUsuario") String nameUsuario);
	
	@Query("from Seguro se where se.vehiculo.placaVehiculo like %:placaVehiculo%")
	List<Seguro> buscarPlacaVehiculo(@Param("placaVehiculo") String placaVehiculo);
	
}
