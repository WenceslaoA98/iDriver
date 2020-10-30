package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.Province;


@Repository
public interface IProvinceRepository extends JpaRepository<Province, Integer>{

	@Query("from Province p where p.nameProvince like %:nameProvince%")
	List<Province> buscarNombre(@Param("nameProvince") String nameProvince);
	
	@Query("from Province p where p.department.nameDepartment like %:nameDepartment%")
	List<Province> buscarDepartment(@Param("nameDepartment") String nameDepartment);
	
}
