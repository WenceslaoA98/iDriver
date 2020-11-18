package pe.edu.login.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.login.idriver.entity.CarModel;


public interface ICarModelService {
	
	public boolean insertar(CarModel carmodel);
	public boolean modificar(CarModel carmodel);
	public void eliminar(int idCarModel);
	public Optional<CarModel> listarId(int idCarModel);
	List<CarModel> listar();
	List<CarModel> findByName(String nameCarModel);
	List<CarModel> findByNameBrand(String nameBrand);
}
