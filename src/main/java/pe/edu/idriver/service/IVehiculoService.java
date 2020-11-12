package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Vehiculo;


public interface IVehiculoService {
	
	public boolean insertar(Vehiculo vehiculo);
	public boolean modificar(Vehiculo vehiculo);
	public void eliminar(String idVehiculo);
	public Optional<Vehiculo> listarId(String idVehiculo);
	List<Vehiculo> listar();
	List<Vehiculo> findByName(String placaVehiculo);

}
