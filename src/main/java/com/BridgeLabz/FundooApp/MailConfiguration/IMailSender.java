package com.BridgeLabz.FundooApp.MailConfiguration;

/*
mail sender config Interface
 */
public interface IMailSender {

    public void sendEmail(String from, String to);

    public void confirmMail(String from, String to, String token);

    public void forgotPasswordMail(String from, String to, String Password);

}
