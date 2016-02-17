package bean2;

import java.util.List;
import javax.ejb.Local;
import model.dao.Invitation;

/**
 *
 * @author Srđan
 */
@Local
public interface InvitationFacadeLocal {

    void create(Invitation invitation);

    void edit(Invitation invitation);

    void remove(Invitation invitation);

    Invitation find(Object id);

    List<Invitation> findAll();

    List<Invitation> findRange(int[] range);

    int count();
    
}
