package bean2;

import model.dao.Invitation;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Srđan
 */
@Local
public interface InvitationLocal {

    void create(Invitation invitation);

    void edit(Invitation invitation);

    void remove(Invitation invitation);

    Invitation find(Integer id);

    List<Invitation> findAll();

    List<Invitation> findRange(int[] range);

    int count();
    
}
