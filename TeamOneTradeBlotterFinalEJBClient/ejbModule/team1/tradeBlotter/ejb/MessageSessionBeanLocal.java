package team1.tradeBlotter.ejb;

import javax.ejb.Local;

import team1.tradeBlotter.jpa.Message;
import team1.tradeBlotter.jpa.Subject;

@Local
public interface MessageSessionBeanLocal {
	public boolean createMessage(String messageBody, Subject subject);

	public String readMessage(Message msg);

	public boolean deleteMessage(Message msg);
}
