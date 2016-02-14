package bean;

import java.util.List;
import javax.ejb.Local;
import model.Invitation;

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
