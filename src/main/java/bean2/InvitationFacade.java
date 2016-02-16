package bean2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.data_access.AbstractFacade;
import model.Invitation;

/**
 *
 * @author Srđan
 */
@Stateless
public class InvitationFacade extends AbstractFacade<Invitation> implements InvitationFacadeLocal {



    public InvitationFacade() {
        super(Invitation.class);
    }
    
}
