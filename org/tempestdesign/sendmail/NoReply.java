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
				
	
				Transport.send(m);
	
				
				
	
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			} // end try/catch //
			
			sleep(500);
			chk();
			System.out.println(">> Your message has been sent successfully!!");
			sleep(250);
			System.out.println(">> ?");
			System.out.println(">> Be sure to check your sent folder, along with your inbox for");
			System.out.println(">> information regarding unsent messages or errors in sending.");
			sleep(200);
			System.out.println(">> ?");
			System.out.println(">> Would you like to send another message?");
			System.out.println(">> # [Y/YES/y/yes/1] # [N/NO/n/no/0] #");
			System.out.print(">> ?");
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
		sufd = UN.endsWith("@gmail.com");
		if(!sufd) {
				UN += suf;
		} else if(UN.length() < 11) {
			UN += suf;
		}
		System.out.println(">> ? \n>> ? \n>> ?USERNAME=" + UN + "\n>> ?");
		sleep(650);
	} // end getUN() //
	
	/** NoReply.#setPW()#
	 *  
	 * >> Prompts user for GMAIL account password.
	 * 
	 * @return Returns valid GMAIL password as type String.
	 */
	
	private static void setPW() {
		
		//sleep(750);
		System.out.println(">> Please enter your GMAIL account password.");
				/* **************************************** */
		PW = sc.nextLine();
	} // end getPW() //
	
	private static void setTo() {
		
		//sleep(900);
		System.out.println(">> ?");
		//sleep(750);
		System.out.println(">> Please enter the EMAIL address(es) you would like to send your message to.");
		//sleep(750);
		System.out.println(">> For multiple addresses, separate with a comma and no space.");
		//sleep(750);
		System.out.println(">> (ex. \"me@here.com,you@there.com,them@where.com\")");
		System.out.println(">> ?");
		System.out.print(">> ?");
		try {
			mto = sc.nextLine();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} //
		chk();
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
		boolean brk = true;
		String chk = null;
		int x = 1;
		System.out.println(">> This software mostly deals with text-based messages.");
		//sleep(750);
		System.out.println(">> Is this a PLAINTEXT message, or an HTMLTEXT message?");
		//sleep(250);
		System.out.println(">> \t ## [PLAINTEXT = 1, plain, PLAIN, text, or TEXT] ##");
		//sleep(250);
		System.out.println(">> \t ## [HTMLTEXT = 2, html, HTML, code, or CODE");
		System.out.println(">> ?");
		System.out.print(">> ?");
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
				System.out.println(">> ?");
				cTYPE = "text/plain";
			}
		chk();
		System.out.println(">> ?");
		chk = null;
		//sleep(750);
		System.out.println(">> Perfect. Now begin composing your message.");
		// sleep(750);
		System.out.println(">> You may press RETURN at the end of each sentence or stopping point.");
		System.out.println(">> RETURN a blank line to end the process and send the email.");
		while(brk) {
			chk = sc.nextLine();
			cTEXT += chk + " ";
			if (chk.equals(""))
				brk = false;
			else if (x % 3 == 0)
				cTEXT += " \n";
			x++;
			}
		cTEXT = cTEXT.substring(4);
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
		System.out.println(">> Check...");
		System.out.println(">> ?.....");
		sleep(750);
		System.out.println(">> Success!");
		System.out.println(">> ?!!!!!");
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















