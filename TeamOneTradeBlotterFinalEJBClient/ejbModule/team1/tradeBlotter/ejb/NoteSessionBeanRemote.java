package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.Remote;

import team1.tradeBlotter.jpa.Note;

@Remote
public interface NoteSessionBeanRemote {

	boolean createNote(String noteText, String userName);

	List<Note> readNote(String userName);

	boolean deleteNote(Note note);
	
	public List<Note> getNotes();

}
