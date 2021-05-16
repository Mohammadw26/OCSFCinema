package il.cshaifasweng.OCSFMediatorExample.entities;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "movies")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String movieTitle;
	private String producer;
	private String starring;
	private String movieDescription;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
	private List<Screening> screenings;
	
	public Movie() {};
	
	public Movie (String movieTitle, String producer, String starring, String movieDescription) {
		this.movieTitle = movieTitle;
		this.producer = producer;
		this.starring = starring;
		this.movieDescription = movieDescription;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getStarring() {
		return starring;
	}

	public void setStarring(String starring) {
		this.starring = starring;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public List<Screening> getScreenings() {
		return screenings;
	}

	public void setScreenings(List<Screening> screenings) {
		this.screenings = screenings;
	}
	
	public void addScreening(Screening...Screenings) {
		for (Screening screening : Screenings) {
			screenings.add(screening);
			screening.setMovie(this);
		}
	}
	
	

}