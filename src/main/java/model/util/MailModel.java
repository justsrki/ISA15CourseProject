package model.util;

import model.dto.user.CreateCustomerRequest;

import java.io.Serializable;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class MailModel implements Serializable {
    private String toAddress;
    private String subject;
    private String content;

    public MailModel(String toAddress, String subject, String content) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.content = content;
    }

    private static final String REGISTRATION_MAIL_CONTENT = "Hi %s,<br /></br>" +
            "welcome to Restaurant Management System. Your password is: %s.";

    public static MailModel createRegistrationMail(String email, String name, String password) {
        String subject = "Restaurant Management System - Welcome!";
        String content = String.format(REGISTRATION_MAIL_CONTENT, name, password);
        return new MailModel(email, subject, content);
    }

    private static final String ACTIVATION_MAIL_CONTENT = "Hi %1$s,<br /></br>" +
            "welcome to Restaurant Management System. To active account click on this link <a href=\"%2$s\">%2$s</a>.";

    public static MailModel createActivationMail(CreateCustomerRequest userInfo, String activationLink) {
        String subject = "Restaurant Management System - Welcome!";
        String content = String.format(ACTIVATION_MAIL_CONTENT, userInfo.getFirstName(), activationLink);
        return new MailModel(userInfo.getEmail(), subject, content);
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MailModel{" +
                "toAddress='" + toAddress + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
