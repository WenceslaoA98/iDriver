package pe.edu.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idriver.entity.Licencia;
import pe.edu.idriver.repository.ILicenciaRepository;
import pe.edu.idriver.service.ILicenciaService;

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
	public void eliminar(int idLicencia) {
		
		dLicencia.deleteById(idLicencia);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Licencia> listarId(int idLicencia) {
		
		return dLicencia.findById(idLicencia);
	}

	@Override
	@Transactional
	public List<Licencia> listar() {
		
		return dLicencia.findAll();
	}

	@Override
	@Transactional
	public List<Licencia> buscarNombre(String claseLicencia) {
		
		return dLicencia.buscarNombre(claseLicencia);
	}
	
	@Override
	@Transactional
	public List<Licencia> buscarUsuario(String nameUsuario) {

		return dLicencia.buscarUsuario(nameUsuario);
	}
}
