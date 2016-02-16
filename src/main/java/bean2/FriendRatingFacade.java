package bean2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.data_access.AbstractFacade;
import model.FriendRating;

/**
 *
 * @author Srđan
 */
@Stateless
public class FriendRatingFacade extends AbstractFacade<FriendRating> implements FriendRatingFacadeLocal {


    public FriendRatingFacade() {
        super(FriendRating.class);
    }
    
}
