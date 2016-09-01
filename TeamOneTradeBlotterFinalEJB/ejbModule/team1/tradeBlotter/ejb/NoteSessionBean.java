package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import team1.tradeBlotter.jpa.Message;
import team1.tradeBlotter.jpa.Note;
import team1.tradeBlotter.jpa.Trader;

/**
 * Session Bean implementation class NoteSessionBean
 */
@Stateful
public class NoteSessionBean implements NoteSessionBeanRemote, NoteSessionBeanLocal {

	@PersistenceContext(name = "TeamOneTradeBlotterFinalJPA")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public NoteSessionBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createNote(String noteText, String userName) {
		// TODO Auto-generated method stub

		TypedQuery<Trader> query = em.createQuery("SELECT distinct t FROM Trader t WHERE t.userName = :userName",
				Trader.class);
		query.setParameter("userName", userName);
		Trader creator = (Trader) query.getSingleResult();

		System.out.println("Creator -> " + creator.getUserName());

		Note note = new Note();
		// noteText, creator
		note.setNoteText(noteText);
		note.setTrader(creator);
		em.persist(note);
		em.flush();

		if (note != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Note> readNote(String userName) {

		TypedQuery<Trader> query = em.createQuery("SELECT distinct t FROM Trader t WHERE t.userName = :userName",
				Trader.class);
		query.setParameter("userName", userName);
		Trader creator = (Trader) query.getSingleResult();

		if (creator != null) {

			System.out.println("Creator ---> " + creator.getUserName() + "\n\n");

			TypedQuery<Note> query2 = em.createQuery("Select n from Note n where n.trader = :creator", Note.class);
			query2.setParameter("creator", creator);
			List<Note> notes = query2.getResultList();
			System.out.println(notes.size());

			return notes;
		} else {
			System.out.println("no notes found");
			return null;
		}

	}

	@Override
	public boolean deleteNote(Note note) {
		// TODO Auto-generated method stub
		if (note != null) {
			em.remove(note);
			note = null;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Note> getNotes() {
		TypedQuery<Note> query = em.createQuery("SELECT n FROM Note AS n", Note.class);
		List<Note> notes = query.getResultList();
		return notes;
	}

}
