package pe.edu.login.idriver.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.login.idriver.entity.Department;
import pe.edu.login.idriver.repository.IDepartmentRepository;
import pe.edu.login.idriver.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private IDepartmentRepository dDepartment;

	@Override
	@Transactional
	public boolean insertar(Department department) {
		
		Department objDepartment = dDepartment.save(department);
		if (objDepartment == null)
			return false;
		else return true;
	}

	@Override
	@Transactional
	public boolean modificar(Department department) {
		
		boolean flag = false;
		try {
			dDepartment.save(department);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idDepartment) {
		
		dDepartment.deleteById(idDepartment);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Department> listarId(int idDepartment) {
		
		return dDepartment.findById(idDepartment);
	}

	@Override
	@Transactional
	public List<Department> listar() {
		
		return dDepartment.findAll();
	}

	@Override
	@Transactional
	public List<Department> findByName(String nameDepartment) {
		
		return dDepartment.buscarNombre(nameDepartment);
	}
	
}
