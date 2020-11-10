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
@Table(name="Polarizado")
public class Polarizado {

	@Id
	@Column(name = "idPolarizado", nullable = false, length = 11)
	private String idPolarizado;
	
	@ManyToOne
	@JoinColumn(name="idVehiculo", nullable = false)
	private Vehiculo vehiculo;
	
	@Column(name="motorPolarizado", length=10, nullable=false)
	private String motorPolarizado;
	
	@Column(name="seriePolarizado", length=10, nullable=false)
	private String seriePolarizado;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpedicion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expedicionPolarizado;
	
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

