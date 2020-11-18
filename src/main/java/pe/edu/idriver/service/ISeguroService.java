package pe.edu.login.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.login.idriver.entity.Seguro;

public interface ISeguroService {
	
	public boolean insertar(Seguro soat);
	public boolean modificar(Seguro soat);
	public void eliminar(String idSeguro);
	public Optional<Seguro> listarId(String idSeguro);
	List<Seguro> listar();
	List<Seguro> findById(String idSeguro);
	List<Seguro> findByNombreUsuario(String nameUsuario);
	List<Seguro> findByPlacaVehiculo(String placaVehiculo);
}
