package pe.edu.login.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.login.idriver.entity.Polarizado;

public interface IPolarizadoService {
	
	public boolean insertar(Polarizado polarizado);
	public boolean modificar(Polarizado polarizado);
	public void eliminar(String idPolarizado);
	public Optional<Polarizado> listarId(String idPolarizado);
	List<Polarizado> listar();
	List<Polarizado> findById(String idPolarizado);
	List<Polarizado> findByPlacaVehiculo(String placaVehiculo);
}
