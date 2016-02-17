package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Restaurant;

import javax.ejb.Local;

/**
 * @author Srđan
 */
@Local
public interface RestaurantLocal extends AbstractLocal<Restaurant> {

}
