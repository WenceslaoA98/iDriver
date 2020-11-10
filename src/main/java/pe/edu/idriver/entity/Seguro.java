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
@Table(name="Seguro")
public class Seguro {

	@Id
	@Column(name = "idSeguro", nullable = false, length = 11)
	private String idSeguro;
	
	@ManyToOne
	@JoinColumn(name="idUsuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idVehiculo", nullable = false)
	private Vehiculo vehiculo;
	
	@Column(name="montoSeguro", length=10, nullable=false)
	private String montoSeguro;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpedicion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expedicionSeguro;
	
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

