package pe.edu.login.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.login.idriver.entity.Soat;

public interface ISoatService {
	
	public boolean insertar(Soat soat);
	public boolean modificar(Soat soat);
	public void eliminar(String idSoat);
	public Optional<Soat> listarId(String idSoat);
	List<Soat> listar();
	List<Soat> findById(String idSoat);
	List<Soat> findByNombreUsuario(String lastnameUsuario);
	List<Soat> findByPlacaVehiculo(String placaVehiculo);
}
