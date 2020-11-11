package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.Tarjeta;

@Repository
public interface ITarjetaRepository extends JpaRepository<Tarjeta, String>{

	@Query("from Tarjeta t where t.idTarjeta like %:idTarjeta%")
	List<Tarjeta> buscarId(@Param("idTarjeta") String idTarjeta);
	
	@Query("from Tarjeta t where t.vehiculo.placaVehiculo like %:placaVehiculo%")
	List<Tarjeta> buscarPlacaVehiculo(@Param("placaVehiculo") String placaVehiculo);
	
}
