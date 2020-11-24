package pe.edu.login.idriver.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Documento")
public class Documento {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idDocumento;
	
	@ManyToOne
	@JoinColumn(name="idUsuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idVehiculo", nullable = false)
	private Vehiculo vehiculo;
	
	@ManyToOne
	@JoinColumn(name="idLicencia", nullable = false)
	private Licencia licencia;
	
	@ManyToOne
	@JoinColumn(name="idSeguro", nullable = false)
	private Seguro seguro;
	
	@ManyToOne
	@JoinColumn(name="idSoat", nullable = false)
	private Soat soat;
	
	@ManyToOne
	@JoinColumn(name="idTarjeta", nullable = false)
	private Tarjeta tarjeta;
	
	@ManyToOne
	@JoinColumn(name="idPolarizado")
	private Polarizado polarizado;
	
	@Temporal(TemporalType.DATE)
	@Column(name="anoDocumento")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date DateDocumento;

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
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

	public Licencia getLicencia() {
		return licencia;
	}

	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public Soat getSoat() {
		return soat;
	}

	public void setSoat(Soat soat) {
		this.soat = soat;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Polarizado getPolarizado() {
		return polarizado;
	}

	public void setPolarizado(Polarizado polarizado) {
		this.polarizado = polarizado;
	}

	public Date getDateDocumento() {
		return DateDocumento;
	}

	public void setDateDocumento(Date dateDocumento) {
		DateDocumento = dateDocumento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}


