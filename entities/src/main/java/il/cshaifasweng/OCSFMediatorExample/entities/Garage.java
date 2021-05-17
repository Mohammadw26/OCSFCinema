package il.cshaifasweng.OCSFMediatorExample.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Garages")
public class Garage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String address;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Owner.class)
	@JoinTable(name = "garages_owners", joinColumns = @JoinColumn(name = "garage_id"), inverseJoinColumns = @JoinColumn(name = "owner_id"))
	private List<Owner> owners;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Car.class)
	@JoinTable(name = "garages_cars", joinColumns = @JoinColumn(name = "garage_id"), inverseJoinColumns = @JoinColumn(name = "car_id"))
	private List<Car> cars;

	public Garage() {

	}

	public Garage(String address) {
		this.address = address;
		this.owners = new ArrayList<Owner>();
		this.cars = new ArrayList<Car>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Car> getCarsList() {
		return cars;
	}

	public void setCarsList(List<Car> cars) {
		this.cars = cars;
	}

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

}
