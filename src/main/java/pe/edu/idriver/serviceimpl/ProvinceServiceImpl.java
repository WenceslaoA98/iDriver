package pe.edu.login.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.login.idriver.entity.Province;
import pe.edu.login.idriver.repository.IProvinceRepository;
import pe.edu.login.idriver.service.IProvinceService;

@Service
public class ProvinceServiceImpl implements IProvinceService {

	@Autowired
	private IProvinceRepository dProvince;

	@Override
	@Transactional
	public boolean insertar(Province province) {
		
		Province objProvince = dProvince.save(province);
		if (objProvince == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Province province) {
		
		boolean flag = false;
		try {
			dProvince.save(province);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idProvince) {
		
		dProvince.deleteById(idProvince);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Province> listarId(int idProvince) {
		
		return dProvince.findById(idProvince);
	}

	@Override
	@Transactional
	public List<Province> listar() {
		
		return dProvince.findAll();
	}

	@Override
	@Transactional
	public List<Province> findByName(String nameProvince) {
		
		return dProvince.buscarNombre(nameProvince);
	}
}
