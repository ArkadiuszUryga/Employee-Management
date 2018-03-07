package pl.com.meridium.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String messageTitle;
	
	
	@Column(columnDefinition="text")
	private String message;
	
	@Column(columnDefinition="text")
	private String answer;
	
	private int status;
	
	private Date added;
	
	private Date forWhen;
	
	@ManyToMany
	private List<User> sender = new ArrayList<>();
	
	@ManyToMany
	private List<User> receiver = new ArrayList<>();
	
	
	public List<User> getSender() {
		return sender;
	}

	public void setSender(List<User> sender) {
		this.sender = sender;
	}

	public List<User> getReceiver() {
		return receiver;
	}

	public void setReceiver(List<User> receiver) {
		this.receiver = receiver;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public Date getForWhen() {
		return forWhen;
	}

	public void setForWhen(Date forWhen) {
		this.forWhen = forWhen;
	}
	
	

}
