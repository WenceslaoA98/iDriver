package pe.edu.login.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.login.idriver.entity.Polarizado;

@Repository
public interface IPolarizadoRepository extends JpaRepository<Polarizado, String>{

	@Query("from Polarizado po where po.idPolarizado like %:idPolarizado%")
	List<Polarizado> buscarId(@Param("idPolarizado") String idPolarizado);
	
	@Query("from Polarizado po where po.vehiculo.placaVehiculo like %:placaVehiculo%")
	List<Polarizado> buscarPlacaVehiculo(@Param("placaVehiculo") String placaVehiculo);
	
}
