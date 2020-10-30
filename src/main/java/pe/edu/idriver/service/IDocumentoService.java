package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Documento;

public interface IDocumentoService {
	
	public boolean insertar(Documento documento);
	public boolean modificar(Documento documento);
	public void eliminar(int idDocumento);
	public Optional<Documento> listarId(int idDocumento);
	List<Documento> listar();
	List<Documento> buscarNombre(int idDocumento);
	List<Documento> buscarUsuario(String nameUsuario);
	List<Documento> buscarVehiculo(String placaVehiculo);
	List<Documento> buscarLicencia(int idLicencia);
	List<Documento> buscarSeguro(int idSeguro);
	List<Documento> buscarSoat(int idSoat);
	List<Documento> buscarTarjeta(int idTarjeta);
	List<Documento> buscarPolarizado(int idPolarizado);
}
