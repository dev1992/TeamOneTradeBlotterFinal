package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the messages database table.
 * 
 */
@Entity
@Table(name="messages")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MessagePK id;

	@Lob
	private String messageBody;

	//bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name="Subjects_subjectId")
	private Subject subject;

	//bi-directional many-to-one association to Trader
	@ManyToOne
	@JoinColumn(name="Traders_traderId")
	private Trader trader;

	public Message(String messageBody,Subject subject) {
		this.messageBody = messageBody;
		this.subject = subject;
	}
	
	public Message() {
	}

	public MessagePK getId() {
		return this.id;
	}

	public void setId(MessagePK id) {
		this.id = id;
	}

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