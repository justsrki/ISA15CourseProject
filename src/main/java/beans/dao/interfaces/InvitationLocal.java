package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Invitation;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Srđan
 */
@Local
public interface InvitationLocal extends AbstractLocal<Invitation> {
    
}
