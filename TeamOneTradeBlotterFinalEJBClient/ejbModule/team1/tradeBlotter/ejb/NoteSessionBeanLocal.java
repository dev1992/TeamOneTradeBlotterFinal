package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.Local;

import team1.tradeBlotter.jpa.Note;

@Local
public interface NoteSessionBeanLocal {

	boolean createNote(String noteText, String userName);

	List<Note> readNote(String userName);

	boolean deleteNote(Note note);
	
	public List<Note> getNotes();

}
