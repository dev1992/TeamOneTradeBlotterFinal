package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the notes database table.
 * 
 */
@Entity
@Table(name="notes")
@NamedQuery(name="Note.findAll", query="SELECT n FROM Note n")
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NotePK id;

	@Lob
	private String noteText;

	//bi-directional many-to-one association to Trader
	@ManyToOne
	@JoinColumn(name="Traders_traderId")
	private Trader trader;

	public Note() {
	}

	public NotePK getId() {
		return this.id;
	}

	public void setId(NotePK id) {
		this.id = id;
	}

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