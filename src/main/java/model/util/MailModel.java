package model.util;

import model.oauth.UserInfo;
import rest.user.SignUpRequest;

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

    private static final String OAuth2_MAIL_CONTENT = "Hi %s,<br /></br>" +
            "welcome to Restaurant Management System. Your password is: %s.";

    public static MailModel createOAuth2RegistrationMail(UserInfo userInfo, String password) {
        String subject = "Restaurant Management System - Welcome!";
        String content = String.format(OAuth2_MAIL_CONTENT, userInfo.getFirstName(), password);
        return new MailModel(userInfo.getEmail(), subject, content);
    }

    private static final String ACTIVATION_MAIL_CONTENT = "Hi %1$s,<br /></br>" +
            "welcome to Restaurant Management System. To active account click on this link <a href=\"%2$s\">%2$s</a>.";

    public static MailModel createActivationMail(SignUpRequest userInfo, String activationLink) {
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
}
