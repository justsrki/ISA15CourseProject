package bean2;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import model.dao.FriendRating;

/**
 *
 * @author Srđan
 */
@Stateless
public class FriendRatingBean extends AbstractBean<FriendRating> implements FriendRatingFacadeLocal {


    public FriendRatingBean() {
        super(FriendRating.class);
    }
    
}
