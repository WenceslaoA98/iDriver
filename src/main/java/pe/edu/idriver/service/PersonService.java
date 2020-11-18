package pe.edu.login.idriver.service;

import java.util.List;

import pe.edu.login.idriver.entity.Person;

public interface PersonService extends CrudService<Person, Integer>{

	List<Person>findByLastName(String lastName) throws Exception;
	List<Person>findByDni(String dni) throws Exception;
}
