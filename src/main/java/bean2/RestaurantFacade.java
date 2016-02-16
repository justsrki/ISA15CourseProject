package bean2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.data_access.AbstractFacade;
import model.Restaurant;

/**
 *
 * @author Srđan
 */
@Stateless
public class RestaurantFacade extends AbstractFacade<Restaurant> implements RestaurantFacadeLocal {



    public RestaurantFacade() {
        super(Restaurant.class);
    }
    
}
