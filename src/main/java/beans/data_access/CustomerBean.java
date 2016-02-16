package beans.data_access;

import javax.ejb.Stateless;

import beans.data_access.interfaces.CustomerLocal;
import model.Customer;

/**
 *
 * @author Srđan
 */
@Stateless
public class CustomerBean extends AbstractFacade<Customer> implements CustomerLocal {

    public CustomerBean() {
        super(Customer.class);
    }
    
}
