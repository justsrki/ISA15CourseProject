package beans.dao.beans;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import beans.dao.interfaces.InvitationLocal;
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
