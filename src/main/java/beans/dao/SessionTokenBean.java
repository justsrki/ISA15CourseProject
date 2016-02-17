package beans.dao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import beans.dao.interfaces.SessionTokenLocal;
import beans.util.SessionTokenGeneratorLocal;
import model.dao.SessionToken;
import model.dao.User;

/**
 *
 * @author Srđan
 */
@Stateless
public class SessionTokenBean extends AbstractBean<SessionToken> implements SessionTokenLocal {

    @EJB
    private SessionTokenGeneratorLocal sessionTokenGenerator;

    public SessionTokenBean() {
        super(SessionToken.class);
    }

    @Override
    public SessionToken create(User user, String type) {
        SessionToken sessionToken = new SessionToken(null, sessionTokenGenerator.generateSessionToken(), type);
        sessionToken.setUserId(user);
        create(sessionToken);
        return sessionToken;
    }

    @Override
    public SessionToken findByValue(String value) {
        TypedQuery<SessionToken> query = getEntityManager().createNamedQuery("SessionToken.findByValue", SessionToken.class);
        try {
            return query.setParameter("value", value).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
