package pe.edu.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idriver.entity.Documento;
import pe.edu.idriver.repository.IDocumentoRepository;
import pe.edu.idriver.service.IDocumentoService;

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
	public List<Documento> buscarNombre(int idDocumento) {
		
		return dDocumento.buscarNombre(idDocumento);
	}
	
	@Override
	@Transactional
	public List<Documento> buscarUsuario(String nameUsuario) {

		return dDocumento.buscarUsuario(nameUsuario);
	}
	
	@Override
	@Transactional
	public List<Documento> buscarVehiculo(String placaVehiculo) {

		return dDocumento.buscarVehiculo(placaVehiculo);
	}
	
	@Override
	@Transactional
	public List<Documento> buscarLicencia(int idLicencia) {

		return dDocumento.buscarLicencia(idLicencia);
	}
	
	@Override
	@Transactional
	public List<Documento> buscarSeguro(int idSeguro) {

		return dDocumento.buscarSeguro(idSeguro);
	}
	
	@Override
	@Transactional
	public List<Documento> buscarSoat(int idSoat) {

		return dDocumento.buscarSoat(idSoat);
	}
	
	@Override
	@Transactional
	public List<Documento> buscarTarjeta(int idTarjeta) {

		return dDocumento.buscarTarjeta(idTarjeta);
	}
	
	@Override
	@Transactional
	public List<Documento> buscarPolarizado(int idPolarizado) {

		return dDocumento.buscarPolarizado(idPolarizado);
	}
	
	
}
