package pl.com.meridium.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import pl.com.meridium.validation.LoginUserGroup;


@Entity
@Table(name="users")
public class User {
	
	public User() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	
	@NotEmpty(groups= {Default.class, LoginUserGroup.class})
	@Email(groups= {Default.class, LoginUserGroup.class})
	@Column(unique=true)
	private String user;
	
	@NotEmpty
	@Size(min=3, max=50)
	@Pattern(regexp="[a-zA-Z]+", message="Tylko duże i małe litery")
	private String firstName;
	
	@NotEmpty
	@Size(min=3, max=50)
	@Pattern(regexp="[a-zA-Z\\-]+", message="Tylko duże i małe litery, ewentualnie myślnik")
	private String secondName;
	
	@NotEmpty(groups= {LoginUserGroup.class})
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")

	private List<Grade> grades = new ArrayList<>();
	
	

	private int etat;
	
	private int urlop;
	
	private int ranga;
	
	private Date umowa;
	
	private Date badania;
	
	private int wymiar_urlopu;
	
	private int opieka;
	
	private int defaultHour;
	
	private int hoursFromLastMonth;
	
	private int hoursForNextMonth;
	
	

	public int getHoursForNextMonth() {
		return hoursForNextMonth;
	}

	public void setHoursForNextMonth(int hoursForNextMonth) {
		this.hoursForNextMonth = hoursForNextMonth;
	}

	public int getHoursFromLastMonth() {
		return hoursFromLastMonth;
	}

	public void setHoursFromLastMonth(int hoursFromLastMonth) {
		this.hoursFromLastMonth = hoursFromLastMonth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getOpieka() {
		return opieka;
	}

	public void setOpieka(int opieka) {
		this.opieka = opieka;
	}

	public int getDefaultHour() {
		return defaultHour;
	}

	public void setDefaultHour(int defaultHour) {
		this.defaultHour = defaultHour;
	}

	public long getId() {
		return id;
	}
	
	

	public void setId(long id) {
		this.id = id;
	}





	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getUrlop() {
		return urlop;
	}

	public void setUrlop(int urlop) {
		this.urlop = urlop;
	}

	public int getRanga() {
		return ranga;
	}

	public void setRanga(int ranga) {
		this.ranga = ranga;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	
	
}
