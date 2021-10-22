package _00_init.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingEmail {
	// Sender's email ID needs to be mentioned
	private final static String from = "yuntingwang911";// change accordingly
	private final static String username = "yuntingwang911@gmail.com";// change accordingly
	private final static String password = "Ting850911@";// change accordingly

	// Assuming you are sending email through relay.jangosmtp.net
	private final static String host = "smtp.gmail.com";
	private final static int port = 587;

	public static void SendVerificationCodeTo(String email, String verificationCode) throws UnsupportedEncodingException {
		// Recipient's email ID needs to be mentioned.
		String to = email;// change accordingly

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// Get the Session object.
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(true);
		
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from, "whattodrink2021"));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("【今天喝什麼？】 發送驗證碼");

			// Now set the actual message
			message.setText("歡迎您使用「今天喝什麼」，\r\n"
					+ "\r\n"
					+ "您的Email驗證碼為："
					+ verificationCode
					+ "，\r\n"
					+ "\r\n"
					+ "請至系統填入驗證碼以執行操作。");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	};
	
	
	
	
	//business side - find company_account
	public static void SendCompanyAccountTo(String email, String company_account) throws UnsupportedEncodingException {
		// Recipient's email ID needs to be mentioned.
		String to = email;// change accordingly

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// Get the Session object.
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(true);
		
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from, "whattodrink2021"));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("【今天喝什麼？】 找回帳號");

			// Now set the actual message
			message.setText("歡迎您使用「今天喝什麼」，\r\n"
					+ "\r\n"
					+ "您的帳號為："
					+ company_account
					+ "，\r\n"
					+ "\r\n"
					+ "請至系統重新登入。");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	};
}