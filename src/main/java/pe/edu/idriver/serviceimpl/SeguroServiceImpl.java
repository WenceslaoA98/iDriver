package pe.edu.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idriver.entity.Seguro;
import pe.edu.idriver.repository.ISeguroRepository;
import pe.edu.idriver.service.ISeguroService;

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
	public void eliminar(int idSeguro) {
		
		dSeguro.deleteById(idSeguro);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Seguro> listarId(int idSeguro) {
		
		return dSeguro.findById(idSeguro);
	}

	@Override
	@Transactional
	public List<Seguro> listar() {
		
		return dSeguro.findAll();
	}

	@Override
	@Transactional
	public List<Seguro> buscarNombre(int idSeguro) {
		
		return dSeguro.buscarNombre(idSeguro);
	}
	
	@Override
	@Transactional
	public List<Seguro> buscarUsuario(String nameUsuario) {

		return dSeguro.buscarUsuario(nameUsuario);
	}
	
	@Override
	@Transactional
	public List<Seguro> buscarVehiculo(String placaVehiculo) {

		return dSeguro.buscarVehiculo(placaVehiculo);
	}
}
