package beans.util;

import model.util.MailModel;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 * @author - Srđan Milaković
 */
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/ExpiryQueue")
        }
)
public class MailMDBean implements MessageListener {

    private static final String FROM_ADDRESS = "restaurant.management.isa@gmail.com";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String SMTP = "smtp";

    @Resource(mappedName = "java:jboss/gmail")
    private Session session;

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                handleObjectMessage((ObjectMessage) message);
            } catch (JMSException | MessagingException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(this.getClass().getName() + " does not support message type " + message.getClass().getName());
        }
    }


    private void handleObjectMessage(ObjectMessage objectMessage) throws JMSException, MessagingException {
        Object object = objectMessage.getObject();
        if (object instanceof MailModel) {
            MailModel mailModel = (MailModel) object;

            // TODO: remove
            if (mailModel.getToAddress().endsWith(".isa")) {
                return;
            }

            // Create mime message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(FROM_ADDRESS);
            message.addRecipients(MimeMessage.RecipientType.TO, mailModel.getToAddress());
            message.setSubject(mailModel.getSubject());
            message.setContent(mailModel.getContent(), CONTENT_TYPE);

            // Create transport and send mail
            Transport transport = session.getTransport(SMTP);
            transport.connect();
            message.saveChanges();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } else {
            System.err.println(this.getClass().getName() + " does not support object type " + object.getClass().getName());
        }
    }
}
