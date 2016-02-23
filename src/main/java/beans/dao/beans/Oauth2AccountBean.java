package beans.dao.beans;

import beans.dao.AbstractBean;
import beans.dao.interfaces.Oauth2AccountLocal;
import beans.dao.interfaces.TokenLocal;
import beans.dao.interfaces.UserLocal;
import beans.util.MailUtilLocal;
import beans.util.PasswordGeneratorLocal;
import beans.util.TokenGeneratorLocal;
import model.dao.Oauth2Account;
import model.dao.Token;
import model.dao.User;
import model.oauth.UserInfo;
import model.util.MailModel;
import model.dto.user.CreateCustomerRequest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class Oauth2AccountBean extends AbstractBean<Oauth2Account> implements Oauth2AccountLocal {

    @EJB
    private UserLocal userBean;
    @EJB
    private TokenLocal tokenBean;
    @EJB
    private PasswordGeneratorLocal passwordGeneratorBean;
    @EJB
    private TokenGeneratorLocal sessionTokenGeneratorBean;
    @EJB
    private MailUtilLocal mailUtilBean;

    public Oauth2AccountBean() {
        super(Oauth2Account.class);
    }

    @Override
    public Token createWithUserInfo(UserInfo userInfo, String provider) {
        User user = userBean.create(new CreateCustomerRequest(userInfo.getEmail(), userInfo.getFirstName(),
                userInfo.getLastName(), passwordGeneratorBean.generatePassword()));

        Oauth2Account oauth2Account = new Oauth2Account(null, provider, userInfo.getId());
        Token token = new Token(null, sessionTokenGeneratorBean.generateSessionToken(), Token.ACCESS_TOKEN);

        token.setUserId(user);
        oauth2Account.setUserId(user);

        userBean.create(user);
        this.create(oauth2Account);
        tokenBean.create(token);

        MailModel mail = MailModel.createRegistrationMail(userInfo.getEmail(), user.getFirstName(), user.getPassword());
        mailUtilBean.sendEmail(mail);

        return token;
    }

    @Override
    public Oauth2Account findByUserId(String userId) {
        TypedQuery<Oauth2Account> query = getEntityManager().createNamedQuery("Oauth2Account.findByClientId", Oauth2Account.class);
        try {
            return query.setParameter("clientId", userId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
