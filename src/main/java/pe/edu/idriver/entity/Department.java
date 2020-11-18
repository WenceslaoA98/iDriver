package pe.edu.login.idriver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="Departamento")
public class Department {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idDepartment;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el nombre")
	@Size(min=3,message = "El departamento debe tener mas de 3 caracteres")
	@Column(name="nombreDepartamento", length=20, nullable=false)
	private String nameDepartment;

	public int getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(int idDepartment) {
		this.idDepartment = idDepartment;
	}

	public String getNameDepartment() {
		return nameDepartment;
	}

	public void setNameDepartment(String nameDepartment) {
		this.nameDepartment = nameDepartment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
