package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.User;
import rest.model.user.CreateCustomerRequest;
import rest.model.user.CreateManagerRequest;

import javax.ejb.Local;
import javax.jws.soap.SOAPBinding;

/**
 * @author Srđan
 */
@Local
public interface UserLocal extends AbstractLocal<User> {

    User create(CreateCustomerRequest request);

    User create(CreateManagerRequest request);

    User findByEmail(String email);

    void follow(User following, User followed);

    void unfollow(User following, User followed);
}