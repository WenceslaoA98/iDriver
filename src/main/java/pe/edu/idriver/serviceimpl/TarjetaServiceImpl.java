package pe.edu.login.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.login.idriver.entity.Tarjeta;
import pe.edu.login.idriver.repository.ITarjetaRepository;
import pe.edu.login.idriver.service.ITarjetaService;

@Service
public class TarjetaServiceImpl implements ITarjetaService {

	@Autowired
	private ITarjetaRepository dTarjeta;

	@Override
	@Transactional
	public boolean insertar(Tarjeta tarjeta) {
		
		Tarjeta objTarjeta = dTarjeta.save(tarjeta);
		if (objTarjeta == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Tarjeta tarjeta) {
		
		boolean flag = false;
		try {
			dTarjeta.save(tarjeta);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(String idTarjeta) {
		
		dTarjeta.deleteById(idTarjeta);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Tarjeta> listarId(String idTarjeta) {
		
		return dTarjeta.findById(idTarjeta);
	}

	@Override
	@Transactional
	public List<Tarjeta> listar() {
		
		return dTarjeta.findAll();
	}

	@Override
	@Transactional
	public List<Tarjeta> findById(String idTarjeta) {
		
		return dTarjeta.buscarId(idTarjeta);
	}
	
	@Override
	@Transactional
	public List<Tarjeta> findByPlacaVehiculo(String placaVehiculo) {

		return dTarjeta.buscarPlacaVehiculo(placaVehiculo);
	}
}
