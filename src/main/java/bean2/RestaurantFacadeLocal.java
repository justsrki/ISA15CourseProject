package bean2;

import java.util.List;
import javax.ejb.Local;
import model.Restaurant;

/**
 *
 * @author Srđan
 */
@Local
public interface RestaurantFacadeLocal {

    void create(Restaurant restaurant);

    void edit(Restaurant restaurant);

    void remove(Restaurant restaurant);

    Restaurant find(Object id);

    List<Restaurant> findAll();

    List<Restaurant> findRange(int[] range);

    int count();
    
}
