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
@Table(name="Vehiculo")
public class Vehiculo {

	@Id
	@Column(name = "idVehiculo", nullable = false, length = 12)
	private int idVehiculo;
	
	@Column(name="placaVehiculo", length=60, nullable=false)
	private String placaVehiculo;
	
	@ManyToOne
	@JoinColumn(name="idCarModel", nullable = false)
	private CarModel carmodel;
	
	@ManyToOne
	@JoinColumn(name="idColor", nullable = false)
	private Color color;
	
	@Temporal(TemporalType.DATE)
	@Column(name="anoVehiculo")
	@DateTimeFormat(pattern="yyyy")
	private Date DateVehiculo;

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
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


