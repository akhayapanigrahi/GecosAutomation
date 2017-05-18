package com.itc.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

public class SendingEmail {
	public static Logger logger = Logger.getLogger(SendingEmail.class);

	public static void main(String[] args) throws Exception {
		SendingEmail email=new SendingEmail();
		String username="apanigrahi";
		String password="Login@29";
		String subject="Sending email";
		String body="Hello";
		List<String> toAddressList=new ArrayList<String>();
		toAddressList.add("apanigrahi@blackberry.com");
		email.sendMailViaExchangeService(username, password, subject, body, toAddressList);
		 	
	}
	public void sendMailViaExchangeService(String username, String password, String subject, String body,List<String> toAddressList) throws Exception {

		logger.info(" Sending mail ...");
		ExchangeService service;

		service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ExchangeCredentials credentials = new WebCredentials(username, password);
		service.setCredentials(credentials);
		try {
			//service.setUrl(new URI("https://outlook.office365.com/EWS/Exchange.asmx"));
			service.setUrl(new URI("https://cas-hq.rim.net/owa/"));
			//service.setUrl(new URI("https://outlook.office365.com"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		try {

			EmailMessage msg= new EmailMessage(service);
			msg.setSubject(subject);
			msg.setBody(MessageBody.getMessageBodyFromText(body));
			Iterator<String> mailList = toAddressList.iterator();
			msg.getToRecipients().addSmtpAddressRange(mailList);
			msg.send();
			logger.info("Mail sending Success...Please check your inbox");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
