package pe.edu.login.idriver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.login.idriver.entity.Color;


@Repository
public interface IColorRepository extends JpaRepository<Color, Integer>{

	@Query("from Color co where co.nameColor like %:nameColor%")
	List<Color> buscarNombre(@Param("nameColor") String nameColor);
	
}
