package org.tempestdesign.sendmail;

import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;

public class NoReply {
	
	
	// private static String host[] = {"smtp.gmail.com"};
	private static String host = "smtp.gmail.com";
	private static String port = "587";
	private static String mto, msub;
	
	// 
	//
	
	
	public static void main (String[] args) {
		//
		//
		String user = getUN(),
				pw = getPW();
				
		Date mdate = new Date();
		//
		
		//
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host/*[0]*/);
		prop.put("mail.smtp.port", port);
		//
		Session sesh = Session.getInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, pw);
					}
				}); //
		//
		//
		try {
			
			mto = getTo();
			msub = getSubject();
			
			Message m = new MimeMessage(sesh);
			m.setFrom(new InternetAddress(user));
			m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mto));
			m.setSubject(msub);
			m.setSentDate(mdate);
			
			
			m.setContent(Contents.TEXT, Contents.TYPE);
			

			Transport.send(m);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} // end try/catch //
		
		
		
	} // end main //
	
	/** #NoReply.getUN();#
	 *  
	 * >> Prompts user for GMAIL address.
	 * 
	 * @return Returns valid GMAIL address as type String.
	 */
	
	private static String getUN() {
		
		String UN;
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("(\\W|^)[\\w.+\\-]*@gmail\\.com(\\W|$)"); // Regular Expression to check for valid Gmail username //
		sc.next();
		do {
			System.out.println("\t >> Please enter your full gmail address \n"
						+ "(ex. username@gmail.com, username@google.com, etc. \n)");
				/*  **************************************  */
			sleep(2500);
		} while (!sc.hasNext());
		sc.next();
		UN = sc.next();
		sc.close(); //
		return UN;
	} // end getUN() //
	
	/** #NoReply.getPW();#
	 *  
	 * >> Prompts user for GMAIL account password.
	 * 
	 * @return Returns valid GMAIL password as type String.
	 */
	
	private static String getPW() {
		
		String PW;
		Scanner sc = new Scanner(System.in);
		sc.next();
		do {
			System.out.println("\t >> Please enter your GMAIL account password. \n");
				/* **************************************** */
			if(!sc.hasNext())
				System.out.println("\t >> Please try again... \n");
			sleep(2500);
		} while (!sc.hasNext());
		
		sc.next();
		PW = sc.next();
		sc.close(); //
		return PW;
	} // end getPW() //
	
	private static String getTo() {
		
		
		
		
		return null;
	} // end getTo() //
	
	private static String getSubject() {
		
		
		
		
		return null;
	} // end get Subject() //

	/** #Sleep(int) subroutine#
	 * 
	 *  >> Try/catch subroutine to put the currentThread() to sleep for n milliseconds.
	 * 
	 * @param int timeMS -- Time (in milliseconds) to (try/catch) to put the current thread to sleep.
	 * 
	 */	
	
	public static void sleep(int timeMS) {
		try {
			Thread.sleep(timeMS);
		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	} // end sleep(timeMS) //
	
} // end NoReply class //






class Contents {
	
	public static String TEXT, TYPE;
	
	private Contents() {
		
		
		
	}
	
}















