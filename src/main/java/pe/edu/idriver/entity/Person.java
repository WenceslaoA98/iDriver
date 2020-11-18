package pe.edu.login.idriver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "people")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el nombre")
	@Column(name="name", length=20, nullable=false)
	private String name;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el apellido")
	@Column(name="lastName", length=30, nullable=false)
	private String lastName;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el dni")
	@Size(min=8,max=8,message = "El dni no debe tener más de 8 digitos")
	@Column(name = "dni", length = 8, nullable = false)
	private String dni;
	
	@Column(name = "numberPhone", nullable = false)
	private Long numberPhone;
	
	@OneToOne(mappedBy = "person")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Long getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(Long numberPhone) {
		this.numberPhone = numberPhone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
