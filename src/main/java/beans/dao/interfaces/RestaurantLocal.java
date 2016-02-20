package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Restaurant;
import model.dao.Table;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Srđan
 */
@Local
public interface RestaurantLocal extends AbstractLocal<Restaurant> {

    boolean setTableConfiguration(Restaurant restaurant, int rows, int columns, List<Table> tables);
}
