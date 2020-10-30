package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Tarjeta;

public interface ITarjetaService {
	
	public boolean insertar(Tarjeta tarjeta);
	public boolean modificar(Tarjeta tarjeta);
	public void eliminar(int idTarjeta);
	public Optional<Tarjeta> listarId(int idTarjeta);
	List<Tarjeta> listar();
	List<Tarjeta> buscarNombre(int idTarjeta);
	List<Tarjeta> buscarVehiculo(String placaVehiculo);
}
