package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.Documento;

@Repository
public interface IDocumentoRepository extends JpaRepository<Documento, Integer>{

	@Query("from Documento do where do.idDocumento like %:idDocumento%")
	List<Documento> buscarId(@Param("idDocumento") int idDocumento);
	
	@Query("from Documento do where do.usuario.nameUsuario like %:nameUsuario%")
	List<Documento> buscarNombreUsuario(@Param("nameUsuario") String nameUsuario);
	
	@Query("from Documento do where do.vehiculo.placaVehiculo like %:placaVehiculo%")
	List<Documento> buscarPlacaVehiculo(@Param("placaVehiculo") String placaVehiculo);
	
	@Query("from Documento do where do.licencia.idLicencia like %:idLicencia%")
	List<Documento> buscarIdLicencia(@Param("idLicencia") String idLicencia);
	
	@Query("from Documento do where do.seguro.idSeguro like %:idSeguro%")
	List<Documento> buscarIdSeguro(@Param("idSeguro") String idSeguro);
	
	@Query("from Documento do where do.soat.idSoat like %:idSoat%")
	List<Documento> buscarIdSoat(@Param("idSoat") String idSoat);
	
	@Query("from Documento do where do.tarjeta.idTarjeta like %:idTarjeta%")
	List<Documento> buscarIdTarjeta(@Param("idTarjeta") String idTarjeta);
	
	@Query("from Documento do where do.polarizado.idPolarizado like %:idPolarizado%")
	List<Documento> buscarIdPolarizado(@Param("idPolarizado") String idPolarizado);
	
	
}
