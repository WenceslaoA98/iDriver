package pe.edu.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idriver.entity.Soat;
import pe.edu.idriver.repository.ISoatRepository;
import pe.edu.idriver.service.ISoatService;

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
	public void eliminar(int idSoat) {
		
		dSoat.deleteById(idSoat);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Soat> listarId(int idSoat) {
		
		return dSoat.findById(idSoat);
	}

	@Override
	@Transactional
	public List<Soat> listar() {
		
		return dSoat.findAll();
	}

	@Override
	@Transactional
	public List<Soat> buscarNombre(int idSoat) {
		
		return dSoat.buscarNombre(idSoat);
	}
	
	@Override
	@Transactional
	public List<Soat> buscarUsuario(String nameUsuario) {

		return dSoat.buscarUsuario(nameUsuario);
	}
	
	@Override
	@Transactional
	public List<Soat> buscarVehiculo(String placaVehiculo) {

		return dSoat.buscarVehiculo(placaVehiculo);
	}
}
