package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.Soat;

@Repository
public interface ISoatRepository extends JpaRepository<Soat, Integer>{

	@Query("from Soat s where s.idSoat like %:idSoat%")
	List<Soat> buscarNombre(@Param("idSoat") int idSoat);
	
	@Query("from Soat s where s.usuario.nameUsuario like %:nameUsuario%")
	List<Soat> buscarUsuario(@Param("nameUsuario") String nameUsuario);
	
	@Query("from Soat s where s.vehiculo.placaVehiculo like %:placaVehiculo%")
	List<Soat> buscarVehiculo(@Param("placaVehiculo") String placaVehiculo);
	
}
