package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Seguro;

public interface ISeguroService {
	
	public boolean insertar(Seguro soat);
	public boolean modificar(Seguro soat);
	public void eliminar(int idSeguro);
	public Optional<Seguro> listarId(int idSeguro);
	List<Seguro> listar();
	List<Seguro> buscarNombre(int idSeguro);
	List<Seguro> buscarUsuario(String nameUsuario);
	List<Seguro> buscarVehiculo(String placaVehiculo);
}
