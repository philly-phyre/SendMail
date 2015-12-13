package org.tempestdesign.sendmail;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;

public class NoReply {
	
	
	// private static String host[] = {"smtp.gmail.com"};
	private static String host;
	private static String port;
	private static String mto, msub, UN, PW, cTEXT, cTYPE;
	private static boolean done = false;
	
	private static Scanner sc = new Scanner(System.in);
	// 
	//
	
	/* 
	 * 
	 * 
	 * vhldkjflhk
	 * 
	 * */
	public static void main (String[] args) {
		//
		//
		setHost();
		if(host.equals("localhost")){
			setUN();
		} else {
			setUN();
			setPW();
		}
				
		Date mdate = new Date();
		//
		
		//
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		if(host.equals("smtp.gmail.com") || host.equals("smtp.mail.yahoo.com")){
			prop.put("mail.smtp.starttls.enable", "true");
		}
		prop.put("mail.smtp.host", host/*[0]*/);
		prop.put("mail.smtp.port", port);
		if(host.equals("smtp.mail.yahoo.com")) { prop.put("mail.smtp.ssl.enable", "true"); }
		//
		Session sesh;
		if(host.equals("smtp.gmail.com") || host.equals("smtp.mail.yahoo.com")){
			sesh = Session.getInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(UN, PW);
					}
				});//
		} else {
			sesh = Session.getDefaultInstance(prop);
		}
		chk();
		//
		//
		do {
			try {
				
				setTo();
				setSubject();
				setContent();
				
				Message m = new MimeMessage(sesh);
				m.setFrom(new InternetAddress(UN));
				m.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mto)); //////////////////////////////////////////
				m.setSubject(msub);
				m.setSentDate(mdate);
				m.setContent(cTEXT, cTYPE);
				
				Transport t;
				if(host.equals("smtp.mail.yahoo.com"))
					t = sesh.getTransport("smtps");
				else 
					t = sesh.getTransport("smtp");
				 
				System.out.println(">> ? " + t.getURLName() + " \n>> ?");
				
				Transport.send(m);
	
				
				
	
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			} // end try/catch //
			
			sleep(500);
			chk();
			System.out.println(">> Your message has been sent successfully!!");
			sleep(250);
			System.out.println(">> ?");
			sleep(50);
			System.out.println(">> Be sure to check your sent folder, along with your inbox for");
			sleep(50);
			System.out.println(">> information regarding unsent messages or errors in sending.");
			sleep(200);
			System.out.println(">> ?");
			sleep(50);
			System.out.println(">> Would you like to send another message?");
			sleep(50);
			System.out.println(">> ## [Y/YES/y/yes/1] ##");
			sleep(50);
			System.out.println(">> ## [N/NO/n/no/0] ##");
			sleep(50);
			System.out.print(">> ? ");
			String ck = sc.nextLine().toUpperCase();
			if (ck.equals("Y") || ck.equals("YES") || ck.equals("1"))
				;
			else if(ck.equals("N") || ck.equals("NO") || ck.equals("0"))
				done = true;
			else {
				System.out.println(">> Invalid argument. Exiting [1]");
				done = true;
			}
		} while (!done); // end of program //
		
		sc.close();
		endSesh();
		
	} // end main //
	
	public static void setHost() {
		System.out.println(">> Would you like to send mail through GMAILSERVERS or through YAHOOSERVERS?");
		sleep(50);
		System.out.println(">> ## [GMAILSERVERS = gmail/GMAIL/google/GOOGLE/g/G/1] ##");
		sleep(50);
		System.out.println(">> ## [YAHOOSERVERS = yahoo/YAHOO/y/Y/2] ##");
		sleep(50);
		System.out.println(">> ?");
		sleep(50);
		System.out.print(">> ? ");
		host = sc.nextLine().toUpperCase();
		switch(host) {
		case "L":
		case "LOCAL":
		case "88":
			host = "localhost";
			port = "80";
			break;
		case "GOOGLE":
		case "G":
		case "GMAIL":
		case "1":
			host = "smtp.gmail.com";
			port = "587";
			break;
		case "YAHOO":
		case "Y":
		case "2":
			host = "smtp.mail.yahoo.com";
			port = "465";
			break;
		}
	}
	
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
		System.out.println(">> Please enter your EMAIL username/address.");
		sleep(50);
		// System.out.println(">> For LOCALSERVERS, this is simply your EMAIL address.");
		sleep(50);
		System.out.println(">> For GMAILSERVERS, this can be either your full address, or \n>> just your USER (address without \"@gmail.com\").");
		sleep(50);
		System.out.println(">> For YAHOOSERVERS, this can be either your full address, or \n>> just your USER (address without \"@yahoo.com\"). \n>> ?");
		System.out.print(">> ? ");
				/*  **************************************  */
		UN = sc.nextLine(); //
		if(host.equals("smtp.gmail.com")){
			sufd = UN.endsWith("@gmail.com");
			if(!sufd) {
					UN += suf;
			} else if(UN.length() < 11) {
				UN += suf;
			}
		} else if(host.equals("smtp.mail.yahoo.com")){
			sufd = UN.endsWith("@yahoo.com");
			if(!sufd) {
				UN += suf;
			} else if(UN.length() < 11) {
			UN += suf;
			}
		}
		System.out.println(">> ? \n>> ? \n>> ??USERNAME=" + UN + "\n>> ?");
		sleep(650);
	} // end getUN() //
	
	/** NoReply.#setPW()#
	 *  
	 * >> Prompts user for GMAIL account password.
	 * 
	 * @return Returns valid GMAIL password as type String.
	 */
	
	private static void setPW() {
		
		sleep(110);
		System.out.println(">> Please enter your GMAIL account password. \n>> ?");
		System.out.print(">> ?");
				/* **************************************** */
		PW = sc.nextLine();
	} // end getPW() //
	
	private static void setTo() {
		
		
		System.out.println(">> ?");
		sleep(50);
		System.out.println(">> Please enter the EMAIL address(es) you would like to send your message to.");
		sleep(50);
		System.out.println(">> For multiple addresses, separate with a comma and no space.");
		sleep(50);
		System.out.println(">> (ex. \"me@here.com,you@there.com,them@where.com\")");
		sleep(50);
		System.out.println(">> ?");
		sleep(50);
		System.out.print(">> ? ");
		try {
			mto = sc.nextLine();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} //
		chk();
	} // end getTo() //
	
	private static void setSubject() {
		sleep(150);
		System.out.println(">> Please enter the subject for your message.");
		System.out.println(">> ?");
		System.out.print(">> ? ");
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
		boolean brk = true;
		String chk = null;
		int x = 1;
		System.out.println(">> This software mostly deals with text-based messages.");
		sleep(50);
		System.out.println(">> Is this a PLAINTEXT message, or an HTMLTEXT message?");
		sleep(50);
		System.out.println(">> \t ## [PLAINTEXT = 1/plain/PLAIN/text/TEXT] ##");
		sleep(50);
		System.out.println(">> \t ## [HTMLTEXT = 2/html/HTML/code/or/CODE] ##");
		sleep(50);
		System.out.println(">> ?");
		sleep(50);
		System.out.print(">> ? ");
		chk = sc.nextLine().toUpperCase();
			switch(chk) {
			case "1":
			case "PLAIN":
			case "TEXT":
				cTYPE = "text/plain";
				break;
			case "2":
			case "HTML":
			case "CODE":
				cTYPE="text/html";
				break;
			default:
				System.out.println(">> That was not a valid option; setting to PLAINTEXT by default!!");
				sleep(50);
				System.out.println(">> ?");
				cTYPE = "text/plain";
			}
		chk();
		System.out.println(">> ?");
		chk = null;
		sleep(150);
		System.out.println(">> Perfect. Now begin composing your message.");
		sleep(50);
		System.out.println(">> You may press RETURN at the end of each sentence or stopping point.");
		sleep(50);
		System.out.println(">> RETURN a blank line to end the process and send the email.");
		cTEXT = "";
		while(brk) {
			System.out.print(">> ? ");
			chk = sc.nextLine();
			cTEXT += chk + " ";
			if (chk.equals(""))
				brk = false;
			else if (x % 3 == 0)
				cTEXT += " \n";
			x++;
			}
		// cTEXT = cTEXT.substring(3);
		chk();
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
	
	public static void chk() {
		System.out.println(">> ?");
		sleep(50);
		System.out.println(">> Check...");
		sleep(50);
		System.out.println(">> ?.....");
		sleep(750);
		System.out.println(">> Success!");
		sleep(50);
		System.out.println(">> ?!!!!!");
		sleep(50);
	}
	
	public static void endSesh() {
		System.out.println(">> ?");
		sleep(111);
		System.out.println(">> ? #");
		sleep(111);
		System.out.println(">> ? ####");
		sleep(111);
		System.out.println(">> ? ########");
		sleep(111);
		System.out.println(">> ? #############");
		sleep(111);
		System.out.println(">> ? ~~# THANK YOU FOR CHOOSING TEMPEST SOFTWARE #~~");
		sleep(111);
		System.out.println(">> ? #############");
		sleep(111);
		System.out.println(">> ? ########");
		sleep(111);
		System.out.println(">> ? ####");
		sleep(111);
		System.out.println(">> ? #");
		sleep(111);
		System.out.println(">> ?");
		sleep(333);
		System.out.println("Exiting [0]");
		sleep(55);
		System.exit(0);
	}
	
} // end NoReply class //















