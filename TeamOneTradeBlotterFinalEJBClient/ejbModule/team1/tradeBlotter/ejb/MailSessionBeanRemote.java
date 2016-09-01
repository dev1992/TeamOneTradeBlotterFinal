package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.Remote;

import team1.tradeBlotter.jpa.Mail;

@Remote
public interface MailSessionBeanRemote {

	List<Mail> readMail(String userName);
	public boolean sendMail(String mailBody,String userNames, String userNamer );
	List<Mail> getMails();
}
