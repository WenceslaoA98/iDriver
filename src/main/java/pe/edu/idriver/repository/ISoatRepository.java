package pe.edu.login.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.login.idriver.entity.Soat;


@Repository
public interface ISoatRepository extends JpaRepository<Soat, String>{

	@Query("from Soat s where s.idSoat like %:idSoat%")
	List<Soat> buscarId(@Param("idSoat") String idSoat);
	
	@Query("from Soat s where s.usuario.nameUsuario like %:nameUsuario%")
	List<Soat> buscarNombreUsuario(@Param("nameUsuario") String nameUsuario);
	
	@Query("from Soat s where s.vehiculo.placaVehiculo like %:placaVehiculo%")
	List<Soat> buscarPlacaVehiculo(@Param("placaVehiculo") String placaVehiculo);
	
}
