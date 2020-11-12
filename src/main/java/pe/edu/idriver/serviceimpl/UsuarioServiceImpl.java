package pe.edu.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idriver.entity.Usuario;
import pe.edu.idriver.repository.IUsuarioRepository;
import pe.edu.idriver.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository dUsuario;

	@Override
	@Transactional
	public boolean insertar(Usuario usuario) {
		
		Usuario objUsuario = dUsuario.save(usuario);
		if (objUsuario == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Usuario usuario) {
		
		boolean flag = false;
		try {
			dUsuario.save(usuario);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(String idUsuario) {
		
		dUsuario.deleteById(idUsuario);
	}

	@Transactional(readOnly = true)
	public List<Usuario> readAll() throws Exception {
		// TODO Auto-generated method stub
		return dUsuario.findAll();
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> listarId(String idUsuario) {
		
		return dUsuario.findById(idUsuario);
	}
	
	@Override
	@Transactional
	public List<Usuario> listar() {
		
		return dUsuario.findAll();
	}

	@Override
	@Transactional
	public List<Usuario> findById(String idUsuario) {
		
		return dUsuario.buscarId(idUsuario);
	}
	
}
