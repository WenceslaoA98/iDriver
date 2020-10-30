package pe.edu.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.idriver.entity.CarModel;

@Repository
public interface ICarModelRepository extends JpaRepository<CarModel, Integer>{

	@Query("from CarModel cm where cm.nameCarModel like %:nameCarModel%")
	List<CarModel> buscarNombre(@Param("nameCarModel") String nameCarModel);
	
	@Query("from CarModel cm where cm.brand.nameBrand like %:nameBrand%")
	List<CarModel> buscarBrand(@Param("nameBrand") String nameBrand);
	
}
