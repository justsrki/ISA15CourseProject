package bean2;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import model.dao.Table;

/**
 *
 * @author Srđan
 */
@Stateless
public class TableBean extends AbstractBean<Table> implements TableFacadeLocal {

    public TableBean() {
        super(Table.class);
    }
    
}
