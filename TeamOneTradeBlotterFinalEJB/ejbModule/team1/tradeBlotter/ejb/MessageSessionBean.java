package team1.tradeBlotter.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import team1.tradeBlotter.jpa.Message;
import team1.tradeBlotter.jpa.Subject;

/**
 * Session Bean implementation class MessageSessionBean
 */
@Stateful
@LocalBean
public class MessageSessionBean implements MessageSessionBeanRemote, MessageSessionBeanLocal {

	/**
	 * Default constructor.
	 */
	public MessageSessionBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createMessage(String messageBody, Subject subject) {
		// TODO Auto-generated method stub
		Message msg = new Message(messageBody, subject);
		if (msg != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String readMessage(Message msg) {
		// TODO Auto-generated method stub
		return msg.getMessageBody();
	}

	@Override
	public boolean deleteMessage(Message msg) {
		// TODO Auto-generated method stub
		if (msg != null) {
			msg = null;
			return true;
		} else {
			return false;
		}
	}

}