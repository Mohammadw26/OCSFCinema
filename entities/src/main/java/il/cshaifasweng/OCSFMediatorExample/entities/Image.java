package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.*;

@Entity
@Table(name = "images")

public class Image {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name = "image_name")
	private String name;
    @Column(length = 100000000)
    private byte[] imagePixels;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "image")
    private Movie movie;
    
    public Image() {}
    
    public Image(String name, byte[] imagePixels) {
        this.name = name;
        this.imagePixels = imagePixels;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImagePixels() {
		return imagePixels;
	}

	public void setImagePixels(byte[] imagePixels) {
		this.imagePixels = imagePixels;
	}
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
		movie.setMovieImage(this);
	}
    
}
