package pe.edu.idriver.service;

import java.util.List;
import java.util.Optional;

import pe.edu.idriver.entity.Department;

public interface IDepartmentService {
	
	public boolean insertar(Department department);
	public boolean modificar(Department department);
	public void eliminar(int idDepartment);
	public Optional<Department> listarId(int idDepartment);
	List<Department> listar();
	List<Department> buscarNombre(String nameDepartment);
}
