package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Restaurant;
import model.dao.Table;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Srđan
 */
@Local
public interface TableLocal extends AbstractLocal<Table> {
    List<Table> getReservedTables(Date startDate, Date endDate, Restaurant restaurant);
}
