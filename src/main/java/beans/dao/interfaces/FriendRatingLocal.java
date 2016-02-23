package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.FriendRating;
import model.dao.Restaurant;
import model.dao.User;

import javax.ejb.Local;

/**
 * @author Srđan
 */
@Local
public interface FriendRatingLocal extends AbstractLocal<FriendRating> {
    public FriendRating findByUserRestaurant(User user, Restaurant restaurant);
}
