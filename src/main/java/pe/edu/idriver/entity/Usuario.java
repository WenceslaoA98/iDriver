package pe.edu.idriver.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Usuario")
public class Usuario {

	@Id
	@Column(name = "idUsuario", nullable = false, length = 9)
	private int idUsuario;
	
	@Column(name="nombreUsuario", length=60, nullable=false)
	private String nameUsuario;
	
	@Column(name="apellidoUsuario", length=60, nullable=false)
	private String lastnameUsuario;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaNacMascota")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthDateUsuario;
	
	@Column(name="correoUsuario", length=60, nullable=false)
	private String emailUsuario;
	
	@Column(name="direccionUsuario", length=60, nullable=false)
	private String addressUsuario;
	
	@ManyToOne
	@JoinColumn(name="idDistrict", nullable = false)
	private District district;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNameUsuario() {
		return nameUsuario;
	}

	public void setNameUsuario(String nameUsuario) {
		this.nameUsuario = nameUsuario;
	}

	public String getLastnameUsuario() {
		return lastnameUsuario;
	}

	public void setLastnameUsuario(String lastnameUsuario) {
		this.lastnameUsuario = lastnameUsuario;
	}

	public Date getBirthDateUsuario() {
		return birthDateUsuario;
	}

	public void setBirthDateUsuario(Date birthDateUsuario) {
		this.birthDateUsuario = birthDateUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getAddressUsuario() {
		return addressUsuario;
	}

	public void setAddressUsuario(String addressUsuario) {
		this.addressUsuario = addressUsuario;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	
	
}


