package bean;

import java.util.List;
import javax.ejb.Local;
import model.Table;

/**
 *
 * @author Srđan
 */
@Local
public interface TableFacadeLocal {

    void create(Table table);

    void edit(Table table);

    void remove(Table table);

    Table find(Object id);

    List<Table> findAll();

    List<Table> findRange(int[] range);

    int count();
    
}
