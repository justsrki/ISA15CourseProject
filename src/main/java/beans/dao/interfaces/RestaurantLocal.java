package beans.dao.interfaces;

import java.util.List;
import javax.ejb.Local;
import model.dao.Restaurant;

/**
 *
 * @author Srđan
 */
@Local
public interface RestaurantLocal {

    void create(Restaurant restaurant);

    void edit(Restaurant restaurant);

    void remove(Restaurant restaurant);

    Restaurant find(Object id);

    List<Restaurant> findAll();

    List<Restaurant> findRange(int[] range);

    int count();
    
}
