package bean2;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import model.dao.Invitation;

/**
 *
 * @author Srđan
 */
@Stateless
public class InvitationBean extends AbstractBean<Invitation> implements InvitationFacadeLocal {



    public InvitationBean() {
        super(Invitation.class);
    }
    
}
