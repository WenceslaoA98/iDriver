package pe.edu.login.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.login.idriver.entity.Soat;
import pe.edu.login.idriver.repository.ISoatRepository;
import pe.edu.login.idriver.service.ISoatService;

@Service
public class SoatServiceImpl implements ISoatService {

	@Autowired
	private ISoatRepository dSoat;

	@Override
	@Transactional
	public boolean insertar(Soat soat) {
		
		Soat objSoat = dSoat.save(soat);
		if (objSoat == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Soat soat) {
		
		boolean flag = false;
		try {
			dSoat.save(soat);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(String idSoat) {
		
		dSoat.deleteById(idSoat);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Soat> listarId(String idSoat) {
		
		return dSoat.findById(idSoat);
	}

	@Override
	@Transactional
	public List<Soat> listar() {
		
		return dSoat.findAll();
	}

	@Override
	@Transactional
	public List<Soat> findById(String idSoat) {
		
		return dSoat.buscarId(idSoat);
	}
	
	@Override
	@Transactional
	public List<Soat> findByNombreUsuario(String lastnameUsuario) {

		return dSoat.buscarNombreUsuario(lastnameUsuario);
	}
	
	@Override
	@Transactional
	public List<Soat> findByPlacaVehiculo(String placaVehiculo) {

		return dSoat.buscarPlacaVehiculo(placaVehiculo);
	}
}
