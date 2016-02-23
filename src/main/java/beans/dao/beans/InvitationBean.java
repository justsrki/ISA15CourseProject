package beans.dao.beans;

import beans.dao.AbstractBean;
import beans.dao.interfaces.InvitationLocal;
import beans.dao.interfaces.TokenLocal;
import beans.util.MailUtilLocal;
import beans.util.TokenGeneratorLocal;
import model.dao.Invitation;
import model.dao.Reservation;
import model.dao.Token;
import model.dao.User;
import model.util.MailModel;
import util.ApplicationConfig;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class InvitationBean extends AbstractBean<Invitation> implements InvitationLocal {

    @EJB
    private MailUtilLocal mailUtilBean;
    @EJB
    private TokenLocal tokenBean;
    @EJB
    private TokenGeneratorLocal tokenGeneratorBean;

    public InvitationBean() {
        super(Invitation.class);
    }

    @Override
    public void inviteUsers(Reservation reservation, List<User> users) {
        users.forEach(user -> inviteUser(reservation, user));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void inviteUser(Reservation reservation, User user) {
        Invitation invitation = new Invitation();
        invitation.setReservationId(reservation);
        invitation.setUserId(user);
        this.create(invitation);

        String tokenString = tokenGeneratorBean.generateSessionToken();
        Token token = new Token(null, tokenString, Token.CONFIRM_REGISTRATION);
        token.setUserId(user);
        tokenBean.create(token);

        String link = ApplicationConfig.baseUrl + "/invitation/" + invitation.getId() + "?token=" + token.getValue();
        MailModel mailModel = MailModel.createInvitationMail(user.getEmail(), user.getFirstName(),
                reservation.getRestaurantId().getName(), link);

        mailUtilBean.sendEmail(mailModel);
    }
}
