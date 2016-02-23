package beans.dao.beans;

import beans.dao.AbstractBean;
import beans.dao.interfaces.FriendRatingLocal;
import model.dao.FriendRating;
import model.dao.Restaurant;
import model.dao.User;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class FriendRatingBean extends AbstractBean<FriendRating> implements FriendRatingLocal {

    public FriendRatingBean() {
        super(FriendRating.class);
    }

    @Override
    public FriendRating findByUserRestaurant(User user, Restaurant restaurant) {
        TypedQuery<FriendRating> query = getEntityManager().createNamedQuery("FriendRating.findByUserRestaurant", FriendRating.class);
        try {
            return query.setParameter("user", user).setParameter("restaurant", restaurant).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
