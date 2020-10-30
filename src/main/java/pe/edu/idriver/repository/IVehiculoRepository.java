package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.Vehiculo;


@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer>{

	@Query("from Vehiculo v where v.placaVehiculo like %:placaVehiculo%")
	List<Vehiculo> buscarNombre(@Param("placaVehiculo") String placaVehiculo);
	
	@Query("from Vehiculo v where v.carmodel.nameCarModel like %:nameCarModel%")
	List<Vehiculo> buscarCarModel(@Param("nameCarModel") String nameCarModel);
	
	@Query("from Vehiculo v where v.color.nameColor like %:nameColor%")
	List<Vehiculo> buscarColor(@Param("nameColor") String nameColor);
	
}
