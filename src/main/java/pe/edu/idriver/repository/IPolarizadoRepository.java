package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.Polarizado;

@Repository
public interface IPolarizadoRepository extends JpaRepository<Polarizado, Integer>{

	@Query("from Polarizado po where po.idPolarizado like %:idPolarizado%")
	List<Polarizado> buscarNombre(@Param("idPolarizado") int idPolarizado);
	
	@Query("from Polarizado po where po.vehiculo.placaVehiculo like %:placaVehiculo%")
	List<Polarizado> buscarVehiculo(@Param("placaVehiculo") String placaVehiculo);
	
}
