package beans.util;

import model.util.MailModel;

import javax.ejb.Local;

/**
 * @author - Srđan Milaković
 */
@Local
public interface MailUtilLocal {
    void sendEmail(MailModel mailModel);
}
