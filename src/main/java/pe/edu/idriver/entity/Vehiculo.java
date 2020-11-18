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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Vehiculo")
public class Vehiculo {

	@Id
	@Column(name = "idVehiculo", nullable = false, length = 12)
	private String idVehiculo;
	
	@NotEmpty(message = "El campo no puede estar vacío")
	@NotBlank(message = "Ingrese la placa del Vehiculo")
	@Size(min=6,max=6,message = "El codigo debe tener 6 digitos")
	@Column(name="placaVehiculo", length=6, nullable=false)
	private String placaVehiculo;
	
	@ManyToOne
	@JoinColumn(name="idCarModel", nullable = false)
	private CarModel carmodel;
	
	@ManyToOne
	@JoinColumn(name="idColor", nullable = false)
	private Color color;
	
	@Past(message = "La fecha debe ser pasada")
	@Column(name="anoVehiculo", length=30, nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy")
	private Date DateVehiculo;

	public String getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(String idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getPlacaVehiculo() {
		return placaVehiculo;
	}

	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
	}

	public CarModel getCarmodel() {
		return carmodel;
	}

	public void setCarmodel(CarModel carmodel) {
		this.carmodel = carmodel;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Date getDateVehiculo() {
		return DateVehiculo;
	}

	public void setDateVehiculo(Date dateVehiculo) {
		DateVehiculo = dateVehiculo;
	}

	
}


