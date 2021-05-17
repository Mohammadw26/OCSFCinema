package il.cshaifasweng.OCSFMediatorExample.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String licensePlate;
	private double price;
	@Column(name = "manufacturing_year")
	private int year;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private Owner owner;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,targetEntity = Image.class)
	private Image image;
	
	@ManyToMany(mappedBy = "cars",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			targetEntity = Garage.class
	)
	private List<Garage> garages;
	
	public Car(String licensePlate, double price, int year, Owner owner,Image image) {
		super();
		this.licensePlate = licensePlate;
		this.price = price;
		this.year = year;
		setOwner(owner); 
		setImage(image);
		this.garages = new ArrayList<Garage>();
	}
	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
		owner.getCars().add(this); 
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getId() {
		return id;
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
			garage.getCarsList().add(this);
		}
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		image.setCar(this);
	}
	
}