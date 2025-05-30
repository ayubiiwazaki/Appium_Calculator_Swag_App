package com.juaracoding.utill;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailSender {
    final static String username = "0932dfbae2ebf0";
    final static String password = "a58744619fdcda";

    public static void send() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            // ngebaca isi report
            String isiHTMLString = new String(Files.readAllBytes(Paths.get("target/surefire-reports/emailable-report.html")));

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply@myapptestuhuy.test"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dev@mail.com"));
            message.setSubject("Ini adalah email testing");

            // masukin body
            // message.setText("Contoh ajah");
            message.setContent(isiHTMLString, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Email berhasil dikirim");
        } catch (Exception e) {
            System.out.println("Email gagal dikirim: " + e.getMessage());
        }
    }
}
