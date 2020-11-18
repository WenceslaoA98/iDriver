package pe.edu.login.idriver.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Licencia")
public class Licencia {

	@Id
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese la licencia")
	@Size(min=11,max=11,message = "La licencia debe tener 10 digitos")
	@Column(name = "idLicencia", nullable = false, length = 11)
	private String idLicencia;
	
	@ManyToOne
	@JoinColumn(name="idUsuario", nullable = false)
	private Usuario usuario;
	
	@NotBlank(message = "Ingrese clase de licencia")
	@Column(name = "clase_licencia", length = 10, nullable = false)
	private String claseLicencia;
	
	@PastOrPresent(message = "La fecha debe ser pasada")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpedicion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expedicionLicencia;
	
	@Future(message = "La fecha debe ser futura")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpiracion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expiracionLicencia;
	
	@NotBlank(message = "Ingrese restricción de licencia")
	@Column(name = "restriccion_licencia", length = 10, nullable = false)
	private String restriccionLicencia;
	

	public String getIdLicencia() {
		return idLicencia;
	}

	public void setIdLicencia(String idLicencia) {
		this.idLicencia = idLicencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getExpedicionLicencia() {
		return expedicionLicencia;
	}

	public void setExpedicionLicencia(Date expedicionLicencia) {
		this.expedicionLicencia = expedicionLicencia;
	}

	public Date getExpiracionLicencia() {
		return expiracionLicencia;
	}

	public void setExpiracionLicencia(Date expiracionLicencia) {
		this.expiracionLicencia = expiracionLicencia;
	}

	public String getClaseLicencia() {
		return claseLicencia;
	}

	public void setClaseLicencia(String claseLicencia) {
		this.claseLicencia = claseLicencia;
	}

	public String getRestriccionLicencia() {
		return restriccionLicencia;
	}

	public void setRestriccionLicencia(String restriccionLicencia) {
		this.restriccionLicencia = restriccionLicencia;
	}

	
}


