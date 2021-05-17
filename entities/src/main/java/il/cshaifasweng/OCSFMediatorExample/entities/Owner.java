package il.cshaifasweng.OCSFMediatorExample.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;




@Entity
@Table(name = "owners")

public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	@Column(name = "owner_password")
	private String password;
	private String email;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
	private List<Car> cars;
	
	
	@ManyToMany(mappedBy = "owners",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			targetEntity = Garage.class
	)
	private List<Garage> garages;
	
	public Owner() {}
	
	public Owner(String firstName, String lastName, String password, String email ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.cars = new ArrayList<Car>();
		this.garages = new ArrayList<Garage>();
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	public List<Garage> getGarageList() {
		return garages;
	}

	public void setGarageList(List<Garage> garageList) {
		this.garages = garageList;
	}
	
	public void addGarages(Garage... Garages) {
		for (Garage garage : Garages) {
			garages.add(garage);
			garage.getOwners().add(this);
		}
	}
	
}

