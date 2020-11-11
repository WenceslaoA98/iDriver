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
@Table(name="Soat")
public class Soat {

	@Id
	@Column(name = "idSoat", nullable = false, length = 9)
	private String idSoat;
	
	@ManyToOne
	@JoinColumn(name="idUsuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idVehiculo", nullable = false)
	private Vehiculo vehiculo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpedicion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expedicionSoat;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpiracion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expiracionSoat;

	public String getIdSoat() {
		return idSoat;
	}

	public void setIdSoat(String idSoat) {
		this.idSoat = idSoat;
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

	public Date getExpedicionSoat() {
		return expedicionSoat;
	}

	public void setExpedicionSoat(Date expedicionSoat) {
		this.expedicionSoat = expedicionSoat;
	}

	public Date getExpiracionSoat() {
		return expiracionSoat;
	}

	public void setExpiracionSoat(Date expiracionSoat) {
		this.expiracionSoat = expiracionSoat;
	}
}

