package beans.dao;

import javax.ejb.Stateless;

import beans.dao.interfaces.RestaurantLocal;
import model.dao.Restaurant;

/**
 *
 * @author Srđan
 */
@Stateless
public class RestaurantBean extends AbstractBean<Restaurant> implements RestaurantLocal {


    public RestaurantBean() {
        super(Restaurant.class);
    }
    
}
