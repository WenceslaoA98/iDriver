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
@Table(name="Polarizado")
public class Polarizado {

	@Id
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el Polarizado")
	@Size(min=11,max=11,message = "El Polarizado debe tener 11 digitos")
	@Column(name = "idPolarizado", length = 11)
	private String idPolarizado;
	
	@ManyToOne
	@JoinColumn(name="idVehiculo", nullable = false)
	private Vehiculo vehiculo;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el monto")
	@Size(min=10,max=10,message = "Motor invalido")
	@Column(name="motorPolarizado", length=10, nullable=false)
	private String motorPolarizado;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese el monto")
	@Size(min=10,max=10,message = "Serie invalido")
	@Column(name="seriePolarizado", length=10, nullable=false)
	private String seriePolarizado;
	
	@Past(message="La fecha debe ser pasada")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpedicion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expedicionPolarizado;
	
	@Future(message="La fecha debe ser futura")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpiracion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expiracionPolarizado;

	public String getIdPolarizado() {
		return idPolarizado;
	}

	public void setIdPolarizado(String idPolarizado) {
		this.idPolarizado = idPolarizado;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getMotorPolarizado() {
		return motorPolarizado;
	}

	public void setMotorPolarizado(String motorPolarizado) {
		this.motorPolarizado = motorPolarizado;
	}

	public String getSeriePolarizado() {
		return seriePolarizado;
	}

	public void setSeriePolarizado(String seriePolarizado) {
		this.seriePolarizado = seriePolarizado;
	}

	public Date getExpedicionPolarizado() {
		return expedicionPolarizado;
	}

	public void setExpedicionPolarizado(Date expedicionPolarizado) {
		this.expedicionPolarizado = expedicionPolarizado;
	}

	public Date getExpiracionPolarizado() {
		return expiracionPolarizado;
	}

	public void setExpiracionPolarizado(Date expiracionPolarizado) {
		this.expiracionPolarizado = expiracionPolarizado;
	}

}

