package pe.edu.idriver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Provincia")
public class Province {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idProvince;
	
	@Column(name="nombreProvincia", length=60, nullable=false)
	private String nameProvince;

	@ManyToOne
	@JoinColumn(name="idDepartment", nullable = false)
	private Department department;
	
	public int getIdProvince() {
		return idProvince;
	}

	public void setIdProvince(int idProvince) {
		this.idProvince = idProvince;
	}

	public String getNameProvince() {
		return nameProvince;
	}

	public void setNameProvince(String nameProvince) {
		this.nameProvince = nameProvince;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
