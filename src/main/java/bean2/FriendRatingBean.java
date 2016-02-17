package bean2;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
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
