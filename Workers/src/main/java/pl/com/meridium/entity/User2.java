package pl.com.meridium.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="userscd")
public class User2 {
	public User2() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Date umowa;
	
	private Date badania;
	
	private int wymiar_urlopu;
	
	private int opieka;
	
	private int defaultHour;
	


	public int getDefaultHour() {
		return defaultHour;
	}

	public void setDefaultHour(int defaultHour) {
		this.defaultHour = defaultHour;
	}

	public int getOpieka() {
		return opieka;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getUmowa() {
		return umowa;
	}

	public void setUmowa(Date umowa) {
		this.umowa = umowa;
	}

	public Date getBadania() {
		return badania;
	}

	public void setBadania(Date badania) {
		this.badania = badania;
	}

	public int getWymiar_urlopu() {
		return wymiar_urlopu;
	}

	public void setWymiar_urlopu(int wymiar_urlopu) {
		this.wymiar_urlopu = wymiar_urlopu;
	}


	public void setOpieka(int opieka) {
		this.opieka = opieka;
	}
	
	
}
