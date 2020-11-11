package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Province;

public interface IProvinceService {
	
	public boolean insertar(Province province);
	public boolean modificar(Province province);
	public void eliminar(int idProvince);
	public Optional<Province> listarId(int idProvince);
	List<Province> listar();
	List<Province> findByName(String nameProvince);
}
