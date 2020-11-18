package pe.edu.login.idriver.entity;

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
@Table(name="Distrito")
public class District {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idDistrict;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el nombre del distrito")
	@Size(min=3,message = "El distrito debe tener mas de 3 caracteres")
	@Column(name="nombreDistrito", length=30, nullable=false)
	private String nameDistrict;
	
	@ManyToOne
	@JoinColumn(name="idProvince", nullable = false)
	private Province province;

	public int getIdDistrict() {
		return idDistrict;
	}

	public void setIdDistrict(int idDistrict) {
		this.idDistrict = idDistrict;
	}

	public String getNameDistrict() {
		return nameDistrict;
	}

	public void setNameDistrict(String nameDistrict) {
		this.nameDistrict = nameDistrict;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
