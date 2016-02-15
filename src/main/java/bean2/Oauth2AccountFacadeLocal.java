package bean2;

import java.util.List;
import javax.ejb.Local;
import model.Oauth2Account;

/**
 *
 * @author Srđan
 */
@Local
public interface Oauth2AccountFacadeLocal {

    void create(Oauth2Account oauth2Account);

    void edit(Oauth2Account oauth2Account);

    void remove(Oauth2Account oauth2Account);

    Oauth2Account find(Object id);

    List<Oauth2Account> findAll();

    List<Oauth2Account> findRange(int[] range);

    int count();
    
}
