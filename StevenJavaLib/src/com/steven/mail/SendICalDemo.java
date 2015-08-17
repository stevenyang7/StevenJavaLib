package com.steven.mail;

import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class SendICalDemo {

	public static void main(String[] args) {
		
		final String username = "XX@163.com";
		final String password = "XX";
		String from = "XX@163.com";
		String to = "XX@163.com";
		
		boolean isSSL = true;
		String host = "smtp.163.com";
		int port = 465;

		boolean isAuth = true;

		Properties props = new Properties();
		props.put("mail.smtp.ssl.enable", isSSL);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", isAuth);

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Outlook Meeting Request Using JavaMail");
			StringBuffer buffer = new StringBuffer();
			buffer.append("BEGIN:VCALENDAR\n"
					+ "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n"
					+ "VERSION:2.0\n"
					+ "METHOD:REQUEST\n"
					+ "BEGIN:VEVENT\n"
					+ "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:"+to+"\n"
					+ "ORGANIZER:MAILTO:"+to+"\n"
					+ "DTSTART:20150818T060000Z\n"
					+ "DTEND:20150819T070000Z\n"
					+ "LOCATION:Conference room\n"
					+ "UID:"+UUID.randomUUID().toString()+"\n"//如果id相同的话，outlook会认为是同一个会议请求，所以使用uuid。
					+ "CATEGORIES:SuccessCentral Reminder\n"
					+ "DESCRIPTION:This the description of the meeting.<br>asd;flkjasdpfi\n\n"
					+ "SUMMARY:Test meeting request\n" + "PRIORITY:5\n"
					+ "CLASS:PUBLIC\n" + "BEGIN:VALARM\n"
					+ "TRIGGER:-PT15M\n" + "ACTION:DISPLAY\n"
					+ "DESCRIPTION:Reminder\n" + "END:VALARM\n"
					+ "END:VEVENT\n" + "END:VCALENDAR");
			BodyPart messageBodyPart = new MimeBodyPart();
			// 测试下来如果不这么转换的话，会以纯文本的形式发送过去，
			//如果没有method=REQUEST;charset=\"UTF-8\"，outlook会议附件的形式存在，而不是直接打开就是一个会议请求
			messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(buffer.toString(), 
					"text/calendar;method=REQUEST;charset=\"UTF-8\"")));
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("发送完毕！");

	}

}
