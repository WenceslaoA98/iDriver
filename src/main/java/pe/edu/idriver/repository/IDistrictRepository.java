package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.District;


@Repository
public interface IDistrictRepository extends JpaRepository<District, Integer>{

	@Query("from District d where d.nameDistrict like %:nameDistrict%")
	List<District> buscarNombre(@Param("nameDistrict") String nameDistrict);
	
	@Query("from District d where d.province.nameProvince like %:nameProvince%")
	List<District> buscarProvince(@Param("nameProvince") String nameProvince);
	
}
