package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import team1.tradeBlotter.jpa.Mail;
import team1.tradeBlotter.jpa.Trader;

/**
 * Session Bean implementation class MailSessionBean
 */
@Stateful
@LocalBean
public class MailSessionBean implements MailSessionBeanRemote, MailSessionBeanLocal {

	@PersistenceContext(name = "TeamOneTradeBlotterFinalJPA")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public MailSessionBean() {
		// TODO Auto-generated constructor stub
	}

	public boolean sendMail(String mailBody, String userNames, String userNamer) {

		Query query1 = em.createQuery("Select t from Trader AS t where t.userName = :userName", Trader.class);
		query1.setParameter("userName", userNames);
		Trader sender = (Trader) query1.getResultList().get(0);

		Query query2 = em.createQuery("Select t from Trader AS t where t.userName = :userName", Trader.class);
		query2.setParameter("userName", userNamer);
		Trader receiver = (Trader) query2.getResultList().get(0);
		int id = receiver.getTraderId();

		System.out.println("Sender -> " + sender.getUserName());
		System.out.println("Receiver -> " + receiver.getUserName());

		Mail mail = new Mail();
		mail.setMailBody(mailBody);
		mail.setTrader(sender);
		mail.setReceiverId(id);

		em.persist(mail);
		em.flush();
		if (mail != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<Mail> readMail(String userName) {

		TypedQuery<Trader> query = em.createQuery("SELECT distinct t FROM Trader t WHERE t.userName = :userName",
				Trader.class);
		query.setParameter("userName", userName);
		Trader user = (Trader) query.getSingleResult();
		int id = user.getTraderId();

		if (user != null) {

			System.out.println("User ---> " + user.getUserName() + "\n\n");

			TypedQuery<Mail> query2 = em.createQuery("Select m from Mail m where m.receiverId = :id", Mail.class);
			query2.setParameter("id", id);
			List<Mail> mails = query2.getResultList();
			System.out.println(mails.size());

			return mails;
		} else {
			System.out.println("no mails found");
			return null;
		}

	}
	
	@Override
	public List<Mail> getMails() {
		TypedQuery<Mail> query = em.createQuery("SELECT m FROM Mail AS m", Mail.class);
		List<Mail> mails = query.getResultList();
		return mails;
	}
}
