package beans.dao.beans;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import beans.dao.interfaces.FriendRatingLocal;
import model.dao.FriendRating;

/**
 *
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class FriendRatingBean extends AbstractBean<FriendRating> implements FriendRatingLocal {

    public FriendRatingBean() {
        super(FriendRating.class);
    }
    
}
