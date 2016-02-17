package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.User;
import rest.user.CreateCustomerRequest;
import rest.user.CreateManagerRequest;

import javax.ejb.Local;

/**
 * @author Srđan
 */
@Local
public interface UserLocal extends AbstractLocal<User> {

    User create(CreateCustomerRequest request);

    User create(CreateManagerRequest request);

    User findByEmail(String email);

}