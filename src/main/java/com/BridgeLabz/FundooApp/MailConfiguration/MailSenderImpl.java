package com.BridgeLabz.FundooApp.MailConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/*
Implementation of IMailSender to send mails
 */
@Service
public class MailSenderImpl implements IMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    /*
    method to send mail when rest api is called
     */
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

    /*
    a function to set content for the successful password reset
     */

    private String messageBuilder(String email) {
        return "<p> Password has been Successfully reset for Email " + email + " <p>";
    }
    /*
    a function to set content for the Confirmation mail
     */

    private String ConfirmMailMessageBuilder(String token) {
        String link = "http://localhost:8080/user/confirm-email?token=" + token;
        String content = "<p>Hello</p>" + "<p>please click on the link to confirm the mail id </p>" + "<p><a href=\"" + link + "\">Click here<a/></p>";
        return content;
    }

    /*
    A function to send Confirmation  mail
     */

    public void confirmMail(String from, String to, String token) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject("mail from admin");
            messageHelper.setText(ConfirmMailMessageBuilder(token), true);
            javaMailSender.send(message);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*
    function to send mail when user forgets the password;
     */

    @Override
    public void forgotPasswordMail(String from, String to, String token) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject("mail from admin for the request of Forgot password");
            messageHelper.setText(PasswordMessageBuilder(token), true);
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    /*
    a function to set content for the forgot password
     */

    private String PasswordMessageBuilder(String token) {
        String content = "<p>Hello</p>" + "<p>Please use the below generated Token to Reset your password </p>" + token + "<p></p>";
        return content;
    }

}
