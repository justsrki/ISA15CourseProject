package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Table;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Srđan
 */
@Local
public interface TableLocal extends AbstractLocal<Table> {

}
