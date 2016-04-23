package entities.GPS;

import java.io.Serializable;
import java.util.Date;
import entities.person.Person;

public class Gps implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Double idGps;// id for gps
	Date gpsDate;
	Double x;
	Double y;
	Person personId;

	public Gps(Double idGps, Double x, Double y, Date gpsDate, Person personId) {
		this.idGps = idGps;
		this.x = x;
		this.y = y;
		this.personId = personId;
		this.gpsDate = gpsDate;
	}

	public void setPerson(Person person) {
		this.personId = person;
		person.addGps(this);
	}

	public Double getIdGps() {
		return idGps;
	}

	public void setIdGps(Double idGps) {
		this.idGps = idGps;
	}

	public Date getGpsDate() {
		return gpsDate;
	}

	public void setGpsDate(Date gpsDate) {
		this.gpsDate = gpsDate;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Person getPerson() {
		return personId;
	}

}