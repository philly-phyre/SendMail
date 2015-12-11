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
			// setContent();
			
			Message m = new MimeMessage(sesh);
			m.setFrom(new InternetAddress(UN));
			m.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mto));
			m.setSubject("l");
			m.setSentDate(mdate);
			
			
			m.setContent(cTEXT, cTYPE);
			

			Transport.send(m);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} // end try/catch //
		
		
		
	} // end main //
	
	/** NoReply.#setUN()#
	 *  
	 * >> Prompts user for GMAIL username/address.
	 * 
	 * >> Checks if user input is suffixed appropriately.
	 * >> If the suffix "@gmail.com" is not attached, an if statement appends the suffix.
	 * 
	 * @return Returns valid GMAIL address as type String.
	 */
	
	private static void setUN() {
		
		sleep(2200);
		String suf = "@gmail.com";
		boolean sufd;
		
		sc.useDelimiter("(\\W|^)[\\w.+\\-]*@gmail\\.com(\\W|$)"); // Regular Expression to check for valid GMAIL username //
		System.out.println(">> Please enter your GMAIL username/address.");
				/*  **************************************  */
		UN = sc.nextLine(); //
		sufd = UN.substring(UN.length()-11,UN.length()-1).equals("@gmail.com");
		if(!sufd)
			UN += suf;
	} // end getUN() //
	
	/** NoReply.#setPW()#
	 *  
	 * >> Prompts user for GMAIL account password.
	 * 
	 * @return Returns valid GMAIL password as type String.
	 */
	
	private static void setPW() {
		
		sleep(2200);
		System.out.println(">> Please enter your GMAIL account password.");
				/* **************************************** */
		PW = sc.nextLine();
		sc.close(); //
	} // end getPW() //
	
	private static void setTo() {
		
		boolean done = false;
		
		sleep(2200);
		System.out.println("");
		System.out.println(">> Please enter the EMAIL addresses you woul like to send your message to.");
		sleep(2200);
		System.out.println("Separate the addresses by pressing the RETURN key each one.");
		System.out.println("");
		sleep(2200);
		System.out.println("Enter \"done/DONE\" when you are finished.");
		do {
			String suf = mto.substring(mto.length()-5,mto.length()-1);
			mto += "," + sc.nextLine();
			if(suf.equals("done") || suf.equals("DONE")) {
				done = true;
				mto.replace(",done", "").replace(",DONE", "");
			} else {
				mto += "," + sc.nextLine();
			}
			
		} while(!done);
		
		
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
	
	public static void setContent() {
		
		cTEXT = "ll";
		cTYPE = "text/plain";
		
	}
	//
	//
	//
	public static void sleep(int timeMS) {
		try {
			Thread.sleep(timeMS);
		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	} // end sleep(timeMS) // 
	
} // end NoReply class //















