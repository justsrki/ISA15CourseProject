package beans.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import beans.dao.interfaces.UserLocal;
import model.dao.User;

/**
 *
 * @author Srđan
 */
@Stateless
public class UserBean extends AbstractBean<User> implements UserLocal {

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
