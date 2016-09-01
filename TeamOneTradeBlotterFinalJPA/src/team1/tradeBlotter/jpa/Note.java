package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the notes database table.
 * 
 */
@Entity
@Table(name = "notes")
@NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n")
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	// @EmbeddedId
	// private NotePK id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int noteId;

	@Column(insertable = false, updatable = false)
	private int traders_traderId;

	@Lob
	private String noteText;

	// bi-directional many-to-one association to Trader
	@ManyToOne
	@JoinColumn(name = "Traders_traderId")
	@JsonBackReference
	private Trader trader;

	public Note() {
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public int getTraders_traderId() {
		return traders_traderId;
	}

	public void setTraders_traderId(int traders_traderId) {
		this.traders_traderId = traders_traderId;
	}

	// public NotePK getId() {
	// return this.id;
	// }
	//
	// public void setId(NotePK id) {
	// this.id = id;
	// }

	public String getNoteText() {
		return this.noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public Trader getTrader() {
		return this.trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

}