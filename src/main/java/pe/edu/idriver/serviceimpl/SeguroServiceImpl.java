package pe.edu.login.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.login.idriver.entity.Seguro;
import pe.edu.login.idriver.repository.ISeguroRepository;
import pe.edu.login.idriver.service.ISeguroService;

@Service
public class SeguroServiceImpl implements ISeguroService {

	@Autowired
	private ISeguroRepository dSeguro;

	@Override
	@Transactional
	public boolean insertar(Seguro seguro) {
		
		Seguro objSeguro = dSeguro.save(seguro);
		if (objSeguro == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Seguro seguro) {
		
		boolean flag = false;
		try {
			dSeguro.save(seguro);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(String idSeguro) {
		
		dSeguro.deleteById(idSeguro);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Seguro> listarId(String idSeguro) {
		
		return dSeguro.findById(idSeguro);
	}

	@Override
	@Transactional
	public List<Seguro> listar() {
		
		return dSeguro.findAll();
	}

	@Override
	@Transactional
	public List<Seguro> findById(String idSeguro) {
		
		return dSeguro.buscarId(idSeguro);
	}
	
	@Override
	@Transactional
	public List<Seguro> findByNombreUsuario(String lastnameUsuario) {

		return dSeguro.buscarNombreUsuario(lastnameUsuario);
	}
	
	@Override
	@Transactional
	public List<Seguro> findByPlacaVehiculo(String placaVehiculo) {

		return dSeguro.buscarPlacaVehiculo(placaVehiculo);
	}
}
