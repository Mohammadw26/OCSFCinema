package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "images")
public class Image {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String imgName;
    @Column(length = 100000000)
    private byte[] imagePixels;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "image")
    private Movie movie;
    
    public Image() {}
    
    public Image(String name, byte[] imagePixels) {
        this.imgName = name;
        this.imagePixels = imagePixels;
    }

	public int getId() {
		return id;
	}
	

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
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
	}
    
}
