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
	private int screeningDay;
	private int screeningMonth;
	private int screeningYear;
	private int screeningTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "screenings")
	private Movie movie;
	
	public Screening() {};
	
	public Screening (int day, int month, int year, int time, Movie movie) {
		screeningDay = day;
		screeningMonth = month;
		screeningYear = year;
		screeningTime = time;
		this.movie = movie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScreeningDay() {
		return screeningDay;
	}

	public void setScreeningDay(int screeningDay) {
		this.screeningDay = screeningDay;
	}

	public int getScreeningMonth() {
		return screeningMonth;
	}

	public void setScreeningMonth(int screeningMonth) {
		this.screeningMonth = screeningMonth;
	}

	public int getScreeningYear() {
		return screeningYear;
	}

	public void setScreeningYear(int screeningYear) {
		this.screeningYear = screeningYear;
	}

	public int getScreeningTime() {
		return screeningTime;
	}

	public void setScreeningTime(int screeningTime) {
		this.screeningTime = screeningTime;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	
	

}
