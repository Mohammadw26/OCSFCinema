package il.cshaifasweng.OCSFMediatorExample.server;
import il.cshaifasweng.OCSFMediatorExample.entities.Car;
import il.cshaifasweng.OCSFMediatorExample.entities.Image;
import il.cshaifasweng.OCSFMediatorExample.entities.Garage;
import il.cshaifasweng.OCSFMediatorExample.entities.Owner;


import java.io.IOException;

/**
 * Hello world!
 *
 */

import java.util.List;
import java.util.Random;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.io.FileInputStream;

public class CreateDB{

	private static Session session;

	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Car.class);
		configuration.addAnnotatedClass(Garage.class);
		configuration.addAnnotatedClass(Owner.class);
		configuration.addAnnotatedClass(Image.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static void initializeData() throws Exception {

		Garage garage_1 = new Garage("SinCity1");
		Garage garage_2 = new Garage("SinCity2");

		session.save(garage_1);
		session.save(garage_2);
		session.flush();

		Owner owner_1 = new Owner("George", "Clony", "123", "GeorgeClony@gmail.com");
		Owner owner_2 = new Owner("Jenniffer", "Aneston", "1234", "JojoAn@gmail.com");
		Owner owner_3 = new Owner("Jason", "Stetham", "12345", "TheRealJason@gmail.com");
		Owner owner_4 = new Owner("Dwayne", "Johnson", "123456", "TheRock@gmail.com");
		Owner owner_5 = new Owner("Amy", "Whinehouse", "1234567", "Whiny@gmail.com");

		owner_1.addGarages(garage_1, garage_2);
		owner_3.addGarages(garage_2);

		session.save(owner_1);
		session.save(owner_2);
		session.save(owner_3);
		session.save(owner_4);
		session.save(owner_5);
		session.flush();

		File imagfile1 = new File(System.getProperty("user.dir") + "/car1.png");
		File imagfile2 = new File(System.getProperty("user.dir") + "/car2.png");
		File imagfile3 = new File(System.getProperty("user.dir") + "/car3.png");
		File imagfile4 = new File(System.getProperty("user.dir") + "/car4.png");
		File imagfile5 = new File(System.getProperty("user.dir") + "/car5.png");

		byte[] pixelsArray1 = new byte[(int) imagfile1.length()];
		byte[] pixelsArray2 = new byte[(int) imagfile2.length()];
		byte[] pixelsArray3 = new byte[(int) imagfile3.length()];
		byte[] pixelsArray4 = new byte[(int) imagfile4.length()];
		byte[] pixelsArray5 = new byte[(int) imagfile5.length()];

		try {
			FileInputStream Image1pixels = new FileInputStream(imagfile1);
			FileInputStream Image2pixels = new FileInputStream(imagfile2);
			FileInputStream Image3pixels = new FileInputStream(imagfile1);
			FileInputStream Image4pixels = new FileInputStream(imagfile2);
			FileInputStream Image5pixels = new FileInputStream(imagfile1);

			Image1pixels.read(pixelsArray1);
			Image1pixels.close();
			Image2pixels.read(pixelsArray2);
			Image2pixels.close();
			Image3pixels.read(pixelsArray3);
			Image3pixels.close();
			Image4pixels.read(pixelsArray4);
			Image4pixels.close();
			Image5pixels.read(pixelsArray5);
			Image5pixels.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		Image image_1 = new Image("car_1", pixelsArray1);
		Image image_2 = new Image("car_2", pixelsArray2);
		Image image_3 = new Image("car_3", pixelsArray3);
		Image image_4 = new Image("car_4", pixelsArray4);
		Image image_5 = new Image("car_5", pixelsArray5);
		session.save(image_1);
		session.save(image_2);
		session.save(image_3);
		session.save(image_4);
		session.save(image_5);
		session.flush();

		Random random = new Random();
		Car car_1 = new Car("LA-" + random.nextInt(1000), 1200000, 2000 + random.nextInt(19), owner_1, image_1);
		Car car_2 = new Car("NYC-" + random.nextInt(1000), 1200000, 2000 + random.nextInt(19), owner_2, image_2);
		Car car_3 = new Car("CA-" + random.nextInt(1000), 1200000, 2000 + random.nextInt(19), owner_3, image_3);
		Car car_4 = new Car("WD-" + random.nextInt(1000), 1200000, 2000 + random.nextInt(19), owner_4, image_4);
		Car car_5 = new Car("UFO-" + random.nextInt(1000), 1200000, 2000 + random.nextInt(19), owner_5, image_5);

		car_1.addGarages(garage_1);
		car_2.addGarages(garage_1, garage_2);
		car_3.addGarages(garage_2);
		car_4.addGarages(garage_1, garage_2);
		car_5.addGarages(garage_2, garage_1);

		session.save(car_1);
		session.save(car_2);
		session.save(car_3);
		session.save(car_4);
		session.save(car_5);
		session.flush();

		session.getTransaction().commit();

	}

	public static <T> List<T> getAll(Class<T> object) throws Exception {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
		Root<T> rootEntry = criteriaQuery.from(object);
		CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

		TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
		return allQuery.getResultList();
	}

	public static void main(String[] args) {
		try {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();

			initializeData();

			List<Car> cars = getAll(Car.class);
			List<Garage> garages = getAll(Garage.class);

			System.out.println("Garages list:");
			for (Garage garage : garages) {
				System.out.format("ID: %d, Address: %s \n", garage.getId(), garage.getAddress());
				System.out.format("Garage owners:\n");
				for (Owner owner : garage.getOwners()) {
					System.out.format("	- Name: %s %s \n", owner.getFirstName(), owner.getLastName());
				}

				System.out.format("Assosiated cars:\n");
				for (Car car : garage.getCarsList()) {
					System.out.format("	- License plate: %s \n", car.getLicensePlate());
				}
			}
			System.out.format("\n");

			System.out.println("Cars list:");
			for (Car car : cars) {
				System.out.format("ID: %d, License plate: %s, Price: %f, Year: %d\n", car.getId(),
						car.getLicensePlate(), car.getPrice(), car.getYear());
				System.out.format("Owner info:");
				System.out.format("Fist name: %s, Last name: %s, Password: %s, E-mail: %s \n",
						car.getOwner().getFirstName(), car.getOwner().getLastName(), car.getOwner().getPassword(),
						car.getOwner().getEmail());

				System.out.format("Assosiated garages:\n");
				for (Garage garage : car.getGarageList()) {
					System.out.format("	- Garage Address: %s\n", garage.getAddress());
				}
			}
			System.out.format("\n\n");

		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				session.getSessionFactory().close();
			}
		}
	}
	
}
