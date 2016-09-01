package team1.tradeBlotter.web;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import team1.tradeBlotter.ejb.NoteSessionBeanLocal;
import team1.tradeBlotter.jpa.Note;

@RequestScoped
@Path("/notes")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class NoteResource {

	private NoteSessionBeanLocal myLocalBean ;
	
	public NoteResource() {
		super();
		try {
			InitialContext context = new InitialContext();
			myLocalBean = (NoteSessionBeanLocal) context.lookup(
					"java:global/TeamOneTradeBlotterFinal/TeamOneTradeBlotterFinalEJB/NoteSessionBean!team1.tradeBlotter.ejb.NoteSessionBeanLocal");
			System.out.println(1);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	
	@GET
	@Produces("application/json")
	public List<Note> getAllNotes() {
		List<Note> notes = myLocalBean.getNotes();
		return notes;
	}
	
	@POST
	@Path("/create")
	@Consumes("application/x-www-form-urlencoded")
	public String createNote(@FormParam("noteText") String noteText, @FormParam("userName") String userName) {
		
		if (myLocalBean.createNote(noteText, userName)) {
			return "true";
		} else {
			return "false";
		}
	}
	
	@GET
	@Path("/byuser")
	@Produces("application/json")
	public List<Note> readNote(@QueryParam("userName") String userName) {
		if (myLocalBean != null)
			return myLocalBean.readNote(userName);
		else
			return null;
	}
	
//	@POST
//	@Path("/deletenote")
//	@Produces("application/json")
//	public boolean deleteNote(@QueryParam("note") Note note) {
//		return myLocalBean.deleteNote(note);
//	}
	
}
