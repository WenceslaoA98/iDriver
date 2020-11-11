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
@Table(name="Tarjeta")
public class Tarjeta {

	@Id
	@Column(name = "idTarjeta", nullable = false, length = 11)
	private String idTarjeta;
	
	@ManyToOne
	@JoinColumn(name="idVehiculo", nullable = false)
	private Vehiculo vehiculo;
	
	@Column(name="partidaTarjeta", length=10, nullable=false)
	private String partidaTarjeta;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpedicion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expedicionTarjeta;
	
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

