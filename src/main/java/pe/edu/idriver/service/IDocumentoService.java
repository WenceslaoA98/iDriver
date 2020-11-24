package pe.edu.login.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.login.idriver.entity.Documento;

public interface IDocumentoService {
	
	public boolean insertar(Documento documento);
	public boolean modificar(Documento documento);
	public void eliminar(int idDocumento);
	public Optional<Documento> listarId(int idDocumento);
	List<Documento> listar();
	List<Documento> findById(int idDocumento);
	List<Documento> findByNameUsuario(String lastnameUsuario);
	List<Documento> findByPlacaVehiculo(String placaVehiculo);
	List<Documento> findByIdLicencia(String idLicencia);
	List<Documento> findByIdSeguro(String idSeguro);
	List<Documento> findByIdSoat(String idSoat);
	List<Documento> findByIdTarjeta(String idTarjeta);
	List<Documento> findByIdPolarizado(String idPolarizado);
}
