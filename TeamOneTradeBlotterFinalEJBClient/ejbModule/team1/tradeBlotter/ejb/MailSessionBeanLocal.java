package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.Local;

import team1.tradeBlotter.jpa.Mail;

@Local
public interface MailSessionBeanLocal {

	List<Mail> readMail(String userName);
	public boolean sendMail(String mailBody,String userNames, String userNamer );
	List<Mail> getMails();

}
