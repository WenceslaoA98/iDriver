package pe.edu.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idriver.entity.Polarizado;
import pe.edu.idriver.repository.IPolarizadoRepository;
import pe.edu.idriver.service.IPolarizadoService;

@Service
public class PolarizadoServiceImpl implements IPolarizadoService {

	@Autowired
	private IPolarizadoRepository dPolarizado;

	@Override
	@Transactional
	public boolean insertar(Polarizado polarizado) {
		
		Polarizado objPolarizado = dPolarizado.save(polarizado);
		if (objPolarizado == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Polarizado polarizado) {
		
		boolean flag = false;
		try {
			dPolarizado.save(polarizado);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(String idPolarizado) {
		
		dPolarizado.deleteById(idPolarizado);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Polarizado> listarId(String idPolarizado) {
		
		return dPolarizado.findById(idPolarizado);
	}

	@Override
	@Transactional
	public List<Polarizado> listar() {
		
		return dPolarizado.findAll();
	}

	@Override
	@Transactional
	public List<Polarizado> findById(String idPolarizado) {
		
		return dPolarizado.buscarId(idPolarizado);
	}
	
	@Override
	@Transactional
	public List<Polarizado> findByPlacaVehiculo(String placaVehiculo) {

		return dPolarizado.buscarPlacaVehiculo(placaVehiculo);
	}
}
