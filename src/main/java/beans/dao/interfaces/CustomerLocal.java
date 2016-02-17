package beans.dao.interfaces;

import java.util.List;
import javax.ejb.Local;
import model.dao.Customer;
import model.oauth.UserInfo;
import rest.user.AuthenticationRequest;
import rest.user.SignUpRequest;

/**
 *
 * @author Srđan
 */
@Local
public interface CustomerLocal {

    void create(Customer customer);

    Customer create(SignUpRequest request);

    void edit(Customer customer);

    void remove(Customer customer);

    Customer find(Object id);

    List<Customer> findAll();

    List<Customer> findRange(int[] range);

    int count();
    
}
