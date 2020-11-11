package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Tarjeta;

public interface ITarjetaService {
	
	public boolean insertar(Tarjeta tarjeta);
	public boolean modificar(Tarjeta tarjeta);
	public void eliminar(String idTarjeta);
	public Optional<Tarjeta> listarId(String idTarjeta);
	List<Tarjeta> listar();
	List<Tarjeta> findById(String idTarjeta);
	List<Tarjeta> findByPlacaVehiculo(String placaVehiculo);
}
