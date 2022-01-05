package com.attraction.reset_password;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.validation.constraints.Email;

public class MailSender {

  private MailSender() {}

  static public void sendMail(@Email String address, Integer hash) throws AddressException, MessagingException, IOException {
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls", "true");
    properties.put("mail.smtp.host", "smtp.google.com");
    properties.put("mail.smtp.port", "587");

    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
     @Override
     protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
       return new javax.mail.PasswordAuthentication("university.sightseeing.java@gmail.com", "6d3aAKQc9wsu19ZhXYw0");
      }
    });
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress("university.sightseeing.java@gmail.com", false));

    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
    message.setSubject("Sightseeing: Password reset");
    message.setSentDate(new Date());

    Multipart multipart = new MimeMultipart();

    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent("To reset password click link below", "text/html");
    multipart.addBodyPart(messageBodyPart);
    String link = "localhost:8080/password/reset/" + hash.toString();
    messageBodyPart.setContent(link, "text/html");
    multipart.addBodyPart(messageBodyPart);
    message.setContent(multipart);

    Transport.send(message);
  }

}
