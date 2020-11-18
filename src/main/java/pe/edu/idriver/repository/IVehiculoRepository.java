package pe.edu.login.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.login.idriver.entity.Vehiculo;


@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, String>{

	@Query("from Vehiculo v where v.placaVehiculo like %:placaVehiculo%")
	List<Vehiculo> buscarPlaca(@Param("placaVehiculo") String placaVehiculo);
	
}
