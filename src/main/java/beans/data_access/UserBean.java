package beans.data_access;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import beans.data_access.interfaces.UserLocal;
import model.User;

/**
 *
 * @author Srđan
 */
@Stateless
public class UserBean extends AbstractFacade<User> implements UserLocal {

    public UserBean() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = getEntityManager().createNamedQuery("User.findByEmail", User.class);
        try {
            return query.setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
