package pe.edu.login.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.login.idriver.entity.Licencia;
import pe.edu.login.idriver.repository.ILicenciaRepository;
import pe.edu.login.idriver.service.ILicenciaService;

@Service
public class LicenciaServiceImpl implements ILicenciaService {

	@Autowired
	private ILicenciaRepository dLicencia;

	@Override
	@Transactional
	public boolean insertar(Licencia licencia) {
		
		Licencia objLicencia = dLicencia.save(licencia);
		if (objLicencia == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Licencia licencia) {
		
		boolean flag = false;
		try {
			dLicencia.save(licencia);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(String idLicencia) {
		
		dLicencia.deleteById(idLicencia);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Licencia> listarId(String idLicencia) {
		
		return dLicencia.findById(idLicencia);
	}

	@Override
	@Transactional
	public List<Licencia> listar() {
		
		return dLicencia.findAll();
	}

	@Override
	@Transactional
	public List<Licencia> findById(String idLicencia) {
		
		return dLicencia.buscarId(idLicencia);
	}
	
	@Override
	@Transactional
	public List<Licencia> findByNameUsuario(String lastnameUsuario) {
		
		return dLicencia.buscarNombreUsuario(lastnameUsuario);
	}
}
