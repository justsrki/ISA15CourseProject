package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Token;
import model.dao.User;

import javax.ejb.Local;

/**
 * @author Srđan
 */
@Local
public interface TokenLocal extends AbstractLocal<Token> {

    Token create(User user, String type);

    Token findByValue(String value);

}
