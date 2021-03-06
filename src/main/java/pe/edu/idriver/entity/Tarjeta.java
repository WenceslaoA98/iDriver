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
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Tarjeta")
public class Tarjeta {

	@Id
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el numero de tarjeta")
	@Size(min=11,max=11,message = "La tarjeta debe tener 11 digitos")
	@Column(name = "idTarjeta", nullable = false, length = 11)
	private String idTarjeta;
	
	@ManyToOne
	@JoinColumn(name="idVehiculo", nullable = false)
	private Vehiculo vehiculo;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese la partida")
	@Size(min=11,max=11,message = "La partida debe tener 11 digitos")
	@Column(name="partidaTarjeta", length=11, nullable=false)
	private String partidaTarjeta;
	
	@Past(message = "La fecha debe ser pasada")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpedicion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expedicionTarjeta;
	
	@Future(message = "La fecha debe ser futura")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpiracion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expiracionTarjeta;

	public String getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(String idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getPartidaTarjeta() {
		return partidaTarjeta;
	}

	public void setPartidaTarjeta(String partidaTarjeta) {
		this.partidaTarjeta = partidaTarjeta;
	}

	public Date getExpedicionTarjeta() {
		return expedicionTarjeta;
	}

	public void setExpedicionTarjeta(Date expedicionTarjeta) {
		this.expedicionTarjeta = expedicionTarjeta;
	}

	public Date getExpiracionTarjeta() {
		return expiracionTarjeta;
	}

	public void setExpiracionTarjeta(Date expiracionTarjeta) {
		this.expiracionTarjeta = expiracionTarjeta;
	}

}

