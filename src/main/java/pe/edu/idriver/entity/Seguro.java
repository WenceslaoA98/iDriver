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
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Seguro")
public class Seguro {

	@Id
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el Seguro")
	@Size(min=11,max=11,message = "El Seguro debe tener 11 digitos")
	@Column(name = "idSeguro", nullable = false, length = 11)
	private String idSeguro;
	
	@ManyToOne
	@JoinColumn(name="idUsuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idVehiculo", nullable = false)
	private Vehiculo vehiculo;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el monto")
	@Size(min=2,max=3,message = "Monto invalido")
	@Column(name="montoSeguro", length=5, nullable=false)
	private String montoSeguro;
	
	@Past(message="La fecha debe ser pasada")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpedicion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expedicionSeguro;
	
	@Future(message="La fecha debe sere futura")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpiracion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expiracionSeguro;

	public String getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(String idSeguro) {
		this.idSeguro = idSeguro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getMontoSeguro() {
		return montoSeguro;
	}

	public void setMontoSeguro(String montoSeguro) {
		this.montoSeguro = montoSeguro;
	}

	public Date getExpedicionSeguro() {
		return expedicionSeguro;
	}

	public void setExpedicionSeguro(Date expedicionSeguro) {
		this.expedicionSeguro = expedicionSeguro;
	}

	public Date getExpiracionSeguro() {
		return expiracionSeguro;
	}

	public void setExpiracionSeguro(Date expiracionSeguro) {
		this.expiracionSeguro = expiracionSeguro;
	}
}

