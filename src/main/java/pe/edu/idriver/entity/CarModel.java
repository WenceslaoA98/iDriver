package pe.edu.idriver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="Modelo")
public class CarModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idCarModel;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el nombre")
	@Size(min=3,message = "El modelo debe tener mas de 3 caracteres")
	@Column(name="nombreModelo", length=30, nullable=false)
	private String nameCarModel;

	@ManyToOne
	@JoinColumn(name="idBrand", nullable = false)
	private Brand brand;

	public int getIdCarModel() {
		return idCarModel;
	}

	public void setIdCarModel(int idCarModel) {
		this.idCarModel = idCarModel;
	}

	public String getNameCarModel() {
		return nameCarModel;
	}

	public void setNameCarModel(String nameCarModel) {
		this.nameCarModel = nameCarModel;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
