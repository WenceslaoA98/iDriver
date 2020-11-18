package pe.edu.login.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.login.idriver.entity.Brand;


@Repository
public interface IBrandRepository extends JpaRepository<Brand, Integer>{

	@Query("from Brand b where b.nameBrand like %:nameBrand%")
	List<Brand> buscarNombre(@Param("nameBrand") String nameBrand);
	
}
