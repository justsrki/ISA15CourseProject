package beans;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import beans.interfaces.UserFacadeLocal;
import model.User;

/**
 *
 * @author Srđan
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User find(String email) {
        TypedQuery<User> query = getEntityManager().createNamedQuery("User.findByEmail", User.class);
        try {
            return query.setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
