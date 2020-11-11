package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Licencia;

public interface ILicenciaService {
	
	public boolean insertar(Licencia licencia);
	public boolean modificar(Licencia licencia);
	public void eliminar(String idLicencia);
	public Optional<Licencia> listarId(String idLicencia);
	List<Licencia> listar();
	List<Licencia> findById(String idLicencia);
	List<Licencia> findByNameUsuario(String nameUsuario);
}
