package bean2;

import model.dao.Table;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Srđan
 */
@Local
public interface TableLocal {

    void create(Table table);

    void edit(Table table);

    void remove(Table table);

    Table find(Integer id);

    List<Table> findAll();

    List<Table> findRange(int[] range);

    int count();
    
}
