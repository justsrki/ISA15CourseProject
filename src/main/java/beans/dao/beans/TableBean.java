package beans.dao.beans;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import beans.dao.interfaces.TableLocal;
import model.dao.Table;

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
    
}
