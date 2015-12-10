package org.tempestdesign.sendmail;

import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.*;

public class NoReply {

	
	
	public static void main (String[] args) {
		//
		//
		String user = getUN(),
				pw = getPW(),
				mto = getTo(),
				msub = getSubject(),
				mtext = getContents(); //
		Date mdate = new Date();
		//
		String host = "smtp.gmail.com";
		String port = "587";
		//
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host);
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

			Message m = new MimeMessage(sesh);
			m.setFrom(new InternetAddress(user));
			m.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mto));
			m.setSubject(msub);
			m.setText(mtext);

			Transport.send(m);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} // end try/catch //
		
		
		
	} // end main //
	
	private static String getUN() {
		
		
		
		
		return null;
	} // end getUN() //
	
	private static String getPW() {
		
		
		
		
		return null;
	} // end getPW() //
	
	private static String getTo() {
		
		
		
		
		return null;
	} // end getTo() //
	
	private static String getSubject() {
		
		
		
		
		return null;
	} // end get Subject() //

	private static String getContents() {
		
		
		
		
		return null;
	} // end getContents() //

	
	

}





