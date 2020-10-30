package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Brand;

public interface IBrandService {
	
	public boolean insertar(Brand brand);
	public boolean modificar(Brand brand);
	public void eliminar(int idBrand);
	public Optional<Brand> listarId(int idBrand);
	List<Brand> listar();
	List<Brand> buscarNombre(String nameBrand);
}
