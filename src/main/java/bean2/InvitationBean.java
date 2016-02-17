package bean2;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import model.dao.Invitation;

/**
 *
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class InvitationBean extends AbstractBean<Invitation> implements InvitationLocal {

    public InvitationBean() {
        super(Invitation.class);
    }
    
}
