package bean2;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import model.dao.Restaurant;

/**
 *
 * @author Srđan
 */
@Stateless
public class RestaurantBean extends AbstractBean<Restaurant> implements RestaurantFacadeLocal {



    public RestaurantBean() {
        super(Restaurant.class);
    }
    
}
