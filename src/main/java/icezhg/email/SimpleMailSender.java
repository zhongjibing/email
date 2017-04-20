package icezhg.email;

import java.io.File;
import java.util.Date;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhongjibing on 2017/4/20.
 */
public class SimpleMailSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailSender.class);

    private MailAuth auth;

    public SimpleMailSender(MailAuth auth) {
        if (auth == null) {
            throw new IllegalArgumentException("non-null param auth is required.");
        }
        this.auth = auth;
    }

    public void sendText(MailDetail detail) {
        Session sendMailSession = Session.getDefaultInstance(auth.properties(), auth);
        try {
            Message mailMessage = new MimeMessage(sendMailSession);
            mailMessage.setFrom(new InternetAddress(auth.getUserName()));

            List<String> toAddress = detail.getToAddress();
            if (toAddress != null) {
                for (String address : toAddress) {
                    mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
                }
            }
            List<String> ccAddress = detail.getCcAddress();
            if (ccAddress != null) {
                for (String address : ccAddress) {
                    mailMessage.setRecipient(Message.RecipientType.CC, new InternetAddress(address));
                }
            }
            mailMessage.setSubject(detail.getSubject());
            mailMessage.setText(detail.getContent());
            mailMessage.setSentDate(new Date());
            Transport.send(mailMessage);
        } catch (MessagingException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void send(MailDetail detail) {
        Session sendMailSession = Session.getDefaultInstance(auth.properties(), auth);
        try {
            Message mailMessage = new MimeMessage(sendMailSession);
            mailMessage.setFrom(new InternetAddress(auth.getUserName()));

            List<String> toAddress = detail.getToAddress();
            if (toAddress != null) {
                for (String address : toAddress) {
                    mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
                }
            }
            List<String> ccAddress = detail.getCcAddress();
            if (ccAddress != null) {
                for (String address : ccAddress) {
                    mailMessage.setRecipient(Message.RecipientType.CC, new InternetAddress(address));
                }
            }
            mailMessage.setSubject(detail.getSubject());
            Multipart multipart = new MimeMultipart();
            BodyPart content = new MimeBodyPart();
            content.setContent(detail.getContent(), "text/html; charset=utf-8");
            multipart.addBodyPart(content);
            if (detail.getAttachments() != null) {
                for (String attachment : detail.getAttachments()) {
                    BodyPart attach = new MimeBodyPart();
                    attach.setDataHandler(new DataHandler(new FileDataSource(attachment)));
                    String[] path = attachment.split("/|\\\\");
                    attach.setFileName(path[path.length - 1]);
                    multipart.addBodyPart(attach);
                }
            }
            mailMessage.setContent(multipart);
            mailMessage.setSentDate(new Date());
            Transport.send(mailMessage);
        } catch (MessagingException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
