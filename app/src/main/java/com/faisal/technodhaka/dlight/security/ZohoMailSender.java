package com.faisal.technodhaka.dlight.security;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by Faisal Mohammad  on 10/16/2017.
 * this class is zoho mail class sender
 */
public class ZohoMailSender extends Authenticator {
    private static final String TAG = ZohoMailSender.class.getSimpleName();

    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_PORT = "mail.smtp.port";
    private static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
    private static final String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    private static final String MAIL_SMTP_SOCKET_FACTORY_FALLBACK = "mail.smtp.socketFactory.fallback";
    private static final String MAIL_SMTP_QUITWAIT = "mail.smtp.quitwait";
    private static final String MAIL_HOST = "mail.host";
    private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";

    //private String mailHost="smtp.gmail.com";
    private static final String mailHost = "smtp.zoho.com";
    private String user;
    private String password;
    private Session session;

    static {
        Security.addProvider(new JSSEProvider());
    }

    public ZohoMailSender(String user, String password) {
        this.user = user;
        this.password = password;

        Properties props = new Properties();
        props.setProperty(MAIL_TRANSPORT_PROTOCOL, "smtp");
        props.setProperty(MAIL_HOST, mailHost);
        props.put(MAIL_SMTP_AUTH, "true");
        props.put(MAIL_SMTP_PORT, "465");
        props.put(MAIL_SMTP_SOCKET_FACTORY_PORT, "465");
        props.put(MAIL_SMTP_SOCKET_FACTORY_CLASS, "javax.net.ssl.SSLSocketFactory");
        props.put(MAIL_SMTP_SOCKET_FACTORY_FALLBACK, "false");
        props.setProperty(MAIL_SMTP_QUITWAIT, "false");

        session = Session.getDefaultInstance(props, this);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

    public synchronized void sendMail(String subject, String body, String sender,
                                      String recipients) throws Exception {
        /**
         * get session from {@link session}
         *  Create a default MimeMessage object.
         */
        MimeMessage message = new MimeMessage(session);

        /** Set From: header field of the header.*/
        message.setSender(new InternetAddress(sender));

        /**  Set Subject: header field */
        message.setSubject(subject);

        /**  create message part */
        BodyPart messageBodyPart = new MimeBodyPart();

        /** Now set the actual message */
        messageBodyPart.setText("This is message body");

        /** Create a multipar message*/
        Multipart multipart = new MimeMultipart();

        /** Set text message part */
        multipart.addBodyPart(messageBodyPart);

        /** Part two is attachment */
        messageBodyPart = new MimeBodyPart();

        /**           Get Memory Directory         */
        String root = Environment.getExternalStorageDirectory().toString();
        String filename = root + "/MYGALLERY/" + Configuration.PHOTO_NAME + ".jpg";
        /**
         * check the file exits
         */
        File file = new File(filename);
        if (file.exists()) {
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            /** Send the complete message parts*/
            message.setContent(multipart);


            /**
             * Create a default MimeMessage object and set From, To, Subject in the message.
             * Set the actual message as below: by {@link DataHandler class}
             */
            //  DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
            // message.setDataHandler(handler);


            if (recipients.indexOf(',') > 0)
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            else
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));

            Transport.send(message);

            Log.d(TAG, "mail sent successfully !");
        } else {
            Log.d(TAG, "image file not found  !");
        }
    }
}
