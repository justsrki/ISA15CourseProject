package beans.data_access;

import beans.data_access.interfaces.SessionTokenLocal;
import beans.data_access.interfaces.CustomerLocal;
import beans.data_access.interfaces.Oauth2AccountLocal;
import beans.data_access.interfaces.UserLocal;
import beans.oauth.model.UserInfo;
import beans.util.PasswordGeneratorLocal;
import beans.util.SessionTokenGeneratorLocal;
import model.Customer;
import model.Oauth2Account;
import model.SessionToken;
import model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Srđan
 */
@Stateless
public class Oauth2AccountBean extends AbstractFacade<Oauth2Account> implements Oauth2AccountLocal {

    @EJB
    private UserLocal userBean;
    @EJB
    private CustomerLocal customerBean;
    @EJB
    private SessionTokenLocal sessionTokenBean;
    @EJB
    private PasswordGeneratorLocal passwordGeneratorBean;
    @EJB
    private SessionTokenGeneratorLocal sessionTokenGeneratorBean;


    public Oauth2AccountBean() {
        super(Oauth2Account.class);
    }

    @Override
    public SessionToken createWithUserInfo(UserInfo userInfo, String provider) {
        User user = new User(null, userInfo.getEmail(), passwordGeneratorBean.generatePassword(), User.CUSTOMER, true);
        Customer customer = new Customer(null, userInfo.getFirstName(), userInfo.getLastName());
        Oauth2Account oauth2Account = new Oauth2Account(null, provider, userInfo.getId());
        SessionToken sessionToken = new SessionToken(null, sessionTokenGeneratorBean.generateSessionToken(), SessionToken.CUSTOMER);

        customer.setUserId(user);
        oauth2Account.setCustomerId(customer);
        sessionToken.setUserId(user);

        userBean.create(user);
        customerBean.create(customer);
        this.create(oauth2Account);
        sessionTokenBean.create(sessionToken);

        return sessionToken;
    }

    @Override
    public Oauth2Account findByUserId(String userId) {
        TypedQuery<Oauth2Account> query = getEntityManager().createNamedQuery("Oauth2Account.findByUserId", Oauth2Account.class);
        try {
            return query.setParameter("userId", userId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
