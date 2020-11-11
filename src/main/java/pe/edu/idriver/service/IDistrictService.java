package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.District;

public interface IDistrictService {
	
	public boolean insertar(District district);
	public boolean modificar(District district);
	public void eliminar(int idDistrict);
	public Optional<District> listarId(int idDistrict);
	List<District> listar();
	List<District> findByName(String nameDistrict);
	List<District> findByNameDepartment(String nameDepartment);
}
