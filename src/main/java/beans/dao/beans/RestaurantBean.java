package beans.dao.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import beans.dao.AbstractBean;
import beans.dao.interfaces.RestaurantLocal;
import beans.dao.interfaces.TableLocal;
import model.dao.Restaurant;
import model.dao.Table;

import java.util.List;

/**
 *
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class RestaurantBean extends AbstractBean<Restaurant> implements RestaurantLocal {


    @EJB
    private TableLocal tableBean;

    public RestaurantBean() {
        super(Restaurant.class);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean setTableConfiguration(Restaurant restaurant, int rows, int columns, List<Table> tables) {
        if (restaurant.getRows() != 0 || restaurant.getColumns() != 0) {
            return false;
        }

        for (Table table : tables)  {
            table.setRestaurantId(restaurant);
            tableBean.create(table);
        }

        restaurant.setRows((short) rows);
        restaurant.setColumns((short) columns);
        this.edit(restaurant);

        return true;
    }
}
