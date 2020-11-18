package pe.edu.login.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.login.idriver.entity.Usuario;

public interface IUsuarioService {
	
	public boolean insertar(Usuario usuario);
	public boolean modificar(Usuario usuario);
	public void eliminar(String idUsuario);
	public Optional<Usuario> listarId(String idUsuario);
	List<Usuario> listar();
	List<Usuario> findById(String idUsuario);
}
