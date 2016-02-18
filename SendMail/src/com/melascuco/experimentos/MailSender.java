package com.melascuco.experimentos;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;


public class MailSender {

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    //private static final int SMTP_HOST_PORT = 465;
    private static final int SMTP_HOST_PORT = 465;
    private static final String SMTP_AUTH_USER = "jaimmehenson@gmail.com";
    private static final String SMTP_AUTH_PWD  = "gm5#Puppet";

    public static void Send(String toParam, String msgParam) throws Exception{
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_HOST_NAME);
        props.put("mail.smtps.auth", "true");
        // props.put("mail.smtps.quitwait", "false");

        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject("Mest ALARM");
        message.setContent(msgParam,"text/plain");

        //message.setFrom(new InternetAddress("chiron-admin@atos.net"));
        message.addFrom(new InternetAddress[] { new InternetAddress("jaimmehenson@gmail.com")});
		message.addRecipient(Message.RecipientType.TO,
             new InternetAddress(toParam));
        message.setSender(new InternetAddress("jaimmehenson@gmail.com"));
        //message.setReplyTo((new InternetAddress("chiron-admin@atos.net")));

        transport.connect
          (SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

        transport.sendMessage(message,
            message.getAllRecipients());
        transport.close();
    }
}