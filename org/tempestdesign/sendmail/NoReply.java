package org.tempestdesign.sendmail;

import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.*;

public class NoReply {

	
	
	public static void main (String[] args) {
		
		final String un = "your gmail";
		final String pw = "your gpass";
		String mto, msub, mtext;
		Date mdate = new Date();
		mto = getTo();
		msub = getSubject();
		mtext = getBody();
		
		
	
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
	
		Session sesh = Session.getInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(un, pw);
					}
				});
	 
		
		try {

			Message m = new MimeMessage(sesh);
			m.setFrom(new InternetAddress(un));
			m.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mto));
			m.setSubject(msub);
			m.setText(mtext);

			Transport.send(m);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		
		
	}
	
	private static String getSubject() {
		return null;
	}

	private static String getBody() {
		return null;
	}

	private static String getTo() {
		
		return null;
		
	}
	

}
