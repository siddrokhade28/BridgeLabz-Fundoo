package com.BridgeLabz.FundooApp.Utility;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailSenderImpl implements IMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String from, String to) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject("mail from admin");
            messageHelper.setText(messageBuilder(to), true);
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private String messageBuilder(String email) {
        return "<html><head>Hi User,</head><body> Password Request Successfull for Email " + email + " </body></html>";
    }


    public void confirmMail(String from, String to, String token) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject("mail from admin");
            messageHelper.setText("<html><body>" +
                    "<link>" + " http://localhost:8080/user/confirm-email?token=" + token + "</link></body></html>", true);
            javaMailSender.send(message);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @SneakyThrows
    @Override
    public void forgotPasswordMail(String from, String to, String password) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject("mail from admin");
        messageHelper.setText("<html><head>Hi User,</head><body> Your password for the " + to + " is "
                + password + "</body></html>", true);
        javaMailSender.send(message);

    }
}
