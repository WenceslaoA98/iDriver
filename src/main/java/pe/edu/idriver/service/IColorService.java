package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Color;

public interface IColorService {
	
	public boolean insertar(Color color);
	public boolean modificar(Color color);
	public void eliminar(int idColor);
	public Optional<Color> listarId(int idColor);
	List<Color> listar();
	List<Color> buscarNombre(String nameColor);
}
