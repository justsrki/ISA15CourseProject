package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.FriendRating;

/**
 *
 * @author Srđan
 */
@Stateless
public class FriendRatingFacade extends AbstractFacade<FriendRating> implements FriendRatingFacadeLocal {

    @PersistenceContext(unitName = "GeneratorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FriendRatingFacade() {
        super(FriendRating.class);
    }
    
}
