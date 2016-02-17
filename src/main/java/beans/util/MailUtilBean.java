package beans.util;

import model.util.MailModel;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

/**
 * @author - Srđan Milaković
 */
@Stateless
public class MailUtilBean implements MailUtilLocal {


    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "java:/jms/queue/ExpiryQueue")
    private Queue paymentQueue;


    @Override
    public void sendEmail(MailModel mailModel) {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(paymentQueue);

            ObjectMessage objectMessage = session.createObjectMessage();
            objectMessage.setObject(mailModel);
            producer.send(objectMessage);

            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
