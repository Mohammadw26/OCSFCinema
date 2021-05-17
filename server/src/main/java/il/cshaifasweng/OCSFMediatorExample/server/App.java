package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;

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

import il.cshaifasweng.OCSFMediatorExample.entities.Image;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;

import java.io.File;
import java.io.FileInputStream;
/**
 * Hello world!
 *
 */
public class App 
{
	private static Session session;
	private static SimpleServer server;
	
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(Image.class);
		configuration.addAnnotatedClass(Screening.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	
	private static void initializeData() throws Exception {
		
		File imagfile1 = new File(System.getProperty("user.dir") + "/Haunt.jpg");

	    byte[] pixelsArray1 = new byte[(int) imagfile1.length()];

	    try {
	    	FileInputStream Image1pixels = new FileInputStream(imagfile1);
	    	
	    	Image1pixels.read(pixelsArray1);
	    	Image1pixels.close();

	     } catch (Exception e) {
	        e.printStackTrace();
	     }

	     Image image1 = new Image("Haunteddddddd",pixelsArray1);
	     session.save(image1);
	     session.flush();

		Movie movie1 = new Movie("Haunt","","Katie Stevens, Will Brittain, Lauryn Alisa McClain","On Halloween, a group of friends encounter an \"extreme\" haunted house that promises to feed on their darkest fears. The night turns deadly as they come to the horrifying realization that some nightmares are real.",44,image1);
	    Screening scrn1 = new Screening("01/06/21","00:30",movie1);
	    Screening scrn2 = new Screening("02/06/21","10:15",movie1);
	    	session.save(movie1);
	    	session.save(scrn1);
	    	session.save(scrn2);
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
    
	public static void main(String[] args) throws Exception {
        server = new SimpleServer(3000);
        server.listen();
        
		try {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();

			initializeData();
			
			List<Movie> movies = getAll(Movie.class);
			List<Screening> screenings = getAll(Screening.class);
			List<Image> images = getAll(Image.class);
			
			System.out.println("Movies list:");
        	for (Movie movie : movies) {
        		System.out.format("Movie: %s, Starring: % \n",movie.getMovieTitle(),movie.getStarring());
        		System.out.format("Garage owners:\n");
        		for (Screening screening: movie.getScreenings()) {
        			System.out.format("%s - at %s", screening.getDate(),screening.getTime());
        		}
        	}
        	System.out.format("\n");
        	
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