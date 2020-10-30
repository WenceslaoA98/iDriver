package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Polarizado;

public interface IPolarizadoService {
	
	public boolean insertar(Polarizado polarizado);
	public boolean modificar(Polarizado polarizado);
	public void eliminar(int idPolarizado);
	public Optional<Polarizado> listarId(int idPolarizado);
	List<Polarizado> listar();
	List<Polarizado> buscarNombre(int idPolarizado);
	List<Polarizado> buscarVehiculo(String placaVehiculo);
}
