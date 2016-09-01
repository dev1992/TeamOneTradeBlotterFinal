package team1.tradeBlotter.web;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import team1.tradeBlotter.ejb.MessageSessionBeanLocal;
import team1.tradeBlotter.ejb.TradeFilterBeanLocal;
import team1.tradeBlotter.jpa.Message;
import team1.tradeBlotter.jpa.Subject;

@RequestScoped
@Path("/messages")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MessageResource {

	private MessageSessionBeanLocal myLocalBean;

	public MessageResource() {
		super();
		try {
			InitialContext context = new InitialContext();
			myLocalBean = (MessageSessionBeanLocal) context.lookup(
					"java:global/TeamOneTradeBlotterFinal/TeamOneTradeBlotterFinalEJB/MessageSessionBean!team1.tradeBlotter.ejb.MessageSessionBean");
			System.out.println(1);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	@GET
	@Produces("application/json")
	public List<Message> getAllMessages() {
		List<Message> messages = myLocalBean.readAllMessages();
		return messages;
	}

	@POST
	@Path("/create")
	@Consumes("application/x-www-form-urlencoded")
	public String createMessage(@FormParam("messageBody") String messageBody,
			@FormParam("subjectName") String subjectName, @FormParam("userName") String userName) {
		if (myLocalBean.createMessage(messageBody, subjectName, userName)) {
			return "true";
		} else {
			return "false";
		}
	}

	@GET
	@Path("/byuser")
	@Produces("application/json")
	public List<Message> readMessage(@QueryParam("userName") String userName) {
		if (myLocalBean != null)
			return myLocalBean.readMessage(userName);
		else
			return null;
	}

	@GET
	@Path("/bytopic")
	@Produces("application/json")
	public List<Message> readMessagesBytopic(@QueryParam("productType") String productType) {
		if (myLocalBean != null)
			return myLocalBean.readMessageByTopic(productType);
		else
			return null;
	}
	// @DELETE
	// public boolean deleteMessage(@QueryParam("message") Message msg) {
	// return myLocalBean.deleteMessage(msg);
	// }

}
