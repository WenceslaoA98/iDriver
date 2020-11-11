package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.Department;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Integer>{

	@Query("from Department d where d.nameDepartment like %:nameDepartment%")
	List<Department> buscarNombre(@Param("nameDepartment") String nameDepartment);
}
