
package il.cshaifasweng.OCSFMediatorExample.entities;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Screenings")
public class Screening {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String screeningDate;
	private String screeningTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie")
	private Movie movie;
	
	public Screening() {};
	
	public Screening (String idate, String itime, Movie movie) {
		screeningDate = idate;
		screeningTime = itime;
		this.movie = movie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return screeningTime;
	}

	public void setTime(String screeningTime) {
		this.screeningTime = screeningTime;
	}
	
	public String getDate() {
		return screeningDate;
	}

	public void setDate(String screeningDate) {
		this.screeningDate = screeningDate;
	}


	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}