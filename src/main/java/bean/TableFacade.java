package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Table;

/**
 *
 * @author Srđan
 */
@Stateless
public class TableFacade extends AbstractFacade<Table> implements TableFacadeLocal {

    @PersistenceContext(unitName = "GeneratorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TableFacade() {
        super(Table.class);
    }
    
}
