package beans.data_access;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import beans.data_access.interfaces.SessionTokenLocal;
import beans.util.SessionTokenGeneratorLocal;
import model.SessionToken;
import model.User;

/**
 *
 * @author Srđan
 */
@Stateless
public class SessionTokenBean extends AbstractFacade<model.SessionToken> implements SessionTokenLocal {

    @EJB
    private SessionTokenGeneratorLocal sessionTokenGenerator;

    public SessionTokenBean() {
        super(model.SessionToken.class);
    }

    @Override
    public SessionToken create(User user, String type) {
        SessionToken sessionToken = new SessionToken(null, sessionTokenGenerator.generateSessionToken(), type);
        sessionToken.setUserId(user);
        create(sessionToken);
        return sessionToken;
    }
}
