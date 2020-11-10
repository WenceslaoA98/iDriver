package pe.edu.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idriver.entity.District;
import pe.edu.idriver.repository.IDistrictRepository;
import pe.edu.idriver.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService {

	@Autowired
	private IDistrictRepository dDistrict;

	@Override
	@Transactional
	public boolean insertar(District district) {
		
		District objDistrict = dDistrict.save(district);
		if (objDistrict == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(District district) {
		
		boolean flag = false;
		try {
			dDistrict.save(district);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idDistrict) {
		
		dDistrict.deleteById(idDistrict);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<District> listarId(int idDistrict) {
		
		return dDistrict.findById(idDistrict);
	}

	@Override
	@Transactional
	public List<District> listar() {
		
		return dDistrict.findAll();
	}

	@Override
	@Transactional
	public List<District> findByName(String nameDistrict) {
		
		return dDistrict.buscarNombre(nameDistrict);
	}

	@Override
	@Transactional
	public List<District> findByNameDepartment(String nameDepartment) {
		
		return dDistrict.buscarNombreDepartment(nameDepartment);
	}

}
