package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the traders database table.
 * 
 */
@Entity
@Table(name="traders")
@NamedQuery(name="Trader.findAll", query="SELECT t FROM Trader t")
public class Trader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int traderId;

	private String firstName;

	private String lastName;

	private int password;

	@Lob
	private String scratchPad;

	private String userName;

	//bi-directional many-to-one association to Mail
	@OneToMany(mappedBy="trader")
	private List<Mail> mails;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="trader")
	private List<Message> messages;

	//bi-directional many-to-one association to Note
	@OneToMany(mappedBy="trader")
	private List<Note> notes;

	//bi-directional many-to-many association to Subject
	@ManyToMany
	@JoinTable(
		name="traders_has_subjects"
		, joinColumns={
			@JoinColumn(name="Traders_traderId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Subjects_subjectId")
			}
		)
	private List<Subject> subjects;

	//bi-directional many-to-one association to Trade
	@OneToMany(mappedBy="trader")
	private List<Trade> trades;

	public Trader() {
	}

	public int getTraderId() {
		return this.traderId;
	}

	public void setTraderId(int traderId) {
		this.traderId = traderId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPassword() {
		return this.password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public String getScratchPad() {
		return this.scratchPad;
	}

	public void setScratchPad(String scratchPad) {
		this.scratchPad = scratchPad;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Mail> getMails() {
		return this.mails;
	}

	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}

	public Mail addMail(Mail mail) {
		getMails().add(mail);
		mail.setTrader(this);

		return mail;
	}

	public Mail removeMail(Mail mail) {
		getMails().remove(mail);
		mail.setTrader(null);

		return mail;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setTrader(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setTrader(null);

		return message;
	}

	public List<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public Note addNote(Note note) {
		getNotes().add(note);
		note.setTrader(this);

		return note;
	}

	public Note removeNote(Note note) {
		getNotes().remove(note);
		note.setTrader(null);

		return note;
	}

	public List<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Trade> getTrades() {
		return this.trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public Trade addTrade(Trade trade) {
		getTrades().add(trade);
		trade.setTrader(this);

		return trade;
	}

	public Trade removeTrade(Trade trade) {
		getTrades().remove(trade);
		trade.setTrader(null);

		return trade;
	}

}