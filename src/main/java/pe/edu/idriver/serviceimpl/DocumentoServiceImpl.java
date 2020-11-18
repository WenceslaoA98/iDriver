package pe.edu.login.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.login.idriver.entity.Documento;
import pe.edu.login.idriver.repository.IDocumentoRepository;
import pe.edu.login.idriver.service.IDocumentoService;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

	@Autowired
	private IDocumentoRepository dDocumento;

	@Override
	@Transactional
	public boolean insertar(Documento documento) {
		
		Documento objDocumento = dDocumento.save(documento);
		if (objDocumento == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Documento documento) {
		
		boolean flag = false;
		try {
			dDocumento.save(documento);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idDocumento) {
		
		dDocumento.deleteById(idDocumento);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Documento> listarId(int idDocumento) {
		
		return dDocumento.findById(idDocumento);
	}

	@Override
	@Transactional
	public List<Documento> listar() {
		
		return dDocumento.findAll();
	}

	@Override
	@Transactional
	public List<Documento> findById(int idDocumento) {
		
		return dDocumento.buscarId(idDocumento);
	}
	
	@Override
	@Transactional
	public List<Documento> findByNameUsuario(String nameUsuario) {

		return dDocumento.buscarNombreUsuario(nameUsuario);
	}
	
	@Override
	@Transactional
	public List<Documento> findByPlacaVehiculo(String placaVehiculo) {

		return dDocumento.buscarPlacaVehiculo(placaVehiculo);
	}
	
	@Override
	@Transactional
	public List<Documento> findByIdLicencia(String idLicencia) {

		return dDocumento.buscarIdLicencia(idLicencia);
	}
	
	@Override
	@Transactional
	public List<Documento> findByIdSeguro(String idSeguro) {

		return dDocumento.buscarIdSeguro(idSeguro);
	}
	
	@Override
	@Transactional
	public List<Documento> findByIdSoat(String idSoat) {

		return dDocumento.buscarIdSoat(idSoat);
	}
	
	@Override
	@Transactional
	public List<Documento> findByIdTarjeta(String idTarjeta) {

		return dDocumento.buscarIdTarjeta(idTarjeta);
	}
	
	@Override
	@Transactional
	public List<Documento> findByIdPolarizado(String idPolarizado) {

		return dDocumento.buscarIdPolarizado(idPolarizado);
	}
	
	
}
