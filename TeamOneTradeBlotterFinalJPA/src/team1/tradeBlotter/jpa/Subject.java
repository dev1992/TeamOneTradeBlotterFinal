package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subjects database table.
 * 
 */
@Entity
@Table(name="subjects")
@NamedQuery(name="Subject.findAll", query="SELECT s FROM Subject s")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int subjectId;

	@Lob
	private String description;

	private String subjectName;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="subject")
	private List<Message> messages;

	//bi-directional many-to-many association to Trader
	@ManyToMany(mappedBy="subjects")
	private List<Trader> traders;

	public Subject() {
	}

	public int getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setSubject(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setSubject(null);

		return message;
	}

	public List<Trader> getTraders() {
		return this.traders;
	}

	public void setTraders(List<Trader> traders) {
		this.traders = traders;
	}

}