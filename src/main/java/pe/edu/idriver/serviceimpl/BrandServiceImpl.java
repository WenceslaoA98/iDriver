package pe.edu.login.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.login.idriver.entity.Brand;
import pe.edu.login.idriver.repository.IBrandRepository;
import pe.edu.login.idriver.service.IBrandService;

@Service
public class BrandServiceImpl implements IBrandService {

	@Autowired
	private IBrandRepository dBrand;

	@Override
	@Transactional
	public boolean insertar(Brand brand) {
		
		Brand objBrand = dBrand.save(brand);
		if (objBrand == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Brand brand) {
		
		boolean flag = false;
		try {
			dBrand.save(brand);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idBrand) {
		
		dBrand.deleteById(idBrand);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Brand> listarId(int idBrand) {
		
		return dBrand.findById(idBrand);
	}

	@Override
	@Transactional
	public List<Brand> listar() {
		
		return dBrand.findAll();
	}

	@Override
	@Transactional
	public List<Brand> findByName(String nameBrand) {
		
		return dBrand.buscarNombre(nameBrand);
	}
}
