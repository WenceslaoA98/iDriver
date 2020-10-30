package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Licencia;

public interface ILicenciaService {
	
	public boolean insertar(Licencia licencia);
	public boolean modificar(Licencia licencia);
	public void eliminar(int idLicencia);
	public Optional<Licencia> listarId(int idLicencia);
	List<Licencia> listar();
	List<Licencia> buscarNombre(String claseLicencia);
	List<Licencia> buscarUsuario(String nameUsuario);
}
