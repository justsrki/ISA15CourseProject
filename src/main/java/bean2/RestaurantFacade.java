package bean2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.AbstractFacade;
import model.Restaurant;

/**
 *
 * @author Srđan
 */
@Stateless
public class RestaurantFacade extends AbstractFacade<Restaurant> implements RestaurantFacadeLocal {

    @PersistenceContext(unitName = "GeneratorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RestaurantFacade() {
        super(Restaurant.class);
    }
    
}
