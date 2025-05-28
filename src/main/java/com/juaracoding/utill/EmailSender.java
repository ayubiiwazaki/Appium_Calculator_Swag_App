package com.juaracoding.utill;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailSender {
    final static String username = "647fbfc8cc7f17";
    final static String password = "0042468989efb5";

//    final static String username = "0932dfbae2ebf0";
//    final static String password = "a58744619fdcda";

    public static void send() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply@myapptestuhuy.test"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dev@mail.com"));
            message.setSubject("Ini adalah email testing");

            // masukin body
            message.setText("satu kali");

            Transport.send(message);

            System.out.println("Email berhasil dikirim");
        } catch (Exception e) {
            System.out.println("Email gagal dikirim: " + e.getMessage());
        }
    }
}
