package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the messages database table.
 * 
 */
@Entity
@Table(name = "messages")
@NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private MessagePK id;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int messageId;

	@Lob
	private String messageBody;

	// bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name = "Subjects_subjectId")
	@JsonBackReference
	private Subject subject;

	// bi-directional many-to-one association to Trader
	@ManyToOne
	@JoinColumn(name = "Traders_traderId")
	@JsonBackReference
	private Trader trader;

	public Message() {
	}
	
	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

//	public MessagePK getId() {
//		return this.id;
//	}
//
//	public void setId(MessagePK id) {
//		this.id = id;
//	}

	public String getMessageBody() {
		return this.messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Trader getTrader() {
		return this.trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

}