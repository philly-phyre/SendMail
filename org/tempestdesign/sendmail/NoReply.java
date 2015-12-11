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
	@SuppressWarnings("unused")
	private static String mto, msub, UN, PW, cTEXT, cTYPE;
	
	private static Scanner sc = new Scanner(System.in);
	// 
	//
	
	
	public static void main (String[] args) {
		//
		//
		setUN();
		setPW();
				
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
						return new PasswordAuthentication(UN, PW);
					}
				}); //
		//
		//
		try {
			
			setTo();
			setSubject();
			
			Message m = new MimeMessage(sesh);
			m.setFrom(new InternetAddress(UN));
			m.setRecipients(Message.RecipientType.TO, InternetAddress.parse("admin@tempestdesign.org"));
			m.setSubject("l");
			m.setSentDate(mdate);
			
			
			m.setContent(cTEXT, cTYPE);
			

			Transport.send(m);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} // end try/catch //
		
		
		
	} // end main //
	
	public static void setContent() {
		
		cTEXT = "ll";
		cTYPE = "text/plain";
		
	}
	
	/** #NoReply.getUN();#
	 *  
	 * >> Prompts user for GMAIL address.
	 * 
	 * @return Returns valid GMAIL address as type String.
	 */
	
	private static void setUN() {
		
		sc.useDelimiter("(\\W|^)[\\w.+\\-]*@gmail\\.com(\\W|$)"); // Regular Expression to check for valid GMAIL username //
		System.out.println(">> Please enter your full GMAIL address.");
				/*  **************************************  */
		UN = sc.nextLine(); //
	} // end getUN() //
	
	/** #NoReply.getPW();#
	 *  
	 * >> Prompts user for GMAIL account password.
	 * 
	 * @return Returns valid GMAIL password as type String.
	 */
	
	private static void setPW() {
		
		System.out.println(">> Please enter your GMAIL account password.");
				/* **************************************** */
		PW = sc.nextLine();
		sc.close(); //
	} // end getPW() //
	
	private static void setTo() {
		
		
		
		
		mto = null;
	} // end getTo() //
	
	private static void setSubject() {
		
		
		
		
		msub = null;
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















