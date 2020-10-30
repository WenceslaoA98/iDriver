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
@Table(name="Licencia")
public class Licencia {

	@Id
	@Column(name = "idLicencia", nullable = false, length = 11)
	private int idLicencia;
	
	@ManyToOne
	@JoinColumn(name="idUsuario", nullable = false)
	private Usuario usuario;
	
	@Column(name="claseLicencia", length=5, nullable=false)
	private String claseLicencia;
	
	@Column(name="categoriaLicencia", length=5, nullable=false)
	private String categoriaLicencia;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpedicion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expedicionLicencia;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaExpiracion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expiracionLicencia;
	
	@Column(name="restriccionLicencia", length=60, nullable=false)
	private String restriccionLicencia;

	public int getIdLicencia() {
		return idLicencia;
	}

	public void setIdLicencia(int idLicencia) {
		this.idLicencia = idLicencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getClaseLicencia() {
		return claseLicencia;
	}

	public void setClaseLicencia(String claseLicencia) {
		this.claseLicencia = claseLicencia;
	}

	public String getCategoriaLicencia() {
		return categoriaLicencia;
	}

	public void setCategoriaLicencia(String categoriaLicencia) {
		this.categoriaLicencia = categoriaLicencia;
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

	public String getRestriccionLicencia() {
		return restriccionLicencia;
	}

	public void setRestriccionLicencia(String restriccionLicencia) {
		this.restriccionLicencia = restriccionLicencia;
	}
	
}


