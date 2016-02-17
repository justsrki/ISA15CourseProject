package beans.dao.local;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import beans.dao.AbstractBean;
import beans.dao.interfaces.TokenLocal;
import beans.util.TokenGeneratorLocal;
import model.dao.Token;
import model.dao.User;

/**
 *
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class TokenBean extends AbstractBean<Token> implements TokenLocal {

    @EJB
    private TokenGeneratorLocal sessionTokenGenerator;

    public TokenBean() {
        super(Token.class);
    }

    @Override
    public Token create(User user, String type) {
        Token sessionToken = new Token(null, sessionTokenGenerator.generateSessionToken(), type);
        sessionToken.setUserId(user);
        create(sessionToken);
        return sessionToken;
    }

    @Override
    public Token findByValue(String value) {
        TypedQuery<Token> query = getEntityManager().createNamedQuery("Token.findByValue", Token.class);
        try {
            return query.setParameter("value", value).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
