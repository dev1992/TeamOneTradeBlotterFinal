package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.Remote;

import team1.tradeBlotter.jpa.Message;
import team1.tradeBlotter.jpa.Subject;

@Remote
public interface MessageSessionBeanRemote {
	public boolean createMessage(String messageBody, String subjectName, String userName);

	public List<Message>  readMessage(String userName);
	
	public List<Message> readAllMessages();

	public boolean deleteMessage(Message msg);
}
