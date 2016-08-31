package team1.tradeBlotter.ejb;

import javax.ejb.Remote;

import team1.tradeBlotter.jpa.Message;
import team1.tradeBlotter.jpa.Subject;

@Remote
public interface MessageSessionBeanRemote {
	public boolean createMessage(String messageBody, Subject subject);

	public String readMessage(Message msg);

	public boolean deleteMessage(Message msg);
}
