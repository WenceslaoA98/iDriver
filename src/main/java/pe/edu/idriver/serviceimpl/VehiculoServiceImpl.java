package pe.edu.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idriver.entity.Vehiculo;
import pe.edu.idriver.repository.IVehiculoRepository;
import pe.edu.idriver.service.IVehiculoService;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository dVehiculo;

	@Override
	@Transactional
	public boolean insertar(Vehiculo vehiculo) {
		
		Vehiculo objVehiculo = dVehiculo.save(vehiculo);
		if (objVehiculo == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Vehiculo vehiculo) {
		
		boolean flag = false;
		try {
			dVehiculo.save(vehiculo);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idVehiculo) {
		
		dVehiculo.deleteById(idVehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Vehiculo> listarId(int idVehiculo) {
		
		return dVehiculo.findById(idVehiculo);
	}

	@Override
	@Transactional
	public List<Vehiculo> listar() {
		
		return dVehiculo.findAll();
	}

	@Override
	@Transactional
	public List<Vehiculo> findByName(String placaVehiculo) {
		
		return dVehiculo.buscarPlaca(placaVehiculo);
	}
	
}
