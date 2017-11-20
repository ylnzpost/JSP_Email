package com.web.email;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class EmailBean implements Serializable {
	// Init default values
	public static final String DEFAULT_CONTENT = "EMPATY EMAIL CONTENT";
	public static final String DEFAULT_SUBJECT = "EMPTY EMAIL SUBJECT";
	public static final String DEFAULT_HOST_SERVER = "mail.datamail.co.nz";
	public static final String DEFAULT_FROM = "yun.li@nzpost.co.nz";
	public static final String DEFAULT_TO = "yun.li@nzpost.co.nz";
	/*
	static {
		java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ccom.web.email.mailDefaults");
		DEFAULT_SERVER = bundle.getString("DEFAULT_HOST_SERVER");
		DEFAULT_FROM = bundle.getString("DEFAULT_FROM");
		DEFAULT_TO = bundle.getString("DEFAULT_TO");
	}
	*/

	private static final long serialVersionUID = 1L;
	// Email objects
	private static String SMTP_HOST = "mail.datamail.co.nz";
	private static String SMTP_PORT = "";
	private String smtpHost = null;
	private String from = null;
	private String to = null;
	private String content = null;
	private String subject = null;
	
	public EmailBean()
	{
	}
	
	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		if (check(smtpHost))
			this.smtpHost = smtpHost;
		else
			this.smtpHost = DEFAULT_HOST_SERVER;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		if (check(to))
			this.to = to;
		else
			this.to= DEFAULT_TO;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	private boolean check(String host) {
		if (host == null || host.equalsIgnoreCase(""))
		{
			return false;
		}
		return true;
	}
	
	public boolean sendEmail() throws Exception
	{
		boolean result = true;
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", SMTP_HOST);
		Session session = Session.getDefaultInstance(properties);
		Message mailMessage = new MimeMessage(session);
		InternetAddress[] addresses = null;
		try
		{
			
		}
		catch (Exception ex)
		{
			result = false;
			throw ex;
		}
		
		if (this.to != null)
		{
			addresses = InternetAddress.parse(to, false);
			mailMessage.setRecipients(Message.RecipientType.TO, addresses);
		}
		else
		{
			throw new MessagingException("The mail message requires [To] address");
		}
		
		if (this.from != null)
		{
			mailMessage.setFrom(new InternetAddress(this.from));
		}
		else
		{
			throw new MessagingException("The mail message requires valid [From] address");
		}
		
		if (this.subject != null)
		{
			mailMessage.setSubject(this.subject);
		}
		
		if (this.content != null)
		{
			mailMessage.setText(this.content);
		}
		
		Transport.send(mailMessage);
		return result;
	}
}
