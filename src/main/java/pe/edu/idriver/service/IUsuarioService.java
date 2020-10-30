package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Usuario;

public interface IUsuarioService {
	
	public boolean insertar(Usuario usuario);
	public boolean modificar(Usuario usuario);
	public void eliminar(int idUsuario);
	public Optional<Usuario> listarId(int idUsuario);
	List<Usuario> listar();
	List<Usuario> buscarNombre(String nameUsuario);
	List<Usuario> buscarDistrict(String nameDistrict);
}
