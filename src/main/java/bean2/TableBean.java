package bean2;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import model.dao.Table;

/**
 *
 * @author Srđan
 */
@Stateless
public class TableBean extends AbstractBean<Table> implements TableLocal {

    public TableBean() {
        super(Table.class);
    }
    
}
