package beans.data_access.interfaces;

import java.util.List;
import javax.ejb.Local;
import model.Customer;

/**
 *
 * @author Srđan
 */
@Local
public interface CustomerLocal {

    void create(Customer customer);

    void edit(Customer customer);

    void remove(Customer customer);

    Customer find(Object id);

    List<Customer> findAll();

    List<Customer> findRange(int[] range);

    int count();
    
}
