package bean;

import java.util.List;
import javax.ejb.Local;
import model.SessionToken;

/**
 *
 * @author Srđan
 */
@Local
public interface SessionTokenFacadeLocal {

    void create(SessionToken sessionToken);

    void edit(SessionToken sessionToken);

    void remove(SessionToken sessionToken);

    SessionToken find(Object id);

    List<SessionToken> findAll();

    List<SessionToken> findRange(int[] range);

    int count();
    
}
