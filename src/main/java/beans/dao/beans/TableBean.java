package beans.dao.beans;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import beans.dao.interfaces.TableLocal;
import model.dao.Restaurant;
import model.dao.Table;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class TableBean extends AbstractBean<Table> implements TableLocal {

    public TableBean() {
        super(Table.class);
    }

    @Override
    public List<Table> getReservedTables(Date startDate, Date endDate, Restaurant restaurant) {
        return getEntityManager().createNamedQuery("Table.getReserved", Table.class)
                .setParameter("restaurant", restaurant)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

}
