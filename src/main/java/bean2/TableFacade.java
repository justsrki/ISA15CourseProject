package bean2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.data_access.AbstractFacade;
import model.Table;

/**
 *
 * @author Srđan
 */
@Stateless
public class TableFacade extends AbstractFacade<Table> implements TableFacadeLocal {

    public TableFacade() {
        super(Table.class);
    }
    
}
