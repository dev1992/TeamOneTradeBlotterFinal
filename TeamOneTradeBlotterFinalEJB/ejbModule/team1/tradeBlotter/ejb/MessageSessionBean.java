package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import team1.tradeBlotter.jpa.Message;
import team1.tradeBlotter.jpa.Subject;
import team1.tradeBlotter.jpa.Trader;

/**
 * Session Bean implementation class MessageSessionBean
 */
@Stateful
@LocalBean
public class MessageSessionBean implements MessageSessionBeanRemote, MessageSessionBeanLocal {

	@PersistenceContext(name = "TeamOneTradeBlotterFinalJPA")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public MessageSessionBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createMessage(String messageBody, String subjectName, String userName) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("Select s from Subject AS s where s.subjectName = :subjectName", Subject.class);
		query.setParameter("subjectName", subjectName);
		Subject subject = (Subject) query.getResultList().get(0);

		Query query2 = em.createQuery("Select t from Trader AS t where t.userName = :userName", Trader.class);
		query2.setParameter("userName", userName);
		Trader creator = (Trader) query2.getResultList().get(0);
		System.out.println("Creator -> " + creator.getUserName());
		System.out.println("Subject -> " + subject.getSubjectName());
		Message msg = new Message();
		// System.out.println("Message id is ---> " + msg.getId());
		msg.setMessageBody(messageBody);
		msg.setSubject(subject);
		msg.setTrader(creator);
		em.persist(msg);
		em.flush();
		if (msg != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<Message> readMessage(String userName) {

		TypedQuery<Trader> query = em.createQuery("SELECT distinct t FROM Trader t WHERE t.userName = :userName",
				Trader.class);
		query.setParameter("userName", userName);
		Trader creator = (Trader) query.getSingleResult();

		// System.out.println("\n" + receiverList.contains(new Trader("user1",
		// "password".hashCode(), "admin", "admin")) +
		// "\n" + receiverList.size());

		if (creator != null) {

			System.out.println("Creator ---> " + creator.getUserName() + "\n\n");

			TypedQuery<Message> query2 = em.createQuery("Select m from Message m where m.trader = :creator",
					Message.class);
			query2.setParameter("creator", creator);
			List<Message> messages = query2.getResultList();
			System.out.println(messages.size());

			return messages;
		} else {
			System.out.println("no receiver found");
			return null;
		}

	}

	@Override
	public boolean deleteMessage(Message msg) {
		// TODO Auto-generated method stub
		if (msg != null) {
			em.remove(msg);
			msg = null;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Message> readAllMessages() {
		TypedQuery<Message> query = em.createQuery("SELECT m FROM Message AS m", Message.class);
		List<Message> messages = query.getResultList();
		return messages;
	}

	@Override
	public List<Message> readMessageByTopic(String productType) {
		TypedQuery<Subject> query = em.createQuery("SELECT distinct s FROM Subject s WHERE s.subjectName = :productType", Subject.class);
		query.setParameter("productType", productType);
		Subject subject = (Subject) query.getSingleResult();

		if (subject != null) {

			TypedQuery<Message> query2 = em.createQuery("Select m from Message m where m.subject = :subject",
					Message.class);
			query2.setParameter("subject", subject);
			List<Message> messages = query2.getResultList();

			return messages;
		}
		return null;
	}

}