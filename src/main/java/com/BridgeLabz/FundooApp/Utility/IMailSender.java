package com.BridgeLabz.FundooApp.Utility;

public interface IMailSender {

    public void sendEmail(String from, String to);

    public void confirmMail(String from, String to, String token);

    public void forgotPasswordMail(String from, String to, String Password);

}
