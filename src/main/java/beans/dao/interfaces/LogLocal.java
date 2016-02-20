package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Log;

import javax.ejb.Local;
import java.util.List;

/**
 * @author - Srđan Milaković
 */
@Local
public interface LogLocal extends AbstractLocal<Log> {
    List<Log> findAllOrderByDate(int limit);
}
