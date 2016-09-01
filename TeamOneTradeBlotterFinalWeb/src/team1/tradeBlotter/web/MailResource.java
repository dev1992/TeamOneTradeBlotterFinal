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

import team1.tradeBlotter.ejb.MailSessionBeanLocal;
import team1.tradeBlotter.jpa.Mail;

@RequestScoped
@Path("/mails")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })

public class MailResource {

	private MailSessionBeanLocal myLocalBean ;
	
	public MailResource() {
		super();
		try {
			InitialContext context = new InitialContext();
			myLocalBean = (MailSessionBeanLocal) context.lookup(
					"java:global/TeamOneTradeBlotterFinal/TeamOneTradeBlotterFinalEJB/MailSessionBean!team1.tradeBlotter.ejb.MailSessionBeanLocal");
			System.out.println(1);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	
	@GET
	@Produces("application/json")
	public List<Mail> getAllMails() {
		List<Mail> mails = myLocalBean.getMails();
		return mails;
	}
	
	@POST
	@Path("/send")
	@Consumes("application/x-www-form-urlencoded")
	public String sendMail(@FormParam("mailBody") String mailBody, @FormParam("sender") String userNames, @FormParam("receiver") String userNamer) {
		
		if (myLocalBean.sendMail(mailBody, userNames, userNamer)) {
			return "true";
		} else {
			return "false";
		}
	}
	
	@GET
	@Path("/ofuser")
	@Produces("application/json")
	public List<Mail> readMail(@QueryParam("userName") String userName) {
		if (myLocalBean != null)
			return myLocalBean.readMail(userName);
		else
			return null;
	}
}
