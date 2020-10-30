package pe.edu.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idriver.entity.Color;
import pe.edu.idriver.repository.IColorRepository;
import pe.edu.idriver.service.IColorService;

@Service
public class ColorServiceImpl implements IColorService {

	@Autowired
	private IColorRepository dColor;

	@Override
	@Transactional
	public boolean insertar(Color color) {
		
		Color objColor = dColor.save(color);
		if (objColor == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Color color) {
		
		boolean flag = false;
		try {
			dColor.save(color);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idColor) {
		
		dColor.deleteById(idColor);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Color> listarId(int idColor) {
		
		return dColor.findById(idColor);
	}

	@Override
	@Transactional
	public List<Color> listar() {
		
		return dColor.findAll();
	}

	@Override
	@Transactional
	public List<Color> buscarNombre(String nameColor) {
		
		return dColor.buscarNombre(nameColor);
	}
}
