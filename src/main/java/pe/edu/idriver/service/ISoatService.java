package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Soat;

public interface ISoatService {
	
	public boolean insertar(Soat soat);
	public boolean modificar(Soat soat);
	public void eliminar(int idSoat);
	public Optional<Soat> listarId(int idSoat);
	List<Soat> listar();
	List<Soat> buscarNombre(int idSoat);
	List<Soat> buscarUsuario(String nameUsuario);
	List<Soat> buscarVehiculo(String placaVehiculo);
}
