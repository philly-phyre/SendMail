package org.tempestdesign.sendmail;

import java.util.Date;
import java.util.NoSuchElementException;
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
			setContent();
			
			Message m = new MimeMessage(sesh);
			m.setFrom(new InternetAddress(UN));
			m.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mto)); //////////////////////////////////////////
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
		
		String suf = "@gmail.com";
		boolean sufd;
		
		// sc.useDelimiter("(\\W|^)[\\w.+\\-]*@gmail\\.com(\\W|$)"); // Regular Expression to check for valid GMAIL username //
		System.out.println(">> Please enter your GMAIL username/address.");
				/*  **************************************  */
		UN = sc.nextLine(); //
		if(UN.length() >= 11){
			sufd = UN.substring(UN.length()-11,UN.length()-1).equals("@gmail.com");
			if(!sufd) {
				UN += suf;
			}
		} else if(UN.length() <= 11) {
			UN += suf;
		}
	} // end getUN() //
	
	/** NoReply.#setPW()#
	 *  
	 * >> Prompts user for GMAIL account password.
	 * 
	 * @return Returns valid GMAIL password as type String.
	 */
	
	private static void setPW() {
		
		//sleep(900);
		System.out.println(">> Please enter your GMAIL account password.");
				/* **************************************** */
		PW = sc.nextLine();
	} // end getPW() //
	
	private static void setTo() {
		
		//sleep(900);
		System.out.println(">> ?");
		//sleep(900);
		System.out.println(">> Please enter the EMAIL address(es) you would like to send your message to.");
		//sleep(900);
		System.out.println(">> For multiple addresses, separate with a comma and no space.");
		//sleep(900);
		System.out.println(">> (ex. \"me@here.com,you@there.com,them@where.com\")");
		try {
			mto = sc.nextLine();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} //
		
	} // end getTo() //
	
	private static void setSubject() {
		//sleep(900);
		System.out.println(">> Please enter the subject for your message.");
		msub = sc.nextLine();
	} // end get Subject() //

	/** #Sleep(int) subroutine#
	 * 
	 *  >> Try/catch subroutine to put the currentThread() to sleep for n milliseconds.
	 * 
	 * @param int timeMS -- Time (in milliseconds) to (try/catch) to put the current thread to sleep.
	 * 
	 */	
	
	public static void setContent() {
		//sleep(900);
		System.out.println(">> Perfect. Now begin typing your message.");
		// sleep(900);
		System.out.println(">> You may press RETURN at the end of each sentence or stopping point.");
		System.out.println(">> RETURN a blank line to end the process and send the email.");
		while(sc.hasNextLine() && !(sc.nextLine().equals(""))) {
			cTEXT += sc.toString() + " \n ";
		}
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















