package pe.edu.login.idriver.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Usuario")
public class Usuario {

	@Id
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el DNI")
	@Size(min=8,max=8,message = "El DNI debe tener 8 digitos")
	@Column(name = "idUsuario", nullable = false, length = 8)
	private String idUsuario;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el Nombre")
	@Column(name="nombreUsuario", length=40, nullable=false)
	private String nameUsuario;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el Apellido")
	@Column(name="apellidoUsuario", length=60, nullable=false)
	private String lastnameUsuario;
	
	@Email
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el Nombre")
	@Column(name="correoUsuario", length=60, nullable=false)
	private String emailUsuario;
	
	@Past(message = "La fecha debe ser pasada")
	@Column(name="fechaNacUsuario", length=30, nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthDateUsuario;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese la Direccion")
	@Column(name="direccionUsuario", length=60, nullable=false)
	private String addressUsuario;
	
	@ManyToOne
	@JoinColumn(name="idDistrict", nullable = false)
	private District district;

	@OneToOne(mappedBy="usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Licencia licencia;
	
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
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

	public Licencia getLicencia() {
		return licencia;
	}

	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}

	
	
}


