package beans.dao;

import beans.dao.interfaces.CustomerLocal;
import beans.dao.interfaces.SessionTokenLocal;
import beans.dao.interfaces.UserLocal;
import beans.util.MailUtilLocal;
import beans.util.SessionTokenGeneratorLocal;
import model.dao.Customer;
import model.dao.SessionToken;
import model.dao.User;
import model.util.MailModel;
import rest.user.SignUpRequest;
import util.ApplicationConfig;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Srđan
 */
@Stateless
public class CustomerBean extends AbstractBean<Customer> implements CustomerLocal {

    @EJB
    private UserLocal userBean;
    @EJB
    private CustomerLocal customerBean;
    @EJB
    private SessionTokenLocal sessionTokenBean;
    @EJB
    private MailUtilLocal mailUtilBean;
    @EJB
    private SessionTokenGeneratorLocal sessionTokenGeneratorBean;

    public CustomerBean() {
        super(Customer.class);
    }

    @Override
    public Customer create(SignUpRequest request) {
        User user = new User(null, request.getEmail(), request.getPassword(), User.CUSTOMER, false);
        Customer customer = new Customer(null, request.getFirstName(), request.getLastName());
        customer.setUserId(user);

        String token = sessionTokenGeneratorBean.generateSessionToken();
        SessionToken sessionToken = new SessionToken(null, token, SessionToken.CONFIRM_REGISTRATION);
        sessionToken.setUserId(user);

        userBean.create(user);
        customerBean.create(customer);
        sessionTokenBean.create(sessionToken);

        String activationLink = ApplicationConfig.baseUrl + "/api/activate?token=" + token;
        mailUtilBean.sendEmail(MailModel.createActivationMail(request, activationLink));

        return null;
    }
}
