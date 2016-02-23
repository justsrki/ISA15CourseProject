package beans.dao.beans;

import beans.dao.AbstractBean;
import beans.dao.interfaces.FriendRatingLocal;
import beans.dao.interfaces.InvitationLocal;
import beans.dao.interfaces.RestaurantLocal;
import beans.dao.interfaces.TokenLocal;
import beans.util.MailUtilLocal;
import beans.util.TokenGeneratorLocal;
import model.dao.*;
import model.util.MailModel;
import util.ApplicationConfig;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;
import java.util.Set;

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
    @EJB
    private RestaurantLocal restaurantBean;
    @EJB
    private FriendRatingLocal friendRatingBean;

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
        Token token = new Token(null, tokenString, Token.ACCESS_TOKEN);
        token.setUserId(user);
        tokenBean.create(token);

        String redirectUrl = "/confirm/" + invitation.getId();
        String link = ApplicationConfig.baseUrl + "/login/"
                                                + "?token=" + token.getValue()
                                                + "&userId=" + user.getId()
                                                + "&redirect=" + redirectUrl;

        MailModel mailModel = MailModel.createInvitationMail(user.getEmail(), user.getFirstName(),
                reservation.getRestaurantId().getName(), link);

        mailUtilBean.sendEmail(mailModel);
    }

    @Override
    public void setRating(Invitation invitation, int value, User user) {
        Restaurant restaurant = invitation.getReservationId().getRestaurantId();
        restaurant.setRatingCount(restaurant.getRatingCount() + 1);
        restaurant.setRatingSum(restaurant.getRatingSum() + value);
        restaurantBean.edit(restaurant);

        Set<User> followed = user.getFollowedBySet();
        followed.forEach(u -> {
            FriendRating friendRating = friendRatingBean.findByUserRestaurant(u, restaurant);
            if (friendRating == null) {
                friendRating = new FriendRating(null, value, 1);
                friendRating.setRestaurantId(restaurant);
                friendRating.setUserId(u);
                friendRatingBean.create(friendRating);
            } else {
                friendRating.setCount(friendRating.getCount() + 1);
                friendRating.setSum(friendRating.getSum() + value);
                friendRatingBean.edit(friendRating);
            }
        });

        invitation.setRating((short) value);
        this.edit(invitation);
    }
}
