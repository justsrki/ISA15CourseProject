package beans.dao.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import beans.dao.AbstractBean;
import beans.dao.interfaces.FriendRatingLocal;
import beans.dao.interfaces.RestaurantLocal;
import beans.dao.interfaces.TokenLocal;
import beans.dao.interfaces.UserLocal;
import beans.util.MailUtilLocal;
import beans.util.PasswordGeneratorLocal;
import beans.util.TokenGeneratorLocal;
import model.dao.FriendRating;
import model.dao.Restaurant;
import model.dao.Token;
import model.dao.User;
import model.dto.restaurant.RestaurantResponse;
import model.util.MailModel;
import model.dto.user.CreateCustomerRequest;
import model.dto.user.CreateManagerRequest;
import util.ApplicationConfig;

/**
 *
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class UserBean extends AbstractBean<User> implements UserLocal {

    @EJB
    private UserLocal userBean;
    @EJB
    private TokenLocal tokenBean;
    @EJB
    private RestaurantLocal restaurantBean;
    @EJB
    private MailUtilLocal mailUtilBean;
    @EJB
    private PasswordGeneratorLocal passwordGeneratorBean;
    @EJB
    private TokenGeneratorLocal tokenGeneratorBean;
    @EJB
    private FriendRatingLocal friendRatingBean;

    public UserBean() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = getEntityManager().createNamedQuery("User.findByEmail", User.class);
        try {
            return query.setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User create(CreateCustomerRequest request) {
        User user = new User(null, request.getEmail(), request.getPassword(), User.CUSTOMER, false);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        String tokenString = tokenGeneratorBean.generateSessionToken();
        Token token = new Token(null, tokenString, Token.CONFIRM_REGISTRATION);
        token.setUserId(user);

        userBean.create(user);
        tokenBean.create(token);

        String activationLink = ApplicationConfig.baseUrl + "/api/activate?token=" + token.getValue();
        mailUtilBean.sendEmail(MailModel.createActivationMail(request, activationLink));

        return user;
    }

    @Override
    public User create(CreateManagerRequest request) {
        String password = passwordGeneratorBean.generatePassword();
        User user = new User(null, request.getEmail(), password, User.MANAGER, true);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        Restaurant restaurant = restaurantBean.find(request.getRestaurantId());

        user.setRestaurantId(restaurant);
        userBean.create(user);

        mailUtilBean.sendEmail(MailModel.createRegistrationMail(user.getEmail(), user.getFirstName(), password));

        return user;
    }

    @Override
    public void follow(User following, User followed) {
        followed.getFollowedBySet().add(following);
        following.getFollowingSet().add(followed);

        followed.getInvitationSet().forEach(invitation -> {
            if (invitation.getRating() != null) {
                Restaurant restaurant = invitation.getReservationId().getRestaurantId();
                FriendRating friendRating = friendRatingBean.findByUserRestaurant(following, restaurant);
                if (friendRating == null) {
                    friendRating = new FriendRating(null, invitation.getRating(), 1);
                    friendRating.setRestaurantId(restaurant);
                    friendRating.setUserId(following);
                    friendRatingBean.create(friendRating);
                } else {
                    friendRating.setCount(friendRating.getCount() + 1);
                    friendRating.setSum(friendRating.getSum() + invitation.getRating());
                    friendRatingBean.edit(friendRating);
                }
            }
        });

        this.edit(followed);
        this.edit(following);
    }

    @Override
    public void unfollow(User following, User followed) {
        followed.getFollowedBySet().remove(following);
        following.getFollowingSet().remove(followed);

        followed.getInvitationSet().forEach(invitation -> {
            if (invitation.getRating() != null) {
                Restaurant restaurant = invitation.getReservationId().getRestaurantId();
                FriendRating friendRating = friendRatingBean.findByUserRestaurant(following, restaurant);
                if (friendRating != null) {
                    friendRating.setCount(friendRating.getCount() - 1);
                    friendRating.setSum(friendRating.getSum() - invitation.getRating());
                    friendRatingBean.edit(friendRating);
                }
            }
        });

        this.edit(followed);
        this.edit(following);
    }
}
