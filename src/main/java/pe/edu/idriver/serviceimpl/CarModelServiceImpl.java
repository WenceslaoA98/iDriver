package pe.edu.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idriver.entity.CarModel;
import pe.edu.idriver.repository.ICarModelRepository;
import pe.edu.idriver.service.ICarModelService;


@Service
public class CarModelServiceImpl implements ICarModelService {

	@Autowired
	private ICarModelRepository dCarModel;

	@Override
	@Transactional
	public boolean insertar(CarModel carmodel) {
		
		CarModel objCarModel = dCarModel.save(carmodel);
		if (objCarModel == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(CarModel carmodel) {
		
		boolean flag = false;
		try {
			dCarModel.save(carmodel);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idCarModel) {
		
		dCarModel.deleteById(idCarModel);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CarModel> listarId(int idCarModel) {
		
		return dCarModel.findById(idCarModel);
	}

	@Override
	@Transactional
	public List<CarModel> listar() {
		
		return dCarModel.findAll();
	}

	@Override
	@Transactional
	public List<CarModel> findByName(String nameCarModel) {
		
		return dCarModel.buscarNombre(nameCarModel);
	}
	
	@Override
	@Transactional
	public List<CarModel> findByNameBrand(String nameBrand) {

		return dCarModel.buscarNombreBrand(nameBrand);
	}
}
