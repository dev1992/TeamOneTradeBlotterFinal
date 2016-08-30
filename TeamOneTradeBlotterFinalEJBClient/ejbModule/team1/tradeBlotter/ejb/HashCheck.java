package team1.tradeBlotter.ejb;

public class HashCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String passwd = "password";
		int password = passwd.hashCode();
		System.out.println("HashCode for password: "+password);
	}

}
