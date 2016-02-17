package beans.dao.interfaces;

import java.util.List;
import javax.ejb.Local;
import model.dao.SessionToken;
import model.dao.User;

/**
 *
 * @author Srđan
 */
@Local
public interface SessionTokenLocal {

    void create(SessionToken sessionToken);

    SessionToken create(User user, String type);

    void edit(SessionToken sessionToken);

    void remove(SessionToken sessionToken);

    SessionToken find(Object id);

    SessionToken findByValue(String value);

    List<SessionToken> findAll();

    List<SessionToken> findRange(int[] range);

    int count();
    
}
